package by.vasilevsky.leasing.service.payments;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import by.vasilevsky.leasing.domain.lease_object.LeaseObject;
import by.vasilevsky.leasing.domain.payments.MonthPayment;
import by.vasilevsky.leasing.domain.payments.PaymentType;
import by.vasilevsky.leasing.domain.payments.PaymentsSchedule;

public class PaymentsScheduleServiceImpl implements PaymentsScheduleService {
	private static int PAYMENT_INTERVAL = 1;

	@Override
	public void calculatePayments(PaymentsSchedule paymentsSchedule) {
		float leaseObjectCost = paymentsSchedule.getLeaseObject().getPrice();
		float leaseObjectCostVat = paymentsSchedule.getLeaseObject().getVat();
		float remainingDebt = leaseObjectCost + leaseObjectCostVat;
		float mounthlyCostRepayment;
		List<MonthPayment> monthlyPayments = new ArrayList<>();
		MonthPayment monthlyPayment;
		MonthPayment prepayment = new MonthPayment();
		Calendar calendar = new GregorianCalendar();

			mounthlyCostRepayment = (leaseObjectCost * (1 - paymentsSchedule.getBuyingOutPercentage() - 
					paymentsSchedule.getPrepaymentPercentage())) / paymentsSchedule.getLeaseDuration();
		

		prepayment.setLeaseObjectCostRepayment(leaseObjectCost * paymentsSchedule.getPrepaymentPercentage());
		if (isLeaseObjectPriceHasVat(paymentsSchedule.getLeaseObject())) {
			prepayment.setLeaseObjectCostRepaymentVat(leaseObjectCostVat * paymentsSchedule.getPrepaymentPercentage());
		}
		prepayment.setPaymentDate(calendar.getTime());
		prepayment.setPaymentType(PaymentType.PRE_PAYMENT);
		prepayment.setRemainingDebt(remainingDebt);

		remainingDebt -= (prepayment.getLeaseObjectCostRepayment() + prepayment.getLeaseObjectCostRepaymentVat());

		monthlyPayments.add(prepayment);

		for (int i = 0; i < paymentsSchedule.getLeaseDuration(); i++) {
			monthlyPayment = new MonthPayment();
			calendar.add(Calendar.MONTH, PAYMENT_INTERVAL);
			monthlyPayment.setPaymentDate(calendar.getTime());
			monthlyPayment.setPaymentType(PaymentType.LEASE_PAYMENT);
			monthlyPayment.setRemainingDebt(remainingDebt);
			monthlyPayment.setLeaseMargin(paymentsSchedule.getLeaseRate() * remainingDebt / 12);
			monthlyPayment.setLeaseMarginVat(monthlyPayment.getLeaseMargin() * VAT_RATE);
			monthlyPayment.setInsurance((leaseObjectCost + leaseObjectCostVat) * paymentsSchedule.getInsuranceRate());
			monthlyPayment.setInsuranceVat(monthlyPayment.getInsurance() * VAT_RATE);
			monthlyPayment.setLeaseObjectCostRepayment(mounthlyCostRepayment);

			if (isLeaseObjectPriceHasVat(paymentsSchedule.getLeaseObject())) {
				monthlyPayment.setLeaseObjectCostRepaymentVat(mounthlyCostRepayment * VAT_RATE);
			}
			
			remainingDebt -= monthlyPayment.getLeaseObjectCostRepayment() + monthlyPayment.getLeaseObjectCostRepaymentVat();
			
			monthlyPayments.add(monthlyPayment);
		}
		MonthPayment buyingOutPayment = new MonthPayment();
		buyingOutPayment.setLeaseObjectCostRepayment(leaseObjectCost * paymentsSchedule.getBuyingOutPercentage());
		if (isLeaseObjectPriceHasVat(paymentsSchedule.getLeaseObject())) {
			buyingOutPayment.setLeaseObjectCostRepaymentVat(leaseObjectCostVat * paymentsSchedule.getBuyingOutPercentage());
		}
		buyingOutPayment.setPaymentDate(calendar.getTime());
		buyingOutPayment.setPaymentType(PaymentType.BUYING_OUT_PAYMENT);
		
		monthlyPayments.add(buyingOutPayment);
		
		paymentsSchedule.setMonthlyPayments(monthlyPayments);
	}

	private boolean isLeaseObjectPriceHasVat(LeaseObject leaseObject) {

		return (leaseObject.getVat() == 0f) ? false : true;
	}
}
