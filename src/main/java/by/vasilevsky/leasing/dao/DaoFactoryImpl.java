package by.vasilevsky.leasing.dao;

import by.vasilevsky.leasing.dao.rate.insurance.LeaseTypeInsuranceDao;
import by.vasilevsky.leasing.dao.rate.insurance.LeaseTypeInsuranceDaoImpl;
import by.vasilevsky.leasing.dao.rate.lease.LeaseCurrencyRateDao;
import by.vasilevsky.leasing.dao.rate.lease.LeaseCurrencyRateDaoImpl;
import by.vasilevsky.leasing.dao.rate.lease.LeaseTypeAgeMarginDao;
import by.vasilevsky.leasing.dao.rate.lease.LeaseTypeAgeMarginDaoImpl;
import by.vasilevsky.leasing.dao.user.UserDao;
import by.vasilevsky.leasing.dao.user.UserDaoImpl;

public class DaoFactoryImpl implements DaoFactory {

	@Override
	public UserDao getUserDao() {

		return UserDaoImpl.getInstance();
	}

	@Override
	public LeaseTypeInsuranceDao getLeaseTypeInsuranceDao() {
		
		return LeaseTypeInsuranceDaoImpl.getInstance();
	}

	@Override
	public LeaseCurrencyRateDao getLeaseCurrencyRateDao() {
		
		return LeaseCurrencyRateDaoImpl.getInstance();
	}

	@Override
	public LeaseTypeAgeMarginDao getLeaseTypeAgeMarginDao() {
		
		return LeaseTypeAgeMarginDaoImpl.getInstance();
	}
}
