package by.vasilevsky.leasing.service.rate.insurance;

import by.vasilevsky.leasing.domain.lease_object.PropertyType;
import by.vasilevsky.leasing.domain.rate.insurance.Insurance;

public interface InsuranceService {
	
	Insurance findInsuranceByObjectType(PropertyType objectType);
}