package by.vasilevsky.leasing.service.user;

import java.util.List;

import by.vasilevsky.leasing.domain.user.User;
import by.vasilevsky.leasing.domain.user.UserDetails;
import by.vasilevsky.leasing.service.exception.ServiceException;

public interface UserService {
	
	User findUserById(int id) throws ServiceException;
	
	User findUserByLogin(String login) throws ServiceException;
	
	List<User> findAll();
	
	void saveUser(User user) throws ServiceException;
	
	void updateUser(User user) throws ServiceException;
	
	void updateUserDetails(UserDetails userDetails) throws ServiceException;
	
	void deleteUserById(int id) throws ServiceException;
}
