package by.vasilevsky.leasing.domain.rate;

import java.io.Serializable;

import by.vasilevsky.leasing.domain.lease_object.PropertyType;

public class Insurance implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private PropertyType objectType;
	private float rate;

	public Insurance() {

	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((objectType == null) ? 0 : objectType.hashCode());
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

		Insurance other = (Insurance) obj;
		if (id != other.id) {
			return false;
		}

		if (objectType != other.objectType) {
			return false;
		}

		if (Float.floatToIntBits(rate) != Float.floatToIntBits(other.rate)) {
			return false;
		}

		return true;
	}
}
