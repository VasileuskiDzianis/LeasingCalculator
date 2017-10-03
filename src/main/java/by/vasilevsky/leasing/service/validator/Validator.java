package by.vasilevsky.leasing.service.validator;

import java.util.regex.Pattern;

import org.apache.commons.validator.routines.EmailValidator;

public final class Validator {
	private static final String PATTERN_PASSWORD = "[a-zA-Z]{6,}";
	private static final String PATTERN_NAME = "^[a-zA-Zа-яА-Я]+(([',. -][a-zA-Zа-яА-Я ])?[a-zA-Zа-яА-Я]*)*$";
	private static final String PATTERN_NUMBER = "\\d{1,}";
	
	private Validator() {
		
	}
	
	public static final boolean validatePassword(String password) {
		if (password == null) {

			return false;
		}
		return Pattern.matches(PATTERN_PASSWORD, password.trim());
	}

	public static final boolean validateLogin(String login) {
		EmailValidator emailValidator = EmailValidator.getInstance();

		return emailValidator.isValid(login);
	}

	public static final boolean validateName(String input) {
		if (input == null) {

			return false;
		}
		return Pattern.matches(PATTERN_NAME, input.trim());
	}

	public static final boolean validateNumber(String input) {
		if (input == null) {

			return false;
		}
		return Pattern.matches(PATTERN_NUMBER, input.trim());
	}
}
