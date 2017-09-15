package by.vasilevsky.leasing.service.user;

import by.vasilevsky.leasing.domain.user.User;

public interface UserService {
	
	User findUserById(int id);
	
	User findUserByLogin(String login);
	
	void saveUser(User user);
	
	void updateUser(User user);
	
	void deleteUser(User user);

}
