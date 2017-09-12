package by.vasilevsky.leasing.domain.rate.insurance;

import by.vasilevsky.leasing.domain.lease_object.LeaseObjectType;

public class LeaseTypeInsurance {
	private int id;
	private LeaseObjectType objectType;
	private float insuranceRate;

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

	public float getInsuranceRate() {
		return insuranceRate;
	}

	public void setInsuranceRate(float insuranceRate) {
		this.insuranceRate = insuranceRate;
	}
}
