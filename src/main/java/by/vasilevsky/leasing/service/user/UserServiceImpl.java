package by.vasilevsky.leasing.service.user;

import java.util.List;

import by.vasilevsky.leasing.dao.DaoFactory;
import by.vasilevsky.leasing.dao.DaoFactoryImpl;
import by.vasilevsky.leasing.domain.user.User;

public class UserServiceImpl implements UserService {
	private static volatile UserServiceImpl instance;
	private static DaoFactory daoFactory = new DaoFactoryImpl();
	
	private UserServiceImpl() {
		
	}
	
	public static UserService getInstance() {
		UserServiceImpl localInstance = instance;
		if (localInstance == null) {
			synchronized (UserServiceImpl.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new UserServiceImpl();
				}
			}
		}
		return localInstance;
	}
	
	@Override
	public User findUserById(int id) {
		
		return daoFactory.getUserDao().findUserById(id);
	}

	@Override
	public User findUserByLogin(String login) {
		if (login == null) {
			throw new IllegalArgumentException();
		}
		return daoFactory.getUserDao().findUserByLogin(login);
	}

	@Override
	public void saveUser(User user) {
		if (!isUserValid(user)) {
			throw new IllegalArgumentException();
		}
		daoFactory.getUserDao().saveUser(user);
	}

	@Override
	public void updateUser(User user) {
		if (!isUserValid(user)) {
			throw new IllegalArgumentException();
		}
		daoFactory.getUserDao().updateUser(user);
	}

	@Override
	public void deleteUser(User user) {
		if (!isUserValid(user)) {
			throw new IllegalArgumentException();
		}
		daoFactory.getUserDao().deleteUser(user);
	}

	@Override
	public List<User> findAll() {
		
		return daoFactory.getUserDao().findAll();
	}
	
	private boolean isUserValid(User user) {
		
		return user != null
				&& user.getLogin() != null
				&& user.getPassword() != null
				&& user.getUserRole() != null
				&& user.getUserDetails().getAge() >= 0
				&& user.getUserDetails().getFirstName() != null
				&& user.getUserDetails().getLastName() != null;
	}
}
