package by.vasilevsky.leasing.dao.user;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import by.vasilevsky.leasing.dao.DaoFactory;
import by.vasilevsky.leasing.dao.DataSourceProvider;
import by.vasilevsky.leasing.domain.user.User;
import by.vasilevsky.leasing.domain.user.UserDetails;
import by.vasilevsky.leasing.domain.user.UserRole;

public class UserDaoImplTest {
	private static final String DEFAULT_DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
	private static final String DEFAULT_DB_URL = "jdbc:mysql://localhost:3306/vls";
	private static final String DEFAULT_DB_USER = "dan";
	private static final String DEFAULT_DB_PASSWORD = "pilotpen";
	private static final String DEFAULT_CON_PROP = "useUnicode=yes;characterEncoding=utf8";
	private static final String DEFAULT_CONN_POOL_SIZE = "5";
	
	private static final int EXPECTED_USER_ID2 = 2;
	private static final String EXPECTED_USER_LOGIN2 = "4sol@tut.by";
	private static final String EXPECTED_USER_PSW2 = "PDhidatxaFssF7PWkm8y+f6kmMC9JnO7xSPyfXVWOUA=$8O0ZuJx3yK6gNLFeekGwRRW33kqSmhvkIvNZQmEUsQI=";
	private static final UserRole EXPECTED_USER_ROLE2 = UserRole.ADMIN;
	private static final String EXPECTED_USER_FIRST_NAME2 = "Степанов";
	private static final String EXPECTED_USER_LAST_NAME2 = "Джордж";
	private static final int EXPECTED_USER_AGE2 = 33;
	private static final int EXPECTED_USER_DETAILS_ID2 = 2;
	
	private static final int EXPECTED_USER_ID1 = 1;
	private static final String EXPECTED_USER_LOGIN1 = "4denver@mail.ru";
	private static final String EXPECTED_USER_PSW1 = "PDhidatxaFssF7PWkm8y+f6kmMC9JnO7xSPyfXVWOUA=$8O0ZuJx3yK6gNLFeekGwRRW33kqSmhvkIvNZQmEUsQI=";
	private static final UserRole EXPECTED_USER_ROLE1 = UserRole.USER;
	private static final String EXPECTED_USER_FIRST_NAME1 = "Ivanov";
	private static final String EXPECTED_USER_LAST_NAME1 = "Ivan";
	private static final int EXPECTED_USER_AGE1 = 30;
	private static final int EXPECTED_USER_DETAILS_ID1 = 1;
	
	private static final String GIVEN_USER_LOGIN = "google@gmail.com";
	private static final String GIVEN_USER_PSW = "qwerty";
	private static final UserRole GIVEN_USER_ROLE = UserRole.USER;
	private static final String GIVEN_USER_FIRST_NAME = "Some";
	private static final String GIVEN_USER_LAST_NAME = "Name";
	private static final int GIVEN_USER_AGE = 88;
	
	private static final String UPDATED_USER_LOGIN = "yandexe@gmail.com";
	private static final String UPDATED_USER_PSW = "asdasd";
	private static final UserRole UPDATED_USER_ROLE = UserRole.ADMIN;
	private static final String UPDATED_USER_FIRST_NAME = "SomeNew";
	private static final String UPDATED_USER_LAST_NAME = "NameNew";
	private static final int UPDATED_USER_AGE = 99;
	
	User expectedUser1;
	User expectedUser2;
	
	UserDao userDao;
	
	@Before
	public void setUp() throws Exception {
		DaoFactory daoFactory = DaoFactory.getInstance();
		userDao = daoFactory.getUserDao();
		Properties database = new Properties();
		database.put("driver", DEFAULT_DRIVER_CLASS_NAME);
		database.put("url", DEFAULT_DB_URL);
		database.put("user", DEFAULT_DB_USER);
		database.put("password", DEFAULT_DB_PASSWORD);
		database.put("poolSize", DEFAULT_CONN_POOL_SIZE);
		database.put("connectionProperties", DEFAULT_CON_PROP);
		DataSourceProvider.setProperties(database);
		
		
		expectedUser1 = new User();
		expectedUser1.setId(EXPECTED_USER_ID1);
		expectedUser1.setLogin(EXPECTED_USER_LOGIN1);
		expectedUser1.setPassword(EXPECTED_USER_PSW1);
		expectedUser1.setUserRole(EXPECTED_USER_ROLE1);
		UserDetails expectedDetails1 = new UserDetails();
		expectedDetails1.setId(EXPECTED_USER_DETAILS_ID1);
		expectedDetails1.setFirstName(EXPECTED_USER_FIRST_NAME1);
		expectedDetails1.setLastName(EXPECTED_USER_LAST_NAME1);
		expectedDetails1.setAge(EXPECTED_USER_AGE1);
		expectedUser1.setUserDetails(expectedDetails1);
		
		expectedUser2 = new User();
		expectedUser2.setId(EXPECTED_USER_ID2);
		expectedUser2.setLogin(EXPECTED_USER_LOGIN2);
		expectedUser2.setPassword(EXPECTED_USER_PSW2);
		expectedUser2.setUserRole(EXPECTED_USER_ROLE2);
		UserDetails expectedDetails2 = new UserDetails();
		expectedDetails2.setId(EXPECTED_USER_DETAILS_ID2);
		expectedDetails2.setFirstName(EXPECTED_USER_FIRST_NAME2);
		expectedDetails2.setLastName(EXPECTED_USER_LAST_NAME2);
		expectedDetails2.setAge(EXPECTED_USER_AGE2);
		expectedUser2.setUserDetails(expectedDetails2);
	}

	@Test
	public void findUserByLoginTest() {
		User gotUser = userDao.findUserByLogin(EXPECTED_USER_LOGIN2);
		
		assertTrue(expectedUser2.equals(gotUser));
		assertFalse(expectedUser1.equals(gotUser));
	}
	
	@Test
	public void findUserByIdTest() {
		User gotUser = userDao.findUserById(EXPECTED_USER_ID1);
		
		assertTrue(expectedUser1.equals(gotUser));
		assertFalse(expectedUser2.equals(gotUser));
	}
	
	@Test
	public void findAllUsers() {
		List<User> givenUsers = Arrays.asList(expectedUser1, expectedUser2);
		List<User> gotUsers = userDao.findAll();
		
		assertTrue(gotUsers.containsAll(givenUsers));
	}
	
	@Test
	public void saveDeleteUserTest() {
		User givenUser = new User();
		UserDetails givenUserDetails = new UserDetails();
		givenUserDetails.setAge(GIVEN_USER_AGE);
		givenUserDetails.setFirstName(GIVEN_USER_FIRST_NAME);
		givenUserDetails.setLastName(GIVEN_USER_LAST_NAME);
		givenUser.setUserDetails(givenUserDetails);
		givenUser.setLogin(GIVEN_USER_LOGIN);
		givenUser.setPassword(GIVEN_USER_PSW);
		givenUser.setUserRole(GIVEN_USER_ROLE);
		
		userDao.saveUser(givenUser);
		
		User gotUser = userDao.findUserById(givenUser.getId());
		
		assertTrue(givenUser.equals(gotUser));
		
		givenUserDetails.setAge(UPDATED_USER_AGE);
		givenUserDetails.setFirstName(UPDATED_USER_FIRST_NAME);
		givenUserDetails.setLastName(UPDATED_USER_LAST_NAME);
		
		givenUser.setLogin(UPDATED_USER_LOGIN);
		givenUser.setPassword(UPDATED_USER_PSW);
		givenUser.setUserRole(UPDATED_USER_ROLE);
		
		userDao.updateUser(givenUser);
		
		gotUser = userDao.findUserById(givenUser.getId());
		
		assertTrue(givenUser.equals(gotUser));
		
		userDao.deleteUserById(gotUser.getId());
		
		assertNull(userDao.findUserById(gotUser.getId()));
	}
}
