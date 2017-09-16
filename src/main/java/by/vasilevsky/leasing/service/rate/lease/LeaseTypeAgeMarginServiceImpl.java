package by.vasilevsky.leasing.service.rate.lease;

import by.vasilevsky.leasing.dao.DaoFactory;
import by.vasilevsky.leasing.dao.DaoFactoryImpl;
import by.vasilevsky.leasing.domain.lease_object.LeaseObjectType;
import by.vasilevsky.leasing.domain.rate.lease.LeaseTypeAgeMargin;

public class LeaseTypeAgeMarginServiceImpl implements LeaseTypeAgeMarginService {
	private static volatile LeaseTypeAgeMarginServiceImpl instance;
	private static DaoFactory daoFactory = new DaoFactoryImpl();

	private LeaseTypeAgeMarginServiceImpl() {

	}

	public static LeaseTypeAgeMarginService getInstance() {
		LeaseTypeAgeMarginServiceImpl localInstance = instance;
		if (localInstance == null) {
			synchronized (LeaseTypeAgeMarginServiceImpl.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new LeaseTypeAgeMarginServiceImpl();
				}
			}
		}
		return instance;
	}

	@Override
	public LeaseTypeAgeMargin findLeaseRateByTypeAndAge(LeaseObjectType objectType, int age) {

		return daoFactory.getLeaseTypeAgeMarginDao().findLeaseRateByTypeAndAge(objectType, age);
	}
}
