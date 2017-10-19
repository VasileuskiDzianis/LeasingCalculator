package by.vasilevsky.leasing.dao.rate;

import by.vasilevsky.leasing.dao.DaoException;
import by.vasilevsky.leasing.domain.lease_object.PropertyType;
import by.vasilevsky.leasing.domain.rate.Insurance;

public interface InsuranceDao {

	Insurance findInsuranceByObjectType(PropertyType objectType) throws DaoException;
}
