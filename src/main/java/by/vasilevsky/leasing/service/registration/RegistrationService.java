package by.vasilevsky.leasing.service.registration;

import by.vasilevsky.leasing.domain.user.User;

public interface RegistrationService {
	
	boolean isLoginExisting(String login);
	
	void registerNewUser(User user);
}
