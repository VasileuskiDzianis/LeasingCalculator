package by.vasilevsky.leasing.service.user;

import by.vasilevsky.leasing.dao.DaoFactory;
import by.vasilevsky.leasing.dao.DaoFactoryImpl;
import by.vasilevsky.leasing.domain.user.User;

public class UserServiceImpl implements UserService {
	private static UserServiceImpl instance;
	private static DaoFactory daoFactory = new DaoFactoryImpl();
	
	private UserServiceImpl() {
		
	}
	
	public static UserService getInstance() {
		if (instance == null) {
			instance = new UserServiceImpl();
		}
		return instance;
	}
	
	@Override
	public User findUserById(int id) {

		return daoFactory.getUserDao().findUserById(id);
	}

	@Override
	public User findUserByLogin(String login) {
		
		return daoFactory.getUserDao().findUserByLogin(login);
	}

	@Override
	public void saveUser(User user) {
		daoFactory.getUserDao().saveUser(user);
	}

	@Override
	public void updateUser(User user) {
		daoFactory.getUserDao().updateUser(user);
	}

	@Override
	public void deleteUser(User user) {
		daoFactory.getUserDao().deleteUser(user);
	}
}
