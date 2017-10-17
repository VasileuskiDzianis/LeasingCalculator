package by.vasilevsky.leasing.domain.lease_object;

import java.io.Serializable;

import by.vasilevsky.leasing.domain.currency.Currency;

public class Property implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private PropertyType propertyType;
	private int age;
	private float price;
	private float vat;
	private Currency currency;

	public Property() {

	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PropertyType getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(PropertyType propertyType) {
		this.propertyType = propertyType;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getVat() {
		return vat;
	}

	public void setVat(float vat) {
		this.vat = vat;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + id;
		result = prime * result + Float.floatToIntBits(price);
		result = prime * result + ((propertyType == null) ? 0 : propertyType.hashCode());
		result = prime * result + Float.floatToIntBits(vat);

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

		Property other = (Property) obj;
		if (age != other.age) {
			return false;
		}

		if (currency != other.currency) {
			return false;
		}

		if (id != other.id) {
			return false;
		}

		if (Float.floatToIntBits(price) != Float.floatToIntBits(other.price)) {
			return false;
		}

		if (propertyType != other.propertyType) {
			return false;
		}

		if (Float.floatToIntBits(vat) != Float.floatToIntBits(other.vat)) {
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		return "Property [id=" + id + ", propertyType=" + propertyType + ", age=" + age + ", price=" + price + ", vat="
				+ vat + ", currency=" + currency + "]";
	}
}
