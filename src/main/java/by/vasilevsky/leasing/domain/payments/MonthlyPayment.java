package by.vasilevsky.leasing.domain.payments;

import java.util.Date;

public class MonthlyPayment {
	private int id;
	private Date paymentDate;
	private PaymentType paymentType;
	private float debt;
	private float leaseMargin;
	private float leaseMarginVat;
	private float propertyCostRepayment;
	private float propertyCostRepaymentVat;
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
		return debt;
	}

	public void setRemainingDebt(float remainingDebt) {
		this.debt = remainingDebt;
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

	public float getPropertyCostRepayment() {
		return propertyCostRepayment;
	}

	public void setPropertyCostRepayment(float propertyCostRepayment) {
		this.propertyCostRepayment = propertyCostRepayment;
	}

	public float getPropertyCostRepaymentVat() {
		return propertyCostRepaymentVat;
	}

	public void setPropertyCostRepaymentVat(float propertyCostVatRepayment) {
		this.propertyCostRepaymentVat = propertyCostVatRepayment;
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
