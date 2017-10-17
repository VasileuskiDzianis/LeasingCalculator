package by.vasilevsky.leasing.domain.payments;

import java.io.Serializable;
import java.util.Date;

public class MonthlyPayment implements Serializable {
	private static final long serialVersionUID = 1L;

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

	public MonthlyPayment() {

	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(debt);
		result = prime * result + id;
		result = prime * result + Float.floatToIntBits(insurance);
		result = prime * result + Float.floatToIntBits(insuranceVat);
		result = prime * result + Float.floatToIntBits(leaseMargin);
		result = prime * result + Float.floatToIntBits(leaseMarginVat);
		result = prime * result + ((paymentDate == null) ? 0 : paymentDate.hashCode());
		result = prime * result + ((paymentType == null) ? 0 : paymentType.hashCode());
		result = prime * result + Float.floatToIntBits(propertyCostRepayment);
		result = prime * result + Float.floatToIntBits(propertyCostRepaymentVat);

		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		MonthlyPayment other = (MonthlyPayment) obj;
		if (Float.floatToIntBits(debt) != Float.floatToIntBits(other.debt)) {
			return false;
		}

		if (id != other.id) {
			return false;
		}

		if (Float.floatToIntBits(insurance) != Float.floatToIntBits(other.insurance)) {
			return false;
		}

		if (Float.floatToIntBits(insuranceVat) != Float.floatToIntBits(other.insuranceVat)) {
			return false;
		}

		if (Float.floatToIntBits(leaseMargin) != Float.floatToIntBits(other.leaseMargin)) {
			return false;
		}

		if (Float.floatToIntBits(leaseMarginVat) != Float.floatToIntBits(other.leaseMarginVat)) {
			return false;
		}

		if (paymentDate == null) {
			if (other.paymentDate != null) {
				return false;
			}

		} else if (!paymentDate.equals(other.paymentDate)) {
			return false;
		}

		if (paymentType != other.paymentType) {
			return false;
		}

		if (Float.floatToIntBits(propertyCostRepayment) != Float.floatToIntBits(other.propertyCostRepayment)) {
			return false;
		}

		if (Float.floatToIntBits(propertyCostRepaymentVat) != Float.floatToIntBits(other.propertyCostRepaymentVat)) {
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		return "MonthlyPayment [id=" + id + ", paymentDate=" + paymentDate + ", paymentType=" + paymentType + ", debt="
				+ debt + ", leaseMargin=" + leaseMargin + ", leaseMarginVat=" + leaseMarginVat
				+ ", propertyCostRepayment=" + propertyCostRepayment + ", propertyCostRepaymentVat="
				+ propertyCostRepaymentVat + ", insurance=" + insurance + ", insuranceVat=" + insuranceVat + "]";
	}
}
