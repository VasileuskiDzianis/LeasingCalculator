package by.vasilevsky.leasing.service.payments;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import by.vasilevsky.leasing.domain.lease_object.Property;
import by.vasilevsky.leasing.domain.lease_object.PropertyType;
import by.vasilevsky.leasing.domain.payments.MonthlyPayment;
import by.vasilevsky.leasing.domain.payments.PaymentType;
import by.vasilevsky.leasing.domain.payments.PaymentsSchedule;

public class PaymentsScheduleServiceImplTest {
	private static final PropertyType LEASE_OBJECT_TYPE = PropertyType.CAR;
	private static final int LEASE_OBJECT_AGE = 1;
	private static final float LEASE_OBJECT_PRICE = 10000f;
	private static final float LEASE_OBJECT_VAT = 2000f;
	
	private static final float PREPAYMENT_PERCENTAGE = 0.1f;
	private static final float BUYINGOUT_PERCENTAGE = 0.1f;
	private static final float LEASE_RATE = 0.15f;
	private static final float INSURANCE_RATE = 0.03f;
	private static final int LEASE_DURATION = 12;
	private static final int PREPAYMENT_INDEX = 0;
	private static final int FIRST_LEASE_PAY_INDEX = 1;
	private static final int BUYINGOUT_INDEX = LEASE_DURATION + 1;
	
	private static final float VAT_RATE = 0.2f;
	
	private static final float PRECISION = 0.000001f;
	
	PaymentsSchedule paymentsSchedule;
	PaymentsScheduleService paymentsService = PaymentsScheduleServiceImpl.getInstance();
	

	@Before
	public void setUp() throws Exception {
		paymentsSchedule = new PaymentsSchedule();
		Property property = new Property();
		property.setAge(LEASE_OBJECT_AGE);
		property.setPropertyType(LEASE_OBJECT_TYPE);
		property.setPrice(LEASE_OBJECT_PRICE);
		property.setVat(LEASE_OBJECT_VAT);
		paymentsSchedule.setProperty(property);
		paymentsSchedule.setBuyingOutPercentage(BUYINGOUT_PERCENTAGE);
		paymentsSchedule.setPrepaymentPercentage(PREPAYMENT_PERCENTAGE);
		paymentsSchedule.setInsuranceRate(INSURANCE_RATE);
		paymentsSchedule.setLeaseDuration(LEASE_DURATION);
		paymentsSchedule.setLeaseRate(LEASE_RATE);
		
	}

	@Test
	public void checkNumberOfPaymentsTest() {
		paymentsService.countPayments(paymentsSchedule);
		int leasePaymentsNumber = paymentsSchedule.getMonthlyPayments().size() - 2;
		
		assertEquals(LEASE_DURATION, leasePaymentsNumber);
	}
	
	@Test
	public void checkPrepayment() {
		paymentsService.countPayments(paymentsSchedule);
		MonthlyPayment prepayment = paymentsSchedule.getMonthlyPayments().get(PREPAYMENT_INDEX);
		
		float expectedRemainingDebt = LEASE_OBJECT_PRICE + LEASE_OBJECT_VAT;
		float expectedLeaseObjectCostRepayment = LEASE_OBJECT_PRICE * PREPAYMENT_PERCENTAGE;
		float expectedLeaseObjectCostRepaymentVat = LEASE_OBJECT_VAT * PREPAYMENT_PERCENTAGE;
		
		assertEquals(expectedRemainingDebt, prepayment.getRemainingDebt(), PRECISION);
		assertEquals(expectedLeaseObjectCostRepayment, prepayment.getPropertyCostRepayment(), PRECISION);
		assertEquals(expectedLeaseObjectCostRepaymentVat, prepayment.getPropertyCostRepaymentVat(), PRECISION);
		assertEquals(PaymentType.PRE_PAYMENT, prepayment.getPaymentType());
		assertEquals(0f, prepayment.getInsurance(), PRECISION);
		assertEquals(0f, prepayment.getInsuranceVat(), PRECISION);
		assertEquals(0f, prepayment.getLeaseMargin(), PRECISION);
		assertEquals(0f, prepayment.getLeaseMarginVat(), PRECISION);
	}
	
	@Test
	public void checkBuyingout() {
		paymentsService.countPayments(paymentsSchedule);
		MonthlyPayment buyingout = paymentsSchedule.getMonthlyPayments().get(BUYINGOUT_INDEX);
		
		float expectedRemainingDebt = (LEASE_OBJECT_PRICE + LEASE_OBJECT_VAT) * BUYINGOUT_PERCENTAGE;
		float expectedLeaseObjectCostRepayment = LEASE_OBJECT_PRICE * BUYINGOUT_PERCENTAGE;
		float expectedLeaseObjectCostRepaymentVat = LEASE_OBJECT_VAT * BUYINGOUT_PERCENTAGE;
		
		assertEquals(expectedRemainingDebt, buyingout.getRemainingDebt(), PRECISION);
		assertEquals(expectedLeaseObjectCostRepayment, buyingout.getPropertyCostRepayment(), PRECISION);
		assertEquals(expectedLeaseObjectCostRepaymentVat, buyingout.getPropertyCostRepaymentVat(), PRECISION);
		assertEquals(PaymentType.BUYING_OUT_PAYMENT, buyingout.getPaymentType());
		assertEquals(0f, buyingout.getInsurance(), PRECISION);
		assertEquals(0f, buyingout.getInsuranceVat(), PRECISION);
		assertEquals(0f, buyingout.getLeaseMargin(), PRECISION);
		assertEquals(0f, buyingout.getLeaseMarginVat(), PRECISION);
	}
	
	@Test
	public void checkMarginRate() {
		paymentsService.countPayments(paymentsSchedule);
		List<MonthlyPayment> payments = paymentsSchedule.getMonthlyPayments();
		
		for (int i = FIRST_LEASE_PAY_INDEX; i <= LEASE_DURATION; i++) {
			MonthlyPayment leasePayment = payments.get(i);
			float calculatedRate = leasePayment.getLeaseMargin() * 12 / leasePayment.getRemainingDebt();
			
			assertEquals(LEASE_RATE, calculatedRate, PRECISION);
		}
	}
	
	@Test
	public void checkMarginRateVat() {
		paymentsService.countPayments(paymentsSchedule);
		List<MonthlyPayment> payments = paymentsSchedule.getMonthlyPayments();
		
		for (int i = FIRST_LEASE_PAY_INDEX; i <= LEASE_DURATION; i++) {
			MonthlyPayment leasePayment = payments.get(i);
			float expectedVat = leasePayment.getLeaseMargin() * VAT_RATE;
			
			assertEquals(expectedVat, leasePayment.getLeaseMarginVat(), PRECISION);
		}
	}
	
	@Test
	public void checkInsurance() {
		paymentsService.countPayments(paymentsSchedule);
		List<MonthlyPayment> payments = paymentsSchedule.getMonthlyPayments();
		
		for (int i = FIRST_LEASE_PAY_INDEX; i <= LEASE_DURATION; i++) {
			MonthlyPayment leasePayment = payments.get(i);
			float expectedInsurance = (LEASE_OBJECT_PRICE + LEASE_OBJECT_VAT) * INSURANCE_RATE / 12;
			float expectedInsuranceVat = expectedInsurance * VAT_RATE;
			
			assertEquals(expectedInsurance, leasePayment.getInsurance(), PRECISION);
			assertEquals(expectedInsuranceVat, leasePayment.getInsuranceVat(), PRECISION);
		}
	}
	
	@Test
	public void checkMonthlyDebtRepayment() {
		paymentsService.countPayments(paymentsSchedule);
		List<MonthlyPayment> payments = paymentsSchedule.getMonthlyPayments();
		
		for (int i = FIRST_LEASE_PAY_INDEX; i <= LEASE_DURATION; i++) {
			MonthlyPayment leasePayment = payments.get(i);
			float expectedDebtRepayment = LEASE_OBJECT_PRICE * (1 - PREPAYMENT_PERCENTAGE - BUYINGOUT_PERCENTAGE) / 12;
			float expectedDebtRepaymentVat = expectedDebtRepayment * VAT_RATE;
			
			assertEquals(expectedDebtRepayment, leasePayment.getPropertyCostRepayment(), PRECISION);
			assertEquals(expectedDebtRepaymentVat, leasePayment.getPropertyCostRepaymentVat(), PRECISION);
		}
	}
}
