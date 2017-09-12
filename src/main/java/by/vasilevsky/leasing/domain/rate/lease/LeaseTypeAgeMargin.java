package by.vasilevsky.leasing.domain.rate.lease;

import by.vasilevsky.leasing.domain.lease_object.LeaseObjectType;

public class LeaseTypeAgeMargin {
	private int id;
	private LeaseObjectType objectType;
	private int objectAge;
	private float margin;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LeaseObjectType getObjectType() {
		return objectType;
	}

	public void setObjectType(LeaseObjectType objectType) {
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
