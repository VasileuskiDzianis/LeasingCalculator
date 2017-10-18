package by.vasilevsky.leasing.dao.rate;

import by.vasilevsky.leasing.domain.lease_object.PropertyType;
import by.vasilevsky.leasing.domain.rate.insurance.Insurance;

public interface InsuranceDao {

	Insurance findInsuranceByObjectType(PropertyType objectType);
}
