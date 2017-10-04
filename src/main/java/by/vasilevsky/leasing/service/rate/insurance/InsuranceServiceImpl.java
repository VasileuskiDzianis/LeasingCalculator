package by.vasilevsky.leasing.service.rate.insurance;

import by.vasilevsky.leasing.dao.DaoFactory;
import by.vasilevsky.leasing.dao.rate.insurance.InsuranceDao;
import by.vasilevsky.leasing.domain.lease_object.PropertyType;
import by.vasilevsky.leasing.domain.rate.insurance.Insurance;

public class InsuranceServiceImpl implements InsuranceService {

	private final InsuranceDao insuranceDao;

	public InsuranceServiceImpl() {
		insuranceDao = DaoFactory.getInstance().getInsuranceDao();
	}

	@Override
	public Insurance findInsuranceByObjectType(PropertyType objectType) {
		if (objectType == null) {
			throw new IllegalArgumentException();
		}

		return insuranceDao.findInsuranceByObjectType(objectType);
	}
}
