package by.vasilevsky.leasing.service.rate.impl;

import by.vasilevsky.leasing.dao.DaoFactory;
import by.vasilevsky.leasing.dao.rate.LeaseRateDao;
import by.vasilevsky.leasing.domain.currency.Currency;
import by.vasilevsky.leasing.domain.lease_object.PropertyType;
import by.vasilevsky.leasing.domain.rate.BaseRate;
import by.vasilevsky.leasing.domain.rate.Margin;
import by.vasilevsky.leasing.service.exception.IllegalArgumentServiceException;
import by.vasilevsky.leasing.service.rate.LeaseRateService;

public class LeaseRateServiceImpl implements LeaseRateService {
	private static final int MIN_AGE = 0;
	
	private final LeaseRateDao leaseRateDao;

	public LeaseRateServiceImpl() {
		leaseRateDao = DaoFactory.getInstance().getLeaseRateDao();
	}

	@Override
	public BaseRate findRateByCurrency(Currency currency) {
		if (currency == null) {
			throw new IllegalArgumentServiceException("Currency is NULL");
		}
		
		return leaseRateDao.findLeaseRateByCurrency(currency);
	}

	@Override
	public Margin findMarginByTypeAndAge(PropertyType objectType, int age) {
		if (objectType == null || age < MIN_AGE) {
			throw new IllegalArgumentServiceException();
		}

		return leaseRateDao.findMarginByTypeAndAge(objectType, age);
	}
}
