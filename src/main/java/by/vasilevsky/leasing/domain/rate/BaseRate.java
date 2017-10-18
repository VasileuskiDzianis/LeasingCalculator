package by.vasilevsky.leasing.domain.rate;

import java.io.Serializable;

import by.vasilevsky.leasing.domain.currency.Currency;

public class BaseRate implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private Currency currency;
	private float rate;

	public BaseRate() {

	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + id;
		result = prime * result + Float.floatToIntBits(rate);

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

		BaseRate other = (BaseRate) obj;
		if (currency != other.currency) {
			return false;
		}

		if (id != other.id) {
			return false;
		}

		if (Float.floatToIntBits(rate) != Float.floatToIntBits(other.rate)) {
			return false;
		}

		return true;
	}
}
