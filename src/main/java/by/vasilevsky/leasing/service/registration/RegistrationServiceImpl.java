package by.vasilevsky.leasing.service.registration;

import by.vasilevsky.leasing.domain.user.User;
import by.vasilevsky.leasing.service.ServiceFactory;
import by.vasilevsky.leasing.service.ServiceFactoryImpl;
import by.vasilevsky.leasing.service.user.UserService;

public class RegistrationServiceImpl implements RegistrationService {
	private static RegistrationServiceImpl instance;
	private static ServiceFactory serviceFactory = new ServiceFactoryImpl();
	
	private RegistrationServiceImpl() {
		
	}
	
	public static RegistrationService getInstance() {
		if (instance == null) {
			instance = new RegistrationServiceImpl();
		}
		return instance;
	}
	
	@Override
	public boolean isLoginExisting(String login) {
		UserService userService = serviceFactory.getUserService();
		User user = userService.findUserByLogin(login);
				
		return (user == null) ? false : true;
	}

	@Override
	public void registerNewUser(User user) {
		UserService userService = serviceFactory.getUserService();
		String encodedPassword;
		try {
			encodedPassword = PasswordEncoderService.getSaltedHash(user.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Password encoding error", e);
		}
		user.setPassword(encodedPassword);
		
		userService.saveUser(user);
	}
}
