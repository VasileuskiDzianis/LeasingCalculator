package by.vasilevsky.leasing.domain.lease_object;

import by.vasilevsky.leasing.domain.currency.Currency;

public class LeaseObject {
	private int id;
	private LeaseObjectType leaseObjectType;
	private int age;
	private float price;
	private float vat;
	private Currency currency;

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

	public LeaseObjectType getLeaseObjectType() {
		return leaseObjectType;
	}

	public void setLeaseObjectType(LeaseObjectType leaseObjectType) {
		this.leaseObjectType = leaseObjectType;
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

}
