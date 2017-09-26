package by.vasilevsky.leasing.domain.rate.lease;

import by.vasilevsky.leasing.domain.lease_object.PropertyType;

public class Margin {
	private int id;
	private PropertyType propertyType;
	private int objectAge;
	private float margin;

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

	public int getPropertyAge() {
		return objectAge;
	}

	public void setPropertyAge(int objectAge) {
		this.objectAge = objectAge;
	}

	public float getMargin() {
		return margin;
	}

	public void setMargin(float margin) {
		this.margin = margin;
	}
}
