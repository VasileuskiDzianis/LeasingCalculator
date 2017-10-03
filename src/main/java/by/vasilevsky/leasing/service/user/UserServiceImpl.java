package by.vasilevsky.leasing.service.user;

import java.util.List;

import by.vasilevsky.leasing.dao.DaoFactory;
import by.vasilevsky.leasing.domain.user.User;
import by.vasilevsky.leasing.domain.user.UserDetails;
import by.vasilevsky.leasing.service.ServiceFactory;

public class UserServiceImpl implements UserService {
	
	@Override
	public User findUserById(int id) {
		
		return DaoFactory.getInstance().getUserDao().findUserById(id);
	}

	@Override
	public User findUserByLogin(String login) {
		if (login == null) {
			throw new IllegalArgumentException();
		}
		return DaoFactory.getInstance().getUserDao().findUserByLogin(login);
	}

	@Override
	public void saveUser(User user) {
		if (!ServiceFactory.getInstance().getUserValidatorService().isUserValid(user)) {
			throw new IllegalArgumentException();
		}
		DaoFactory.getInstance().getUserDao().saveUser(user);
	}

	@Override
	public void updateUser(User user) {
		if (!ServiceFactory.getInstance().getUserValidatorService().isUserValid(user)) {
			throw new IllegalArgumentException();
		}
		DaoFactory.getInstance().getUserDao().updateUser(user);
	}
	
	@Override
	public void updateUserDetails(UserDetails userDetails) {
		if (!ServiceFactory.getInstance().getUserValidatorService().isUserDetailsValid(userDetails)) {
			throw new IllegalArgumentException();
		}
		DaoFactory.getInstance().getUserDao().updateUserDetails(userDetails);
	}

	@Override
	public void deleteUser(User user) {
		if (!ServiceFactory.getInstance().getUserValidatorService().isUserValid(user)) {
			throw new IllegalArgumentException();
		}
		DaoFactory.getInstance().getUserDao().deleteUser(user);
	}
	
	@Override
	public void deleteUserById(int id) {
		
		DaoFactory.getInstance().getUserDao().deleteUserById(id);
	}

	@Override
	public List<User> findAll() {
		
		return DaoFactory.getInstance().getUserDao().findAll();
	}
}
