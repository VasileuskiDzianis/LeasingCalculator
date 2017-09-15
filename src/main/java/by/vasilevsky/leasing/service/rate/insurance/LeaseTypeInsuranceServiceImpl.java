package by.vasilevsky.leasing.service.rate.insurance;

import by.vasilevsky.leasing.dao.DaoFactory;
import by.vasilevsky.leasing.dao.DaoFactoryImpl;
import by.vasilevsky.leasing.domain.lease_object.LeaseObjectType;
import by.vasilevsky.leasing.domain.rate.insurance.LeaseTypeInsurance;

public class LeaseTypeInsuranceServiceImpl implements LeaseTypeInsuranceService {
	private static LeaseTypeInsuranceServiceImpl instance;
	private static DaoFactory daoFactory = new DaoFactoryImpl();
	
	private LeaseTypeInsuranceServiceImpl() {
		
	}
	
	public static LeaseTypeInsuranceService getInstance() {
		if (instance == null) {
			instance = new LeaseTypeInsuranceServiceImpl();
		}
		return instance;
	}
	
	@Override
	public LeaseTypeInsurance findInsuranceByObjectType(LeaseObjectType objectType) {
		
		return daoFactory.getLeaseTypeInsuranceDao().findInsuranceByObjectType(objectType);
	}
}
