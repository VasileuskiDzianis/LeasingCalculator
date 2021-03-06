package by.vasilevsky.leasing.service.rate.impl;

import by.vasilevsky.leasing.dao.DaoFactory;
import by.vasilevsky.leasing.dao.rate.InsuranceDao;
import by.vasilevsky.leasing.domain.lease_object.PropertyType;
import by.vasilevsky.leasing.domain.rate.Insurance;
import by.vasilevsky.leasing.service.rate.InsuranceService;

public class InsuranceServiceImpl implements InsuranceService {

	private final InsuranceDao insuranceDao;

	public InsuranceServiceImpl() {
		insuranceDao = DaoFactory.getInstance().getInsuranceDao();
	}

	@Override
	public Insurance findInsuranceByObjectType(PropertyType objectType) {
		if (objectType == null) {
			throw new IllegalArgumentException("Object type can not be NULL");
		}

		return insuranceDao.findInsuranceByObjectType(objectType);
	}
}
