package by.vasilevsky.leasing.service.validator;

import java.util.regex.Pattern;

import org.apache.commons.validator.routines.EmailValidator;

public class Validator {
	private static final String PATTERN_PASSWORD = "[a-zA-Z]{6,}";

	public static final boolean validatePassword(String password) {
		if (password == null) {

			return false;
		}
		String trimmedPassword = password.trim();

		return Pattern.matches(PATTERN_PASSWORD, trimmedPassword);
	}

	public static final boolean validateLogin(String login) {
		EmailValidator emailValidator = EmailValidator.getInstance();

		return emailValidator.isValid(login);
	}

	public static final boolean validateName(String login) {
		if (login == null) {

			return false;
		}

		return true;
	}
}
