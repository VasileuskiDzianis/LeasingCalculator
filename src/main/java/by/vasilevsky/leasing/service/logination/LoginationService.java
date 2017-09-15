package by.vasilevsky.leasing.service.logination;

import by.vasilevsky.leasing.domain.user.UserRole;

public interface LoginationService {

	UserRole authenticateUser(String login, String password);
}
