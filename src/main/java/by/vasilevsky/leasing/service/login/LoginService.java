package by.vasilevsky.leasing.service.login;

import by.vasilevsky.leasing.domain.user.User;
import by.vasilevsky.leasing.domain.user.UserRole;

public interface LoginService {

	UserRole authenticateUser(String login, String password);

	boolean isLoginExisting(String login);

	void registerNewUser(User user);
}
