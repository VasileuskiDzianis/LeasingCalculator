package by.vasilevsky.leasing.dao.rate.lease;

import by.vasilevsky.leasing.domain.currency.Currency;
import by.vasilevsky.leasing.domain.rate.lease.LeaseCurrencyRate;

public interface LeaseCurrencyRateDao {

	LeaseCurrencyRate findLeaseRateByCurrency(Currency currency);
}
