package by.vasilevsky.leasing.service.registration;

import by.vasilevsky.leasing.domain.user.User;
import by.vasilevsky.leasing.service.ServiceFactory;

public class RegistrationServiceImpl implements RegistrationService {
	
	@Override
	public boolean isLoginExisting(String login) {
		if (login == null) {
			throw new IllegalArgumentException();
		}
		User user = ServiceFactory.getInstance().getUserService().findUserByLogin(login);

		return (user == null) ? false : true;
	}

	@Override
	public void registerNewUser(User user) {
		if (user == null) {
			throw new IllegalArgumentException();
		}
		String encodedPassword;
		try {
			encodedPassword = PasswordService.getSaltedHash(user.getPassword());
		} catch (Exception e) {
			throw new RuntimeException("Password encoding error", e);
		}
		user.setPassword(encodedPassword);
		ServiceFactory.getInstance().getUserService().saveUser(user);
	}
}
