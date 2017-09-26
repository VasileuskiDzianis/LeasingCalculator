package by.vasilevsky.leasing.service.rate.insurance;

import by.vasilevsky.leasing.dao.DaoFactory;
import by.vasilevsky.leasing.domain.lease_object.PropertyType;
import by.vasilevsky.leasing.domain.rate.insurance.Insurance;

public class InsuranceServiceImpl implements InsuranceService {
	private static volatile InsuranceServiceImpl instance;
	private static final DaoFactory daoFactory = DaoFactory.getInstance();

	private InsuranceServiceImpl() {

	}

	public static InsuranceService getInstance() {
		InsuranceServiceImpl localInstance = instance;
		if (localInstance == null) {
			synchronized (InsuranceServiceImpl.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new InsuranceServiceImpl();
				}
			}
		}
		return localInstance;
	}

	@Override
	public Insurance findInsuranceByObjectType(PropertyType objectType) {
		if (objectType == null) {
			throw new IllegalArgumentException();
		}
		return daoFactory.getInsuranceDao().findInsuranceByObjectType(objectType);
	}
}
