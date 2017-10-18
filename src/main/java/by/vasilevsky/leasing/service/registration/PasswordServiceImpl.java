package by.vasilevsky.leasing.service.registration;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import org.apache.commons.codec.binary.Base64;

public class PasswordServiceImpl implements PasswordService {
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
	public String getSaltedHash(String password) throws Exception {
		if (password == null) {
			throw new IllegalArgumentException("Password is NULL");
		}
		byte[] salt = SecureRandom.getInstance(SALT_ALGORITM).generateSeed(SALT_LEN);

		// store the salt with the password
		return Base64.encodeBase64String(salt) + SPLITTER + hash(password, salt);
	}

	@Override
	public boolean check(String password, String stored) throws Exception {
		if (password == null || stored == null) {
			throw new IllegalArgumentException("Password is NULL");
		}
		String[] saltAndPass = stored.split(SPLITTER_PATTERN);
		if (saltAndPass.length != SPLITED_PARTS_NUMBER) {
			throw new IllegalStateException("The stored password have the form 'salt$hash'");
		}
		String hashOfInput = hash(password, Base64.decodeBase64(saltAndPass[SALT_INDEX]));

		return hashOfInput.equals(saltAndPass[PASSWORD_INDEX]);
	}

	// using PBKDF2 from Sun, an alternative is https://github.com/wg/scrypt
	// cf. http://www.unlimitednovelty.com/2012/03/dont-use-bcrypt.html
	private String hash(String password, byte[] salt) throws Exception {
		if (password == null || password.length() == 0) {
			throw new IllegalArgumentException("Empty passwords are not supported.");
		}
		SecretKeyFactory f = SecretKeyFactory.getInstance(PSW_ALGORITM);
		SecretKey key = f.generateSecret(new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, DESIRED_KEY_LEN));

		return Base64.encodeBase64String(key.getEncoded());
	}
}