package by.vasilevsky.leasing.service.rate.lease;

import by.vasilevsky.leasing.dao.DaoFactory;
import by.vasilevsky.leasing.domain.lease_object.PropertyType;
import by.vasilevsky.leasing.domain.rate.lease.Margin;

public class MarginServiceImpl implements MarginService {
	private static volatile MarginServiceImpl instance;
	private static final DaoFactory daoFactory = DaoFactory.getInstance();

	private MarginServiceImpl() {

	}

	public static MarginService getInstance() {
		MarginServiceImpl localInstance = instance;
		if (localInstance == null) {
			synchronized (MarginServiceImpl.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new MarginServiceImpl();
				}
			}
		}
		return instance;
	}

	@Override
	public Margin findMarginByTypeAndAge(PropertyType objectType, int age) {
		if (objectType == null || age < 0) {
			throw new IllegalArgumentException();
		}
		return daoFactory.getLeaseTypeAgeMarginDao().findMarginByTypeAndAge(objectType, age);
	}
}
