package by.vasilevsky.leasing.service;

import by.vasilevsky.leasing.service.login.LoginService;
import by.vasilevsky.leasing.service.login.LoginServiceImpl;
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
import by.vasilevsky.leasing.service.validator.UserValidatorService;
import by.vasilevsky.leasing.service.validator.UserValidatorServiceImpl;

public final class ServiceFactoryImpl implements ServiceFactory {
	private static volatile ServiceFactoryImpl instance;
	
	private final UserService userService;
	private final PaymentsScheduleService paymentsScheduleService;
	private final InsuranceService insuranceService;
	private final BaseRateService baseRateService;
	private final MarginService marginService;
	private final RegistrationService registrationService;
	private final LoginService loginService;
	private final UserValidatorService userValidatorService;
	
	private ServiceFactoryImpl () {
		userService = new UserServiceImpl();
		paymentsScheduleService = new PaymentsScheduleServiceImpl();
		insuranceService = new InsuranceServiceImpl();
		baseRateService = new BaseRateServiceImpl();
		marginService = new MarginServiceImpl();
		registrationService = new RegistrationServiceImpl();
		loginService = new LoginServiceImpl();
		userValidatorService = new UserValidatorServiceImpl();
		
	}
	
	static ServiceFactory getInstance() {
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
		
		return userService;
	}

	@Override
	public PaymentsScheduleService getPaymentsScheduleService() {
		
		return paymentsScheduleService;
	}

	@Override
	public InsuranceService getInsuranceService() {
		
		return insuranceService;
	}

	@Override
	public BaseRateService getBaseRateService() {
		
		return baseRateService;
	}

	@Override
	public MarginService getMarginService() {
		
		return marginService;
	}
	
	@Override
	public RegistrationService getRegistrationService() {
		
		return registrationService;
	}

	@Override
	public LoginService getLoginService() {
		
		return loginService;
	}

	@Override
	public UserValidatorService getUserValidatorService() {
		
		return userValidatorService;
	}
}
