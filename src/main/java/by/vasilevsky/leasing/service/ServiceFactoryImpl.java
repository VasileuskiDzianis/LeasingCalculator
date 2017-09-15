package by.vasilevsky.leasing.service;

import by.vasilevsky.leasing.service.logination.LoginationService;
import by.vasilevsky.leasing.service.logination.LoginationServiceImpl;
import by.vasilevsky.leasing.service.payments.PaymentsScheduleService;
import by.vasilevsky.leasing.service.payments.PaymentsScheduleServiceImpl;
import by.vasilevsky.leasing.service.rate.insurance.LeaseTypeInsuranceService;
import by.vasilevsky.leasing.service.rate.insurance.LeaseTypeInsuranceServiceImpl;
import by.vasilevsky.leasing.service.rate.lease.LeaseCurrencyRateService;
import by.vasilevsky.leasing.service.rate.lease.LeaseCurrencyRateServiceImpl;
import by.vasilevsky.leasing.service.rate.lease.LeaseTypeAgeMarginService;
import by.vasilevsky.leasing.service.rate.lease.LeaseTypeAgeMarginServiceImpl;
import by.vasilevsky.leasing.service.registration.RegistrationService;
import by.vasilevsky.leasing.service.registration.RegistrationServiceImpl;
import by.vasilevsky.leasing.service.user.UserService;
import by.vasilevsky.leasing.service.user.UserServiceImpl;

public class ServiceFactoryImpl implements ServiceFactory {

	@Override
	public UserService getUserService() {
		
		return UserServiceImpl.getInstance();
	}

	@Override
	public PaymentsScheduleService getPaymentsScheduleService() {
		
		return PaymentsScheduleServiceImpl.getInstance();
	}

	@Override
	public LeaseTypeInsuranceService getLeaseTypeInsuranceService() {
		
		return LeaseTypeInsuranceServiceImpl.getInstance();
	}

	@Override
	public LeaseCurrencyRateService getLeaseCurrencyRateService() {
		
		return LeaseCurrencyRateServiceImpl.getInstance();
	}

	@Override
	public LeaseTypeAgeMarginService getLeaseTypeAgeMarginService() {
		
		return LeaseTypeAgeMarginServiceImpl.getInstance();
	}
	
	@Override
	public RegistrationService getRegistrationService() {
		
		return RegistrationServiceImpl.getInstance();
	}

	@Override
	public LoginationService getLoginationService() {
		
		return LoginationServiceImpl.getInstance();
	}
}
