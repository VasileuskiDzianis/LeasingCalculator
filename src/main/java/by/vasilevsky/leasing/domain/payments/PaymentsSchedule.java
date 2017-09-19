package by.vasilevsky.leasing.domain.payments;

import java.util.List;

import by.vasilevsky.leasing.domain.currency.Currency;
import by.vasilevsky.leasing.domain.lease_object.Property;

public class PaymentsSchedule {
	private int id;
	private Property property;
	private float prepaymentPercentage;
	private float buyingOutPercentage;
	private Currency currency;
	private float leaseRate;
	private float insuranceRate;
	private int leaseDuration;
	private List<MonthlyPayment> monthlyPayments;

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
}
