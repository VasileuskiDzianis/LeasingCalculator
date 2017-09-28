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
	
	private final UserDao userDao;
	private final InsuranceDao insuranceDao;
	private final BaseRateDao baseRateDao;
	private final MarginDao marginDao;

	private DaoFactoryImpl() {
		userDao = new UserDaoImpl();
		insuranceDao = new InsuranceDaoImpl();
		baseRateDao = new BaseRateDaoImpl();
		marginDao = new MarginDaoImpl();
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

		return userDao;
	}

	@Override
	public InsuranceDao getInsuranceDao() {

		return insuranceDao;
	}

	@Override
	public BaseRateDao getBaseRateDao() {

		return baseRateDao;
	}

	@Override
	public MarginDao getLeaseTypeAgeMarginDao() {

		return marginDao;
	}
}
