package by.vasilevsky.leasing.domain.rate.insurance;

import by.vasilevsky.leasing.domain.lease_object.PropertyType;

public class Insurance {
	private int id;
	private PropertyType objectType;
	private float rate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PropertyType getObjectType() {
		return objectType;
	}

	public void setObjectType(PropertyType objectType) {
		this.objectType = objectType;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}
}
