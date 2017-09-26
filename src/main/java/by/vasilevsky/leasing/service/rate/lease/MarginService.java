package by.vasilevsky.leasing.service.rate.lease;

import by.vasilevsky.leasing.domain.lease_object.PropertyType;
import by.vasilevsky.leasing.domain.rate.lease.Margin;

public interface MarginService {
	
	Margin findMarginByTypeAndAge(PropertyType objectType, int age);
}
