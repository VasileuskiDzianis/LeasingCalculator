package by.vasilevsky.leasing.dao;

import by.vasilevsky.leasing.dao.rate.insurance.InsuranceDao;
import by.vasilevsky.leasing.dao.rate.insurance.InsuranceDaoImpl;
import by.vasilevsky.leasing.dao.rate.lease.BaseRateDao;
import by.vasilevsky.leasing.dao.rate.lease.BaseRateDaoImpl;
import by.vasilevsky.leasing.dao.rate.lease.MarginDao;
import by.vasilevsky.leasing.dao.rate.lease.MarginDaoImpl;
import by.vasilevsky.leasing.dao.user.UserDao;
import by.vasilevsky.leasing.dao.user.UserDaoImpl;

public class DaoFactoryImpl implements DaoFactory {
	private static volatile DaoFactoryImpl instance;

	private DaoFactoryImpl() {

	}

	public static DaoFactory getInstance() {
		DaoFactoryImpl localInstance = instance;
		if (localInstance == null) {
			synchronized (DaoFactoryImpl.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new DaoFactoryImpl();
				}
			}
		}
		return localInstance;
	}

	@Override
	public UserDao getUserDao() {

		return UserDaoImpl.getInstance();
	}

	@Override
	public InsuranceDao getInsuranceDao() {

		return InsuranceDaoImpl.getInstance();
	}

	@Override
	public BaseRateDao getBaseRateDao() {

		return BaseRateDaoImpl.getInstance();
	}

	@Override
	public MarginDao getLeaseTypeAgeMarginDao() {

		return MarginDaoImpl.getInstance();
	}
}
