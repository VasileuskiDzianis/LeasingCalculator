package by.vasilevsky.leasing.service.rate;

import by.vasilevsky.leasing.domain.currency.Currency;
import by.vasilevsky.leasing.domain.lease_object.PropertyType;
import by.vasilevsky.leasing.domain.rate.BaseRate;
import by.vasilevsky.leasing.domain.rate.Margin;

public interface LeaseRateService {
	
	BaseRate findRateByCurrency(Currency currency);
	
	Margin findMarginByTypeAndAge(PropertyType objectType, int age);
}
