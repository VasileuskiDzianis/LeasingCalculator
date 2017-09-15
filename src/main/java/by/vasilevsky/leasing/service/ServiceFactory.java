package by.vasilevsky.leasing.service;

import by.vasilevsky.leasing.service.payments.PaymentsScheduleService;
import by.vasilevsky.leasing.service.rate.insurance.LeaseTypeInsuranceService;
import by.vasilevsky.leasing.service.rate.lease.LeaseCurrencyRateService;
import by.vasilevsky.leasing.service.rate.lease.LeaseTypeAgeMarginService;
import by.vasilevsky.leasing.service.user.UserService;

public interface ServiceFactory {
	
	UserService getUserService();
	
	PaymentsScheduleService getPaymentsScheduleService();
	
	LeaseTypeInsuranceService getLeaseTypeInsuranceService();
	
	LeaseCurrencyRateService getLeaseCurrencyRateService();
	
	LeaseTypeAgeMarginService getLeaseTypeAgeMarginService();
}
