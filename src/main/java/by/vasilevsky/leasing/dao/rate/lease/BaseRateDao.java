package by.vasilevsky.leasing.dao.rate.lease;

import by.vasilevsky.leasing.domain.currency.Currency;
import by.vasilevsky.leasing.domain.rate.lease.BaseRate;

public interface BaseRateDao {

	BaseRate findLeaseRateByCurrency(Currency currency);
}
