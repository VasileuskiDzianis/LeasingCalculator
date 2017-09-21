package by.vasilevsky.leasing.service.rate.insurance;

import by.vasilevsky.leasing.dao.DaoFactory;
import by.vasilevsky.leasing.dao.DaoFactoryImpl;
import by.vasilevsky.leasing.domain.lease_object.PropertyType;
import by.vasilevsky.leasing.domain.rate.insurance.LeaseTypeInsurance;

public class LeaseTypeInsuranceServiceImpl implements LeaseTypeInsuranceService {
	private static volatile LeaseTypeInsuranceServiceImpl instance;
	private static DaoFactory daoFactory = new DaoFactoryImpl();

	private LeaseTypeInsuranceServiceImpl() {

	}

	public static LeaseTypeInsuranceService getInstance() {
		LeaseTypeInsuranceServiceImpl localInstance = instance;
		if (localInstance == null) {
			synchronized (LeaseTypeInsuranceServiceImpl.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new LeaseTypeInsuranceServiceImpl();
				}
			}
		}
		return localInstance;
	}

	@Override
	public LeaseTypeInsurance findInsuranceByObjectType(PropertyType objectType) {
		if (objectType == null) {
			throw new IllegalArgumentException();
		}
		return daoFactory.getLeaseTypeInsuranceDao().findInsuranceByObjectType(objectType);
	}
}
