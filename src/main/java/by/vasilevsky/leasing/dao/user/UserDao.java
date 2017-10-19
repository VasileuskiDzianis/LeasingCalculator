package by.vasilevsky.leasing.dao.user;

import java.util.List;

import by.vasilevsky.leasing.dao.DaoException;
import by.vasilevsky.leasing.domain.user.User;
import by.vasilevsky.leasing.domain.user.UserDetails;

public interface UserDao {
	
	void saveUser(User user) throws DaoException;
	
	void updateUser(User user) throws DaoException;
	
	User findUserByLogin(String login) throws DaoException;

	User findUserById(int id) throws DaoException;
	
	List<User> findAll();

	void updateUserDetails(UserDetails userDetails) throws DaoException;

	void deleteUserById(int id) throws DaoException;
}
