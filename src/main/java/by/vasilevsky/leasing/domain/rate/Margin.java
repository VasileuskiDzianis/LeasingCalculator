package by.vasilevsky.leasing.domain.rate;

import java.io.Serializable;

import by.vasilevsky.leasing.domain.lease_object.PropertyType;

public class Margin implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private PropertyType propertyType;
	private int objectAge;
	private float margin;

	public Margin() {

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + Float.floatToIntBits(margin);
		result = prime * result + objectAge;
		result = prime * result + ((propertyType == null) ? 0 : propertyType.hashCode());

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

		Margin other = (Margin) obj;
		if (id != other.id) {
			return false;
		}

		if (Float.floatToIntBits(margin) != Float.floatToIntBits(other.margin)) {
			return false;
		}

		if (objectAge != other.objectAge) {
			return false;
		}

		if (propertyType != other.propertyType) {
			return false;
		}

		return true;
	}
}
