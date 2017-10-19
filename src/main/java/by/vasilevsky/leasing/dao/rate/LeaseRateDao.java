package by.vasilevsky.leasing.dao.rate;

import by.vasilevsky.leasing.dao.DaoException;
import by.vasilevsky.leasing.domain.currency.Currency;
import by.vasilevsky.leasing.domain.lease_object.PropertyType;
import by.vasilevsky.leasing.domain.rate.BaseRate;
import by.vasilevsky.leasing.domain.rate.Margin;

public interface LeaseRateDao {

	Margin findMarginByTypeAndAge(PropertyType propertyType, int age) throws DaoException;

	BaseRate findLeaseRateByCurrency(Currency currency) throws DaoException;
}
