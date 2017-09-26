package by.vasilevsky.leasing.service;

import by.vasilevsky.leasing.service.logination.LoginationService;
import by.vasilevsky.leasing.service.logination.LoginationServiceImpl;
import by.vasilevsky.leasing.service.payments.PaymentsScheduleService;
import by.vasilevsky.leasing.service.payments.PaymentsScheduleServiceImpl;
import by.vasilevsky.leasing.service.rate.insurance.InsuranceService;
import by.vasilevsky.leasing.service.rate.insurance.InsuranceServiceImpl;
import by.vasilevsky.leasing.service.rate.lease.BaseRateService;
import by.vasilevsky.leasing.service.rate.lease.BaseRateServiceImpl;
import by.vasilevsky.leasing.service.rate.lease.MarginService;
import by.vasilevsky.leasing.service.rate.lease.MarginServiceImpl;
import by.vasilevsky.leasing.service.registration.RegistrationService;
import by.vasilevsky.leasing.service.registration.RegistrationServiceImpl;
import by.vasilevsky.leasing.service.user.UserService;
import by.vasilevsky.leasing.service.user.UserServiceImpl;

public class ServiceFactoryImpl implements ServiceFactory {
	private static volatile ServiceFactoryImpl instance;
	
	private ServiceFactoryImpl () {
		
	}
	
	public static ServiceFactory getInstance() {
		ServiceFactoryImpl localInstance = instance;
		if (localInstance == null) {
			synchronized (ServiceFactoryImpl.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new ServiceFactoryImpl();
				}
			}
		}
		return localInstance;
	}
	
	@Override
	public UserService getUserService() {
		
		return UserServiceImpl.getInstance();
	}

	@Override
	public PaymentsScheduleService getPaymentsScheduleService() {
		
		return PaymentsScheduleServiceImpl.getInstance();
	}

	@Override
	public InsuranceService getInsuranceService() {
		
		return InsuranceServiceImpl.getInstance();
	}

	@Override
	public BaseRateService getBaseRateService() {
		
		return BaseRateServiceImpl.getInstance();
	}

	@Override
	public MarginService getMarginService() {
		
		return MarginServiceImpl.getInstance();
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
