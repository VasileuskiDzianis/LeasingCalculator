package by.vasilevsky.leasing.service.validator;

import by.vasilevsky.leasing.domain.user.User;
import by.vasilevsky.leasing.domain.user.UserDetails;

public class UserValidatorServiceImpl implements UserValidatorService {
	private static final int MIN_AGE = 0;
	
	@Override
	public boolean isUserValid(User user) {
		
		return user != null
				&& user.getLogin() != null && Validator.validateLogin(user.getLogin())
				&& user.getPassword() != null
				&& user.getUserRole() != null
				&& isUserDetailsValid(user.getUserDetails());
	}

	@Override
	public boolean isUserDetailsValid(UserDetails userDetails) {
		
		return userDetails != null
				&& userDetails.getAge() >= MIN_AGE
				&& userDetails.getFirstName() != null && Validator.validateName(userDetails.getFirstName())
				&& userDetails.getLastName() != null && Validator.validateName(userDetails.getLastName());
	}
}
