package by.vasilevsky.leasing.service.rate.lease;

import by.vasilevsky.leasing.dao.DaoFactory;
import by.vasilevsky.leasing.domain.currency.Currency;
import by.vasilevsky.leasing.domain.rate.lease.BaseRate;

public class BaseRateServiceImpl implements BaseRateService {

	@Override
	public BaseRate findRateByCurrency(Currency currency) {
		if (currency == null) {
			throw new IllegalArgumentException();
		}
		return DaoFactory.getInstance().getBaseRateDao().findLeaseRateByCurrency(currency);
	}
}
