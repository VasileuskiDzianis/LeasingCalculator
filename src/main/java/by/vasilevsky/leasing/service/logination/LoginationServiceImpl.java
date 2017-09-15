package by.vasilevsky.leasing.service.logination;

import by.vasilevsky.leasing.domain.user.User;
import by.vasilevsky.leasing.domain.user.UserRole;
import by.vasilevsky.leasing.service.ServiceFactory;
import by.vasilevsky.leasing.service.ServiceFactoryImpl;
import by.vasilevsky.leasing.service.registration.PasswordService;
import by.vasilevsky.leasing.service.user.UserService;

public class LoginationServiceImpl implements LoginationService {
	private ServiceFactory serviceFactory = new ServiceFactoryImpl();
	private UserService userService = serviceFactory.getUserService();
	
	private static LoginationServiceImpl instance;
	
	private LoginationServiceImpl() {
		
	}
	
	public static LoginationService getInstance() {
		if (instance == null) {
			instance = new LoginationServiceImpl();
		}
		return instance;
	}

	@Override
	public UserRole authenticateUser(String login, String password) {
		User user = userService.findUserByLogin(login);
		if (user == null) {

			return UserRole.ANONYMOUS;
		}

		try {
			if (PasswordService.check(password, user.getPassword())) {

				return user.getUserRole();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("password checking error", e);
		}
		return UserRole.ANONYMOUS;
	}
}
