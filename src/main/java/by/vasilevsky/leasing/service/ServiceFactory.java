package by.vasilevsky.leasing.service;

import by.vasilevsky.leasing.service.logination.LoginationService;
import by.vasilevsky.leasing.service.payments.PaymentsScheduleService;
import by.vasilevsky.leasing.service.rate.insurance.LeaseTypeInsuranceService;
import by.vasilevsky.leasing.service.rate.lease.LeaseCurrencyRateService;
import by.vasilevsky.leasing.service.rate.lease.LeaseTypeAgeMarginService;
import by.vasilevsky.leasing.service.registration.RegistrationService;
import by.vasilevsky.leasing.service.user.UserService;

public interface ServiceFactory {
	
	static ServiceFactory instance = ServiceFactoryImpl.getInstance();
	
	UserService getUserService();
	
	PaymentsScheduleService getPaymentsScheduleService();
	
	LeaseTypeInsuranceService getLeaseTypeInsuranceService();
	
	LeaseCurrencyRateService getLeaseCurrencyRateService();
	
	LeaseTypeAgeMarginService getLeaseTypeAgeMarginService();

	RegistrationService getRegistrationService();
	
	LoginationService getLoginationService();
	
	static ServiceFactory getInstance() {
		
		return instance;
	}
}
