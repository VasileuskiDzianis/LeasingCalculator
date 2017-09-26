package by.vasilevsky.leasing.domain.rate.lease;

import by.vasilevsky.leasing.domain.currency.Currency;

public class BaseRate {
	private int id;
	private Currency currency;
	private float rate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}
}
