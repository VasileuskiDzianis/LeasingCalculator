package by.vasilevsky.leasing.domain.payments;

import java.io.Serializable;
import java.util.List;

import by.vasilevsky.leasing.domain.currency.Currency;
import by.vasilevsky.leasing.domain.lease_object.Property;

public class PaymentsSchedule implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private Property property;
	private float prepaymentPercentage;
	private float buyingOutPercentage;
	private Currency currency;
	private float leaseRate;
	private float insuranceRate;
	private int leaseDuration;
	private List<MonthlyPayment> monthlyPayments;

	public PaymentsSchedule() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public float getPrepaymentPercentage() {
		return prepaymentPercentage;
	}

	public void setPrepaymentPercentage(float prepaymentPercentage) {
		this.prepaymentPercentage = prepaymentPercentage;
	}

	public float getBuyingOutPercentage() {
		return buyingOutPercentage;
	}

	public void setBuyingOutPercentage(float buyingOutPercentage) {
		this.buyingOutPercentage = buyingOutPercentage;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public float getLeaseRate() {
		return leaseRate;
	}

	public void setLeaseRate(float leaseRate) {
		this.leaseRate = leaseRate;
	}

	public float getInsuranceRate() {
		return insuranceRate;
	}

	public void setInsuranceRate(float insuranceRate) {
		this.insuranceRate = insuranceRate;
	}

	public int getLeaseDuration() {
		return leaseDuration;
	}

	public void setLeaseDuration(int leaseDuration) {
		this.leaseDuration = leaseDuration;
	}

	public List<MonthlyPayment> getMonthlyPayments() {
		return monthlyPayments;
	}

	public void setMonthlyPayments(List<MonthlyPayment> monthlyPayments) {
		this.monthlyPayments = monthlyPayments;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(buyingOutPercentage);
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + id;
		result = prime * result + Float.floatToIntBits(insuranceRate);
		result = prime * result + leaseDuration;
		result = prime * result + Float.floatToIntBits(leaseRate);
		result = prime * result + ((monthlyPayments == null) ? 0 : monthlyPayments.hashCode());
		result = prime * result + Float.floatToIntBits(prepaymentPercentage);
		result = prime * result + ((property == null) ? 0 : property.hashCode());

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

		PaymentsSchedule other = (PaymentsSchedule) obj;
		if (Float.floatToIntBits(buyingOutPercentage) != Float.floatToIntBits(other.buyingOutPercentage)) {
			return false;
		}

		if (currency != other.currency) {
			return false;
		}

		if (id != other.id) {
			return false;
		}

		if (Float.floatToIntBits(insuranceRate) != Float.floatToIntBits(other.insuranceRate)) {
			return false;
		}

		if (leaseDuration != other.leaseDuration) {
			return false;
		}

		if (Float.floatToIntBits(leaseRate) != Float.floatToIntBits(other.leaseRate)) {
			return false;
		}

		if (monthlyPayments == null) {
			if (other.monthlyPayments != null) {
				return false;
			}

		} else if (!monthlyPayments.equals(other.monthlyPayments)) {
			return false;
		}

		if (Float.floatToIntBits(prepaymentPercentage) != Float.floatToIntBits(other.prepaymentPercentage)) {
			return false;
		}

		if (property == null) {
			if (other.property != null) {
				return false;
			}

		} else if (!property.equals(other.property)) {
			return false;
		}

		return true;
	}
}
