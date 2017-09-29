package by.vasilevsky.leasing.service.payments;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import by.vasilevsky.leasing.domain.lease_object.Property;
import by.vasilevsky.leasing.domain.payments.MonthlyPayment;
import by.vasilevsky.leasing.domain.payments.PaymentType;
import by.vasilevsky.leasing.domain.payments.PaymentsSchedule;

public class PaymentsScheduleServiceImpl implements PaymentsScheduleService {
	private static final int PAYMENT_INTERVAL = 1;
	private static final int INTERVALS_NUMBER_IN_A_YEAR = 12;
	
	private static final float ZERO_VAT_VALUE = 0f;
	private static final float MIN_BUYOUT_PERCENT = 0f;
	private static final float MIN_INSURANCE_RATE = 0f;
	private static final float MIN_LEASE_RATE = 0f;
	private static final float MIN_PREPAY_PERCENT = 0f;
	private static final float MIN_PRICE = 0f;
	private static final float MIN_VAT = 0f;
	private static final int MIN_DURATION = 0;
	private static final int MIN_AGE = 0;
	
	@Override
	public void countPayments(PaymentsSchedule schedule) {
		if (!isPaymentsScheduleValid(schedule)) {
			throw new IllegalArgumentException();
		}
		List<MonthlyPayment> payments = new ArrayList<>();
		Calendar calendar = new GregorianCalendar();
		Property property = schedule.getProperty();
		float debt = property.getPrice() + property.getVat();
		float costRepayment = countCostRepayment(schedule);
		
		MonthlyPayment prepayment = countSpecialPayment(PaymentType.PRE_PAYMENT, property,
				schedule.getPrepaymentPercentage(), calendar.getTime(), debt);
		payments.add(prepayment);
		debt -= (prepayment.getPropertyCostRepayment() + prepayment.getPropertyCostRepaymentVat());

		for (int i = 0; i < schedule.getLeaseDuration(); i++) {
			calendar.add(Calendar.MONTH, PAYMENT_INTERVAL);
			MonthlyPayment payment = countLeasePayment(schedule, calendar.getTime(), debt, costRepayment);
			debt -= payment.getPropertyCostRepayment() + payment.getPropertyCostRepaymentVat();
			payments.add(payment);
		}
		payments.add(countSpecialPayment(PaymentType.BUYING_OUT_PAYMENT, property,
				schedule.getBuyingOutPercentage(), calendar.getTime(), debt));
		schedule.setMonthlyPayments(payments);
	}

	private boolean isPropertyPriceHasVat(Property property) {

		return (property.getVat() == ZERO_VAT_VALUE) ? false : true;
	}

	private MonthlyPayment countLeasePayment(PaymentsSchedule schedule, Date date, float debt, float costRepayment) {
		MonthlyPayment payment = new MonthlyPayment();
		Property property = schedule.getProperty();
		payment.setPaymentDate(date);
		payment.setRemainingDebt(debt);
		payment.setPaymentType(PaymentType.LEASE_PAYMENT);
		payment.setLeaseMargin(schedule.getLeaseRate() * debt / INTERVALS_NUMBER_IN_A_YEAR);
		payment.setLeaseMarginVat(payment.getLeaseMargin() * VAT_RATE);
		payment.setInsurance((property.getPrice() + property.getVat()) * schedule.getInsuranceRate() / INTERVALS_NUMBER_IN_A_YEAR);
		payment.setInsuranceVat(payment.getInsurance() * VAT_RATE);
		payment.setPropertyCostRepayment(costRepayment);
		if (isPropertyPriceHasVat(property)) {
			payment.setPropertyCostRepaymentVat(costRepayment * VAT_RATE);
		}
		return payment;
	}

	private MonthlyPayment countSpecialPayment(PaymentType type, Property property, float percent, Date date,
			float debt) {
		MonthlyPayment payment = new MonthlyPayment();
		payment.setPaymentDate(date);
		payment.setRemainingDebt(debt);
		payment.setPaymentType(type);
		payment.setPropertyCostRepayment(property.getPrice() * percent);
		if (isPropertyPriceHasVat(property)) {
			payment.setPropertyCostRepaymentVat(property.getVat() * percent);
		}
		return payment;
	}

	private float countCostRepayment(PaymentsSchedule schedule) {
		Property property = schedule.getProperty();

		return property.getPrice() * (1 - schedule.getBuyingOutPercentage() - schedule.getPrepaymentPercentage())
				/ schedule.getLeaseDuration();
	}
	
	private boolean isPaymentsScheduleValid(PaymentsSchedule schedule) {
				
		return schedule != null 
				&& schedule.getBuyingOutPercentage() >= MIN_BUYOUT_PERCENT
				&& schedule.getCurrency() != null 
				&& schedule.getInsuranceRate() >= MIN_INSURANCE_RATE
				&& schedule.getLeaseDuration() >= MIN_DURATION
				&& schedule.getLeaseRate() >= MIN_LEASE_RATE
				&& schedule.getPrepaymentPercentage() >= MIN_PREPAY_PERCENT
				&& schedule.getProperty().getPropertyType() != null
				&& schedule.getProperty().getCurrency() != null
				&& schedule.getProperty().getAge() >= MIN_AGE
				&& schedule.getProperty().getPrice() > MIN_PRICE
				&& schedule.getProperty().getVat() > MIN_VAT;
	}
}
