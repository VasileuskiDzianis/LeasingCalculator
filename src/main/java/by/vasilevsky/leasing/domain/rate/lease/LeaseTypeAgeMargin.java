package by.vasilevsky.leasing.domain.rate.lease;

import by.vasilevsky.leasing.domain.lease_object.PropertyType;

public class LeaseTypeAgeMargin {
	private int id;
	private PropertyType objectType;
	private int objectAge;
	private float margin;

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

	public int getObjectAge() {
		return objectAge;
	}

	public void setObjectAge(int objectAge) {
		this.objectAge = objectAge;
	}

	public float getMargin() {
		return margin;
	}

	public void setMargin(float margin) {
		this.margin = margin;
	}
}
