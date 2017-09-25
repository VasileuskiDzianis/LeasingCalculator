package by.vasilevsky.leasing.service.registration;

import by.vasilevsky.leasing.domain.user.User;
import by.vasilevsky.leasing.service.ServiceFactory;
import by.vasilevsky.leasing.service.user.UserService;

public class RegistrationServiceImpl implements RegistrationService {
	private static volatile RegistrationServiceImpl instance;
	private static ServiceFactory serviceFactory = ServiceFactory.getInstance();

	private RegistrationServiceImpl() {

	}

	public static RegistrationService getInstance() {
		RegistrationServiceImpl localInstance = instance;
		if (localInstance == null) {
			synchronized (RegistrationServiceImpl.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new RegistrationServiceImpl();
				}
			}
		}
		return localInstance;
	}

	@Override
	public boolean isLoginExisting(String login) {
		if (login == null) {
			throw new IllegalArgumentException();
		}
		UserService userService = serviceFactory.getUserService();
		User user = userService.findUserByLogin(login);

		return (user == null) ? false : true;
	}

	@Override
	public void registerNewUser(User user) {
		if (user == null) {
			throw new IllegalArgumentException();
		}
		UserService userService = serviceFactory.getUserService();
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
