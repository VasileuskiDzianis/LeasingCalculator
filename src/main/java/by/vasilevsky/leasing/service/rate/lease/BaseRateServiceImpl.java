package by.vasilevsky.leasing.service.rate.lease;

import by.vasilevsky.leasing.dao.DaoFactory;
import by.vasilevsky.leasing.dao.rate.lease.BaseRateDao;
import by.vasilevsky.leasing.domain.currency.Currency;
import by.vasilevsky.leasing.domain.rate.lease.BaseRate;

public class BaseRateServiceImpl implements BaseRateService {
	
	private final BaseRateDao baseRateDao;
	
	public BaseRateServiceImpl() {
		baseRateDao = DaoFactory.getInstance().getBaseRateDao();
	}

	@Override
	public BaseRate findRateByCurrency(Currency currency) {
		if (currency == null) {
			throw new IllegalArgumentException("Currency is NULL");
		}
		
		return baseRateDao.findLeaseRateByCurrency(currency);
	}
}
