package by.vasilevsky.leasing.service.user;

import java.util.List;

import by.vasilevsky.leasing.dao.DaoFactory;
import by.vasilevsky.leasing.dao.user.UserDao;
import by.vasilevsky.leasing.domain.user.User;
import by.vasilevsky.leasing.domain.user.UserDetails;
import by.vasilevsky.leasing.service.ServiceFactory;

public class UserServiceImpl implements UserService {
	private final UserDao userDao;
	
	public UserServiceImpl() {
		userDao = DaoFactory.getInstance().getUserDao();
	}
	
	@Override
	public User findUserById(int id) {
		
		return userDao.findUserById(id);
	}

	@Override
	public User findUserByLogin(String login) {
		if (login == null) {
			throw new IllegalArgumentException("Login is NULL");
		}
		
		return userDao.findUserByLogin(login);
	}

	@Override
	public void saveUser(User user) {
		if (!ServiceFactory.getInstance().getUserValidatorService().isUserValid(user)) {
			throw new IllegalArgumentException();
		}
		userDao.saveUser(user);
	}

	@Override
	public void updateUser(User user) {
		if (!ServiceFactory.getInstance().getUserValidatorService().isUserValid(user)) {
			throw new IllegalArgumentException("User has illegal data");
		}
		userDao.updateUser(user);
	}
	
	@Override
	public void updateUserDetails(UserDetails userDetails) {
		if (!ServiceFactory.getInstance().getUserValidatorService().isUserDetailsValid(userDetails)) {
			throw new IllegalArgumentException("User details has illegal data");
		}
		userDao.updateUserDetails(userDetails);
	}

	@Override
	public void deleteUser(User user) {
		if (!ServiceFactory.getInstance().getUserValidatorService().isUserValid(user)) {
			throw new IllegalArgumentException("User has illegal data");
		}
		userDao.deleteUser(user);
	}
	
	@Override
	public void deleteUserById(int id) {
		userDao.deleteUserById(id);
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}
}
