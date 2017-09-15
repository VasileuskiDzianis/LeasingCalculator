package by.vasilevsky.leasing.service.rate.lease;

import by.vasilevsky.leasing.domain.lease_object.LeaseObjectType;
import by.vasilevsky.leasing.domain.rate.lease.LeaseTypeAgeMargin;

public interface LeaseTypeAgeMarginService {
	
	LeaseTypeAgeMargin findLeaseRateByTypeAndAge(LeaseObjectType objectType, int age);
}
