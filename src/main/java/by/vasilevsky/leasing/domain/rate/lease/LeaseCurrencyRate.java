package by.vasilevsky.leasing.domain.rate.lease;

import by.vasilevsky.leasing.domain.currency.Currency;

public class LeaseCurrencyRate {
	private int id;
	private Currency currency;
	private float currencyRate;

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

	public float getCurrencyRate() {
		return currencyRate;
	}

	public void setCurrencyRate(float currencyRate) {
		this.currencyRate = currencyRate;
	}
}
