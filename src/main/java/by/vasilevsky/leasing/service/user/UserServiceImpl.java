package by.vasilevsky.leasing.service.user;

import java.util.List;

import by.vasilevsky.leasing.dao.DaoException;
import by.vasilevsky.leasing.dao.DaoFactory;
import by.vasilevsky.leasing.dao.user.UserDao;
import by.vasilevsky.leasing.domain.user.User;
import by.vasilevsky.leasing.domain.user.UserDetails;
import by.vasilevsky.leasing.service.exception.IllegalArgumentServiceException;
import by.vasilevsky.leasing.service.exception.ServiceException;
import by.vasilevsky.leasing.util.Validator;

public class UserServiceImpl implements UserService {
	private static final int MIN_AGE = 0;
	
	private final UserDao userDao;
	
	public UserServiceImpl() {
		userDao = DaoFactory.getInstance().getUserDao();
	}
	
	@Override
	public User findUserById(int id) throws ServiceException {
		
		try {
			return userDao.findUserById(id);
		} catch (DaoException e) {
			throw new ServiceException("Finding user by id error", e);
		}
	}

	@Override
	public User findUserByLogin(String login) throws ServiceException {
		if (login == null) {
			throw new IllegalArgumentServiceException("Login is NULL");
		}
		User user = null;
		try {
			user = userDao.findUserByLogin(login);
		} catch (DaoException e) {
			throw new ServiceException("Finding user by login error", e);
		}
		return user;
	}

	@Override
	public void saveUser(User user) throws ServiceException {
		if (!isUserValid(user)) {
			throw new IllegalArgumentServiceException();
		}
		try {
			userDao.saveUser(user);
		} catch (DaoException e) {
			throw new ServiceException("Saving user error", e);
		}
	}

	@Override
	public void updateUser(User user) throws ServiceException {
		if (!isUserValid(user)) {
			throw new IllegalArgumentServiceException("User has illegal data");
		}
		try {
			userDao.updateUser(user);
		} catch (DaoException e) {
			throw new ServiceException("User updating error", e);
		}
	}
	
	@Override
	public void updateUserDetails(UserDetails userDetails) throws ServiceException {
		if (!isUserDetailsValid(userDetails)) {
			throw new IllegalArgumentServiceException("User details has illegal data");
		}
		try {
			userDao.updateUserDetails(userDetails);
		} catch (DaoException e) {
			throw new ServiceException("Updating user details error", e);
		}
	}

	@Override
	public void deleteUserById(int id) throws ServiceException {
		try {
			userDao.deleteUserById(id);
		} catch (DaoException e) {
			throw new ServiceException("User deleting exception", e);
		}
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
