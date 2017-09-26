package by.vasilevsky.leasing.service.rate.lease;

import by.vasilevsky.leasing.domain.currency.Currency;
import by.vasilevsky.leasing.domain.rate.lease.BaseRate;

public interface BaseRateService {
	
	BaseRate findRateByCurrency(Currency currency);
}
