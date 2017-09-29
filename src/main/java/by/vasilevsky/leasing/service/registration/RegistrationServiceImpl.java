package by.vasilevsky.leasing.service.registration;

import by.vasilevsky.leasing.domain.user.User;
import by.vasilevsky.leasing.service.ServiceFactory;
import by.vasilevsky.leasing.service.user.UserService;

public class RegistrationServiceImpl implements RegistrationService {
	
	@Override
	public boolean isLoginExisting(String login) {
		if (login == null) {
			throw new IllegalArgumentException();
		}
		UserService userService = ServiceFactory.getInstance().getUserService();
		User user = userService.findUserByLogin(login);

		return (user == null) ? false : true;
	}

	@Override
	public void registerNewUser(User user) {
		if (user == null) {
			throw new IllegalArgumentException();
		}
		UserService userService = ServiceFactory.getInstance().getUserService();
		String encodedPassword;
		try {
			encodedPassword = PasswordService.getSaltedHash(user.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Password encoding error", e);
		}
		user.setPassword(encodedPassword);
		userService.saveUser(user);
	}
}
