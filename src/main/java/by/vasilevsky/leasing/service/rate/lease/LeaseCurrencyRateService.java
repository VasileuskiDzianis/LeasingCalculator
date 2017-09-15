package by.vasilevsky.leasing.service.rate.lease;

import by.vasilevsky.leasing.domain.currency.Currency;
import by.vasilevsky.leasing.domain.rate.lease.LeaseCurrencyRate;

public interface LeaseCurrencyRateService {
	
	LeaseCurrencyRate findLeaseRateByCurrency(Currency currency);
}
