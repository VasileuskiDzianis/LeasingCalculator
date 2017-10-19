package by.vasilevsky.leasing.service.login;

import by.vasilevsky.leasing.domain.user.User;
import by.vasilevsky.leasing.domain.user.UserRole;
import by.vasilevsky.leasing.service.exception.ServiceException;

public interface LoginService {

	UserRole authenticateUser(String login, String password) throws ServiceException;

	boolean isLoginExisting(String login) throws ServiceException;

	void registerNewUser(User user) throws ServiceException;
}
