package by.vasilevsky.leasing.dao;

import by.vasilevsky.leasing.dao.rate.InsuranceDao;
import by.vasilevsky.leasing.dao.rate.LeaseRateDao;
import by.vasilevsky.leasing.dao.rate.impl.InsuranceDaoImpl;
import by.vasilevsky.leasing.dao.rate.impl.LeaseRateDaoImpl;
import by.vasilevsky.leasing.dao.user.UserDao;
import by.vasilevsky.leasing.dao.user.UserDaoImpl;

public final class DaoFactoryImpl implements DaoFactory {
	private static volatile DaoFactoryImpl instance;

	private final UserDao userDao;
	private final InsuranceDao insuranceDao;
	private final LeaseRateDao leaseRateDao;

	private DaoFactoryImpl() {
		userDao = new UserDaoImpl();
		insuranceDao = new InsuranceDaoImpl();
		leaseRateDao = new LeaseRateDaoImpl();
	}

	static DaoFactory getInstance() {
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

		return userDao;
	}

	@Override
	public InsuranceDao getInsuranceDao() {

		return insuranceDao;
	}

	@Override
	public LeaseRateDao getLeaseRateDao() {

		return leaseRateDao;
	}
}
