package by.vasilevsky.leasing.service.rate.insurance;

import by.vasilevsky.leasing.domain.lease_object.LeaseObjectType;
import by.vasilevsky.leasing.domain.rate.insurance.LeaseTypeInsurance;

public interface LeaseTypeInsuranceService {
	
	LeaseTypeInsurance findInsuranceByObjectType(LeaseObjectType objectType);
}
