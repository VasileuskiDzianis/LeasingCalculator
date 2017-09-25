package by.vasilevsky.leasing.service.logination;

import by.vasilevsky.leasing.domain.user.User;
import by.vasilevsky.leasing.domain.user.UserRole;
import by.vasilevsky.leasing.service.ServiceFactory;
import by.vasilevsky.leasing.service.registration.PasswordService;
import by.vasilevsky.leasing.service.user.UserService;

public class LoginationServiceImpl implements LoginationService {
	private static volatile LoginationServiceImpl instance;
	
	private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private final UserService userService = serviceFactory.getUserService();
	
	private LoginationServiceImpl() {
		
	}
	
	public static LoginationService getInstance() {
		LoginationServiceImpl localInstance = instance;
		if (localInstance == null) {
			synchronized (LoginationServiceImpl.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new LoginationServiceImpl();
				}
			}
		}
		return localInstance;
	}

	@Override
	public UserRole authenticateUser(String login, String password) {
		if (login == null || password == null) {
			throw new IllegalArgumentException();
		}
		
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
