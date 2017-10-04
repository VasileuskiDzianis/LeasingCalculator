package by.vasilevsky.leasing.service.registration;

public interface PasswordService {

	String getSaltedHash(String password) throws Exception;

	boolean check(String password, String stored) throws Exception;
}