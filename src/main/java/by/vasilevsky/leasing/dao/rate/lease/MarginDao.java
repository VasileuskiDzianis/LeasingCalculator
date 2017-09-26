package by.vasilevsky.leasing.dao.rate.lease;

import by.vasilevsky.leasing.domain.lease_object.PropertyType;
import by.vasilevsky.leasing.domain.rate.lease.Margin;

public interface MarginDao {
	
	Margin findMarginByTypeAndAge(PropertyType propertyType, int age);
}
