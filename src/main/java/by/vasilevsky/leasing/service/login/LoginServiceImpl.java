package by.vasilevsky.leasing.service.login;

import java.security.SecureRandom;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.commons.codec.binary.Base64;
import by.vasilevsky.leasing.domain.user.User;
import by.vasilevsky.leasing.domain.user.UserRole;
import by.vasilevsky.leasing.service.ServiceFactory;
import by.vasilevsky.leasing.service.exception.IllegalArgumentServiceException;
import by.vasilevsky.leasing.service.exception.ServiceException;
import by.vasilevsky.leasing.service.user.UserService;

public class LoginServiceImpl implements LoginService {
	
	// The higher the number of iterations the more expensive computing the hash is
	// for us and also for an attacker.
	private static final int ITERATIONS = 20 * 1000;
	private static final int SALT_LEN = 32;
	private static final int DESIRED_KEY_LEN = 256;

	private static final String SALT_ALGORITM = "SHA1PRNG";
	private static final String PSW_ALGORITM = "PBKDF2WithHmacSHA1";

	private static final String SPLITTER = "$";
	private static final String SPLITTER_PATTERN = "\\$";

	private static final int SPLITED_PARTS_NUMBER = 2;
	private static final int SALT_INDEX = 0;
	private static final int PASSWORD_INDEX = 1;

	@Override
	public UserRole authenticateUser(String login, String password) throws ServiceException {
		if (login == null || password == null) {
			throw new IllegalArgumentServiceException("Login or password is NUll");
		}

		UserService userService = ServiceFactory.getInstance().getUserService();
		User user = null;
		try {
			user = userService.findUserByLogin(login);
		} catch (ServiceException e) {
			throw new ServiceException("User authentication error", e);
		}
		
		if (user == null) {
			return UserRole.ANONYMOUS;
		}

		try {
			if (checkPasswords(password, user.getPassword())) {
				return user.getUserRole();
			}
		} catch (Exception e) {
			throw new ServiceException("Passwords checking error error", e);
		}

		return UserRole.ANONYMOUS;
	}

	@Override
	public boolean isLoginExisting(String login) throws ServiceException {
		if (login == null) {
			throw new IllegalArgumentServiceException("Login is NULL");
		}
		User user = null;
		try {
			user = ServiceFactory.getInstance().getUserService().findUserByLogin(login);
		} catch (ServiceException e) {
			throw new ServiceException("Login existence checking error", e);
		}

		return user != null;
	}

	@Override
	public void registerNewUser(User user) throws ServiceException {
		if (user == null) {
			throw new IllegalArgumentServiceException("User is NULL");
		}
		String encodedPassword;
		try {
			encodedPassword = getSaltedHash(user.getPassword());
		} catch (Exception e) {
			throw new ServiceException("Encoding password error", e);
		}
		user.setPassword(encodedPassword);
		
		try {
			ServiceFactory.getInstance().getUserService().saveUser(user);
		} catch (ServiceException e) {
			throw new ServiceException("User saving error", e);
		}
	}

	private String getSaltedHash(String password) throws Exception {
		byte[] salt = SecureRandom.getInstance(SALT_ALGORITM).generateSeed(SALT_LEN);

		// store the salt with the password
		return Base64.encodeBase64String(salt) + SPLITTER + hash(password, salt);
	}

	private boolean checkPasswords(String password, String stored) throws Exception {
		String[] saltAndPass = stored.split(SPLITTER_PATTERN);
		if (saltAndPass.length != SPLITED_PARTS_NUMBER) {
			throw new ServiceException("The stored password doesn't have the form 'salt$hash'");
		}
		String hashOfInput = hash(password, Base64.decodeBase64(saltAndPass[SALT_INDEX]));

		return hashOfInput.equals(saltAndPass[PASSWORD_INDEX]);
	}

	private String hash(String password, byte[] salt) throws Exception {
		SecretKeyFactory f = SecretKeyFactory.getInstance(PSW_ALGORITM);
		SecretKey key = f.generateSecret(new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, DESIRED_KEY_LEN));

		return Base64.encodeBase64String(key.getEncoded());
	}
}
