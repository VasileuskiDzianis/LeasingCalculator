package by.vasilevsky.leasing.service.rate.lease;

import by.vasilevsky.leasing.dao.DaoFactory;
import by.vasilevsky.leasing.domain.currency.Currency;
import by.vasilevsky.leasing.domain.rate.lease.BaseRate;

public class BaseRateServiceImpl implements BaseRateService {
	private static volatile BaseRateServiceImpl instance;
	private static final DaoFactory daoFactory = DaoFactory.getInstance();

	private BaseRateServiceImpl() {

	}

	public static BaseRateService getInstance() {
		BaseRateServiceImpl localInstance = instance;
		if (localInstance == null) {
			synchronized (BaseRateServiceImpl.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new BaseRateServiceImpl();
				}
			}
		}
		return instance;
	}

	@Override
	public BaseRate findRateByCurrency(Currency currency) {
		if (currency == null) {
			throw new IllegalArgumentException();
		}

		return daoFactory.getBaseRateDao().findLeaseRateByCurrency(currency);
	}
}
