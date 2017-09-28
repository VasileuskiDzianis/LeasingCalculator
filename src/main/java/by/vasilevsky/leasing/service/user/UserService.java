package by.vasilevsky.leasing.service.user;

import java.util.List;

import by.vasilevsky.leasing.domain.user.User;
import by.vasilevsky.leasing.domain.user.UserDetails;

public interface UserService {
	
	User findUserById(int id);
	
	User findUserByLogin(String login);
	
	List<User> findAll();
	
	void saveUser(User user);
	
	void updateUser(User user);
	
	void updateUserDetails(UserDetails userDetails);
	
	void deleteUser(User user);

	void deleteUserById(int id);
}
