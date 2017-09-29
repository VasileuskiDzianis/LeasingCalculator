package by.vasilevsky.leasing.service.user;

import java.util.List;

import by.vasilevsky.leasing.dao.DaoFactory;
import by.vasilevsky.leasing.domain.user.User;
import by.vasilevsky.leasing.domain.user.UserDetails;

public class UserServiceImpl implements UserService {
	private static final int MIN_AGE = 0;
	
	private final DaoFactory daoFactory = DaoFactory.getInstance();
	
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
	public void updateUserDetails(UserDetails userDetails) {
		if (!isUserDetailsValid(userDetails)) {
			throw new IllegalArgumentException();
		}
		daoFactory.getUserDao().updateUserDetails(userDetails);
	}

	@Override
	public void deleteUser(User user) {
		if (!isUserValid(user)) {
			throw new IllegalArgumentException();
		}
		daoFactory.getUserDao().deleteUser(user);
	}
	
	@Override
	public void deleteUserById(int id) {
		
		daoFactory.getUserDao().deleteUserById(id);
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
				&& isUserDetailsValid(user.getUserDetails());
	}
	private boolean isUserDetailsValid(UserDetails userDetails) {
		
		return userDetails != null
				&& userDetails.getAge() >= MIN_AGE
				&& userDetails.getFirstName() != null
				&& userDetails.getLastName() != null;
	}
}
