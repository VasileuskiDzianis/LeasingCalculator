package by.vasilevsky.leasing.service.rate.lease;

import by.vasilevsky.leasing.dao.DaoFactory;
import by.vasilevsky.leasing.dao.DaoFactoryImpl;
import by.vasilevsky.leasing.domain.lease_object.LeaseObjectType;
import by.vasilevsky.leasing.domain.rate.lease.LeaseTypeAgeMargin;

public class LeaseTypeAgeMarginServiceImpl implements LeaseTypeAgeMarginService {
	private static LeaseTypeAgeMarginServiceImpl instance;
	private static DaoFactory daoFactory = new DaoFactoryImpl();

	private LeaseTypeAgeMarginServiceImpl() {

	}
	
	public static LeaseTypeAgeMarginService getInstance() {
		if (instance == null) {
			instance = new LeaseTypeAgeMarginServiceImpl();
		}
		return instance;
	}
	
	@Override
	public LeaseTypeAgeMargin findLeaseRateByTypeAndAge(LeaseObjectType objectType, int age) {
		
		return daoFactory.getLeaseTypeAgeMarginDao().findLeaseRateByTypeAndAge(objectType, age);
	}
}
