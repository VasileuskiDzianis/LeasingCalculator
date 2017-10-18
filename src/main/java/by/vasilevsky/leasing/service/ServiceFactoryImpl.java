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
import by.vasilevsky.leasing.service.registration.PasswordService;
import by.vasilevsky.leasing.service.registration.PasswordServiceImpl;
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
	private final LoginService loginService;
	private final UserValidatorService userValidatorService;
	private final PasswordService passwordService;
	
	private ServiceFactoryImpl () {
		userService = new UserServiceImpl();
		paymentsScheduleService = new PaymentsScheduleServiceImpl();
		insuranceService = new InsuranceServiceImpl();
		baseRateService = new BaseRateServiceImpl();
		marginService = new MarginServiceImpl();
		loginService = new LoginServiceImpl();
		userValidatorService = new UserValidatorServiceImpl();
		passwordService = new PasswordServiceImpl();
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
	public LoginService getLoginService() {
		
		return loginService;
	}

	@Override
	public UserValidatorService getUserValidatorService() {
		
		return userValidatorService;
	}

	@Override
	public PasswordService getPasswordService() {
		
		return passwordService;
	}
}
