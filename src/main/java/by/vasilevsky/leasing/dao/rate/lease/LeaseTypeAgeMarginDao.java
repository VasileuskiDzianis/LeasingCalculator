package by.vasilevsky.leasing.dao.rate.lease;

import by.vasilevsky.leasing.domain.lease_object.LeaseObjectType;
import by.vasilevsky.leasing.domain.rate.lease.LeaseTypeAgeMargin;

public interface LeaseTypeAgeMarginDao {
	
	LeaseTypeAgeMargin findLeaseRateByTypeAndAge(LeaseObjectType objectType, int age);
}
