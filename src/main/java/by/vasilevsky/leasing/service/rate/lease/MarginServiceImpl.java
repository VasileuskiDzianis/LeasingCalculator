package by.vasilevsky.leasing.service.rate.lease;

import by.vasilevsky.leasing.dao.DaoFactory;
import by.vasilevsky.leasing.dao.rate.lease.LeaseRateDao;
import by.vasilevsky.leasing.domain.lease_object.PropertyType;
import by.vasilevsky.leasing.domain.rate.lease.Margin;

public class MarginServiceImpl implements MarginService {
	private static final int MIN_AGE = 0;

	private final LeaseRateDao leaseRateDao;

	public MarginServiceImpl() {
		leaseRateDao = DaoFactory.getInstance().getLeaseRateDao();
	}

	@Override
	public Margin findMarginByTypeAndAge(PropertyType objectType, int age) {
		if (objectType == null || age < MIN_AGE) {
			throw new IllegalArgumentException();
		}

		return leaseRateDao.findMarginByTypeAndAge(objectType, age);
	}
}
