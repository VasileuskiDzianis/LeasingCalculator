package by.vasilevsky.leasing.service;

import by.vasilevsky.leasing.service.login.LoginService;
import by.vasilevsky.leasing.service.login.LoginServiceImpl;
import by.vasilevsky.leasing.service.payments.PaymentsScheduleService;
import by.vasilevsky.leasing.service.payments.PaymentsScheduleServiceImpl;
import by.vasilevsky.leasing.service.rate.InsuranceService;
import by.vasilevsky.leasing.service.rate.LeaseRateService;
import by.vasilevsky.leasing.service.rate.impl.InsuranceServiceImpl;
import by.vasilevsky.leasing.service.rate.impl.LeaseRateServiceImpl;
import by.vasilevsky.leasing.service.user.UserService;
import by.vasilevsky.leasing.service.user.UserServiceImpl;
import by.vasilevsky.leasing.service.validator.UserValidatorService;
import by.vasilevsky.leasing.service.validator.UserValidatorServiceImpl;

public final class ServiceFactoryImpl implements ServiceFactory {
	private static volatile ServiceFactoryImpl instance;

	private final UserService userService;
	private final PaymentsScheduleService paymentsScheduleService;
	private final InsuranceService insuranceService;
	private final LeaseRateService leaseRateService;
	private final LoginService loginService;
	private final UserValidatorService userValidatorService;

	private ServiceFactoryImpl() {
		userService = new UserServiceImpl();
		paymentsScheduleService = new PaymentsScheduleServiceImpl();
		insuranceService = new InsuranceServiceImpl();
		leaseRateService = new LeaseRateServiceImpl();
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
	public LoginService getLoginService() {

		return loginService;
	}

	@Override
	public UserValidatorService getUserValidatorService() {

		return userValidatorService;
	}

	@Override
	public LeaseRateService getLeaseRateService() {

		return leaseRateService;
	}
}
