package by.vasilevsky.leasing.service.rate.lease;

import by.vasilevsky.leasing.dao.DaoFactory;
import by.vasilevsky.leasing.dao.rate.LeaseRateDao;
import by.vasilevsky.leasing.domain.currency.Currency;
import by.vasilevsky.leasing.domain.rate.lease.BaseRate;

public class BaseRateServiceImpl implements BaseRateService {
	
	private final LeaseRateDao leaseRateDao;
	
	public BaseRateServiceImpl() {
		leaseRateDao = DaoFactory.getInstance().getLeaseRateDao();
	}

	@Override
	public BaseRate findRateByCurrency(Currency currency) {
		if (currency == null) {
			throw new IllegalArgumentException("Currency is NULL");
		}
		
		return leaseRateDao.findLeaseRateByCurrency(currency);
	}
}
