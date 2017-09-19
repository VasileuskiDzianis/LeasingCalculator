package by.vasilevsky.leasing.dao.rate.lease;

import by.vasilevsky.leasing.domain.lease_object.PropertyType;
import by.vasilevsky.leasing.domain.rate.lease.LeaseTypeAgeMargin;

public interface LeaseTypeAgeMarginDao {
	
	LeaseTypeAgeMargin findLeaseRateByTypeAndAge(PropertyType objectType, int age);
}
