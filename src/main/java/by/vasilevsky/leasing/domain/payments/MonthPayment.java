package by.vasilevsky.leasing.domain.payments;

import java.util.Date;

public class MonthPayment {
	private int id;
	private Date paymentDate;
	private PaymentType paymentType;
	private float remainingDebt;
	private float leaseMargin;
	private float leaseMarginVat;
	private float leaseObjectCostRepayment;
	private float leaseObjectCostRepaymentVat;
	private float insurance;
	private float insuranceVat;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public float getRemainingDebt() {
		return remainingDebt;
	}

	public void setRemainingDebt(float remainingDebt) {
		this.remainingDebt = remainingDebt;
	}

	public float getLeaseMargin() {
		return leaseMargin;
	}

	public void setLeaseMargin(float leaseMargin) {
		this.leaseMargin = leaseMargin;
	}

	public float getLeaseMarginVat() {
		return leaseMarginVat;
	}

	public void setLeaseMarginVat(float leaseMarginVat) {
		this.leaseMarginVat = leaseMarginVat;
	}

	public float getLeaseObjectCostRepayment() {
		return leaseObjectCostRepayment;
	}

	public void setLeaseObjectCostRepayment(float leaseObjectCostRepayment) {
		this.leaseObjectCostRepayment = leaseObjectCostRepayment;
	}

	public float getLeaseObjectCostRepaymentVat() {
		return leaseObjectCostRepaymentVat;
	}

	public void setLeaseObjectCostRepaymentVat(float leaseObjectCostVatRepayment) {
		this.leaseObjectCostRepaymentVat = leaseObjectCostVatRepayment;
	}

	public float getInsurance() {
		return insurance;
	}

	public void setInsurance(float insurance) {
		this.insurance = insurance;
	}

	public float getInsuranceVat() {
		return insuranceVat;
	}

	public void setInsuranceVat(float insuranceVat) {
		this.insuranceVat = insuranceVat;
	}
}
