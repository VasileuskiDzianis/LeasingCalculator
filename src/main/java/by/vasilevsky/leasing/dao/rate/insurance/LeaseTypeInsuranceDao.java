package by.vasilevsky.leasing.dao.rate.insurance;

import by.vasilevsky.leasing.domain.lease_object.LeaseObjectType;
import by.vasilevsky.leasing.domain.rate.insurance.LeaseTypeInsurance;

public interface LeaseTypeInsuranceDao {

	LeaseTypeInsurance findInsuranceByObjectType(LeaseObjectType objectType);
}
