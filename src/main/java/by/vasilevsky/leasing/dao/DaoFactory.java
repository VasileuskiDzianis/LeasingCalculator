package by.vasilevsky.leasing.dao;

import by.vasilevsky.leasing.dao.rate.InsuranceDao;
import by.vasilevsky.leasing.dao.rate.LeaseRateDao;
import by.vasilevsky.leasing.dao.user.UserDao;

public interface DaoFactory {
	
	UserDao getUserDao();
	
	InsuranceDao getInsuranceDao();
	
	LeaseRateDao getLeaseRateDao();

	static DaoFactory getInstance() {
		
		return DaoFactoryImpl.getInstance();
	}
}
