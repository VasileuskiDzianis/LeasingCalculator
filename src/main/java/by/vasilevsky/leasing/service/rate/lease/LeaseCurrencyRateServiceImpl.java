package by.vasilevsky.leasing.service.rate.lease;

import by.vasilevsky.leasing.dao.DaoFactory;
import by.vasilevsky.leasing.dao.DaoFactoryImpl;
import by.vasilevsky.leasing.domain.currency.Currency;
import by.vasilevsky.leasing.domain.rate.lease.LeaseCurrencyRate;

public class LeaseCurrencyRateServiceImpl implements LeaseCurrencyRateService {
	private static LeaseCurrencyRateServiceImpl instance;
	private static DaoFactory daoFactory = new DaoFactoryImpl();

	private LeaseCurrencyRateServiceImpl() {

	}
	
	public static LeaseCurrencyRateService getInstance() {
		if (instance == null) {
			instance = new LeaseCurrencyRateServiceImpl();
		}
		return instance;
	}

	@Override
	public LeaseCurrencyRate findLeaseRateByCurrency(Currency currency) {

		return daoFactory.getLeaseCurrencyRateDao().findLeaseRateByCurrency(currency);
	}
}
