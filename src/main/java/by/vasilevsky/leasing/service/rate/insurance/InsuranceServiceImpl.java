package by.vasilevsky.leasing.service.rate.insurance;

import by.vasilevsky.leasing.dao.DaoFactory;
import by.vasilevsky.leasing.domain.lease_object.PropertyType;
import by.vasilevsky.leasing.domain.rate.insurance.Insurance;

public class InsuranceServiceImpl implements InsuranceService {

	@Override
	public Insurance findInsuranceByObjectType(PropertyType objectType) {
		if (objectType == null) {
			throw new IllegalArgumentException();
		}
		return DaoFactory.getInstance().getInsuranceDao().findInsuranceByObjectType(objectType);
	}
}
