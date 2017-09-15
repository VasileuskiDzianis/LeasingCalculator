package by.vasilevsky.leasing.dao;

import by.vasilevsky.leasing.dao.rate.insurance.LeaseTypeInsuranceDao;
import by.vasilevsky.leasing.dao.rate.lease.LeaseCurrencyRateDao;
import by.vasilevsky.leasing.dao.rate.lease.LeaseTypeAgeMarginDao;
import by.vasilevsky.leasing.dao.user.UserDao;

public interface DaoFactory {
	
	UserDao getUserDao();
	
	LeaseTypeInsuranceDao getLeaseTypeInsuranceDao();
	
	LeaseCurrencyRateDao getLeaseCurrencyRateDao();
	
	LeaseTypeAgeMarginDao getLeaseTypeAgeMarginDao();
	
	
}
