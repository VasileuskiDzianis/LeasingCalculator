package by.vasilevsky.leasing.service;

import by.vasilevsky.leasing.service.login.LoginService;
import by.vasilevsky.leasing.service.payments.PaymentsScheduleService;
import by.vasilevsky.leasing.service.rate.InsuranceService;
import by.vasilevsky.leasing.service.rate.LeaseRateService;
import by.vasilevsky.leasing.service.user.UserService;
import by.vasilevsky.leasing.service.validator.UserValidatorService;

public interface ServiceFactory {
	
	UserService getUserService();
	
	PaymentsScheduleService getPaymentsScheduleService();
	
	InsuranceService getInsuranceService();
	
	LeaseRateService getLeaseRateService();

	LoginService getLoginService();
	
	UserValidatorService getUserValidatorService();
	
	static ServiceFactory getInstance() {
		
		return ServiceFactoryImpl.getInstance();
	}
}
