package by.vasilevsky.leasing.service.user;

import java.util.List;

import by.vasilevsky.leasing.dao.DaoFactory;
import by.vasilevsky.leasing.dao.user.UserDao;
import by.vasilevsky.leasing.domain.user.User;
import by.vasilevsky.leasing.domain.user.UserDetails;
import by.vasilevsky.leasing.util.Validator;

public class UserServiceImpl implements UserService {
	private static final int MIN_AGE = 0;
	
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
		if (!isUserValid(user)) {
			throw new IllegalArgumentException();
		}
		userDao.saveUser(user);
	}

	@Override
	public void updateUser(User user) {
		if (!isUserValid(user)) {
			throw new IllegalArgumentException("User has illegal data");
		}
		userDao.updateUser(user);
	}
	
	@Override
	public void updateUserDetails(UserDetails userDetails) {
		if (!isUserDetailsValid(userDetails)) {
			throw new IllegalArgumentException("User details has illegal data");
		}
		userDao.updateUserDetails(userDetails);
	}

	@Override
	public void deleteUserById(int id) {
		userDao.deleteUserById(id);
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}
	
	public boolean isUserValid(User user) {
		
		return user != null
				&& user.getLogin() != null && Validator.validateLogin(user.getLogin())
				&& user.getPassword() != null
				&& user.getUserRole() != null
				&& isUserDetailsValid(user.getUserDetails());
	}

	public boolean isUserDetailsValid(UserDetails userDetails) {
		
		return userDetails != null
				&& userDetails.getAge() >= MIN_AGE
				&& userDetails.getFirstName() != null && Validator.validateName(userDetails.getFirstName())
				&& userDetails.getLastName() != null && Validator.validateName(userDetails.getLastName());
	}
}
