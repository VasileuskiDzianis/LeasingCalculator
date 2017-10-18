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
	
	@Override
	public boolean isLoginExisting(String login) {
		if (login == null) {
			throw new IllegalArgumentException("Login is NULL");
		}
		User user = ServiceFactory.getInstance().getUserService().findUserByLogin(login);

		return user != null;
	}

	@Override
	public void registerNewUser(User user) {
		if (user == null) {
			throw new IllegalArgumentException("User is NULL");
		}
		String encodedPassword;
		try {
			encodedPassword = ServiceFactory.getInstance().getPasswordService().getSaltedHash(user.getPassword());
		} catch (Exception e) {
			throw new RuntimeException("Password encoding error", e);
		}
		user.setPassword(encodedPassword);
		ServiceFactory.getInstance().getUserService().saveUser(user);
	}
}
