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

public final class ServiceFactoryImpl implements ServiceFactory {
	private static final ServiceFactoryImpl INSTANCE = new ServiceFactoryImpl();

	private final UserService userService;
	private final PaymentsScheduleService paymentsScheduleService;
	private final InsuranceService insuranceService;
	private final LeaseRateService leaseRateService;
	private final LoginService loginService;

	private ServiceFactoryImpl() {
		userService = new UserServiceImpl();
		paymentsScheduleService = new PaymentsScheduleServiceImpl();
		insuranceService = new InsuranceServiceImpl();
		leaseRateService = new LeaseRateServiceImpl();
		loginService = new LoginServiceImpl();
	}

	static ServiceFactory getInstance() {
		
		return INSTANCE;
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
	public LeaseRateService getLeaseRateService() {

		return leaseRateService;
	}
}
