package by.vasilevsky.leasing.dao;

import by.vasilevsky.leasing.dao.rate.insurance.InsuranceDao;
import by.vasilevsky.leasing.dao.rate.lease.BaseRateDao;
import by.vasilevsky.leasing.dao.rate.lease.MarginDao;
import by.vasilevsky.leasing.dao.user.UserDao;

public interface DaoFactory {
	
	UserDao getUserDao();
	
	InsuranceDao getInsuranceDao();
	
	BaseRateDao getBaseRateDao();
	
	MarginDao getLeaseTypeAgeMarginDao();
	
	static DaoFactory getInstance() {
		
		return DaoFactoryImpl.getInstance();
	}
}
