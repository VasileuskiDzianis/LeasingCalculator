package by.vasilevsky.leasing.service.rate;

import by.vasilevsky.leasing.domain.lease_object.PropertyType;
import by.vasilevsky.leasing.domain.rate.Insurance;

public interface InsuranceService {
	
	Insurance findInsuranceByObjectType(PropertyType objectType);
}
