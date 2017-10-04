package by.vasilevsky.leasing.service.login;

import by.vasilevsky.leasing.domain.user.User;
import by.vasilevsky.leasing.domain.user.UserRole;
import by.vasilevsky.leasing.service.ServiceFactory;
import by.vasilevsky.leasing.service.user.UserService;

public class LoginServiceImpl implements LoginService {

	@Override
	public UserRole authenticateUser(String login, String password) {
		if (login == null || password == null) {
			throw new IllegalArgumentException("Login or password is NUll");
		}

		UserService userService = ServiceFactory.getInstance().getUserService();
		User user = userService.findUserByLogin(login);
		if (user == null) {
			return UserRole.ANONYMOUS;
		}

		try {
			if (ServiceFactory.getInstance().getPasswordService().check(password, user.getPassword())) {
				return user.getUserRole();
			}
			
		} catch (Exception e) {
			throw new RuntimeException("password checking error", e);
		}
		
		return UserRole.ANONYMOUS;
	}
}
