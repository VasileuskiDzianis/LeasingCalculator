package by.vasilevsky.leasing.dao.user;

import by.vasilevsky.leasing.domain.user.User;

public interface UserDao {
	
	void saveUser(User user);
	
	void updateUser(User user);
	
	void deleteUser(User user);
	
	User findUserByLogin(String login);

	User findUserById(int id);

}
