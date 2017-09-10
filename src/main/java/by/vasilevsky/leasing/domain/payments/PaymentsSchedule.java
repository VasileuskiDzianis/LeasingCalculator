package by.vasilevsky.leasing.domain.payments;

import java.util.List;

import by.vasilevsky.leasing.domain.currency.Currency;
import by.vasilevsky.leasing.domain.lease_object.LeaseObject;

public class PaymentsSchedule {
	private int id;
	private LeaseObject leaseObject;
	private float prepaymentPercentage;
	private float buyingOutPercentage;
	private Currency currency;
	private float leaseRate;
	private float insuranceRate;
	private int leaseDuration;
	private List<MonthPayment> monthlyPayments;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LeaseObject getLeaseObject() {
		return leaseObject;
	}

	public void setLeaseObject(LeaseObject leaseObject) {
		this.leaseObject = leaseObject;
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

	public List<MonthPayment> getMonthlyPayments() {
		return monthlyPayments;
	}

	public void setMonthlyPayments(List<MonthPayment> monthlyPayments) {
		this.monthlyPayments = monthlyPayments;
	}
}
