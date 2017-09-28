package by.vasilevsky.leasing.dao.user;

import java.util.List;

import by.vasilevsky.leasing.domain.user.User;
import by.vasilevsky.leasing.domain.user.UserDetails;

public interface UserDao {
	
	void saveUser(User user);
	
	void updateUser(User user);
	
	void deleteUser(User user);
	
	User findUserByLogin(String login);

	User findUserById(int id);
	
	List<User> findAll();

	void updateUserDetails(UserDetails userDetails);

	void deleteUserById(int id);
}
