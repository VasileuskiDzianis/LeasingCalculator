package by.vasilevsky.leasing.service.validator;

import by.vasilevsky.leasing.domain.user.User;
import by.vasilevsky.leasing.domain.user.UserDetails;

public interface UserValidatorService {

	boolean isUserValid(User user);

	boolean isUserDetailsValid(UserDetails userDetails);
}
