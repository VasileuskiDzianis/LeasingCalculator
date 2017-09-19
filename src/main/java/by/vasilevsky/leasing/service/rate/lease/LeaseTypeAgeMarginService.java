package by.vasilevsky.leasing.service.rate.lease;

import by.vasilevsky.leasing.domain.lease_object.PropertyType;
import by.vasilevsky.leasing.domain.rate.lease.LeaseTypeAgeMargin;

public interface LeaseTypeAgeMarginService {
	
	LeaseTypeAgeMargin findLeaseRateByTypeAndAge(PropertyType objectType, int age);
}
