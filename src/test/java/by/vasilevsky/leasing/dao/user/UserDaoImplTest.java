package by.vasilevsky.leasing.dao.user;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import by.vasilevsky.leasing.dao.DaoFactory;
import by.vasilevsky.leasing.dao.DaoFactoryImpl;
import by.vasilevsky.leasing.domain.user.User;
import by.vasilevsky.leasing.domain.user.UserDetails;
import by.vasilevsky.leasing.domain.user.UserRole;

public class UserDaoImplTest {
	private static final int EXPECTED_USER_ID = 2;
	private static final String EXPECTED_USER_LOGIN = "4sol@tut.by";
	private static final String EXPECTED_USER_PSW = "PDhidatxaFssF7PWkm8y+f6kmMC9JnO7xSPyfXVWOUA=$8O0ZuJx3yK6gNLFeekGwRRW33kqSmhvkIvNZQmEUsQI=";
	private static final UserRole EXPECTED_USER_ROLE = UserRole.ADMIN;
	private static final String EXPECTED_USER_FIRST_NAME = "Степанов";
	private static final String EXPECTED_USER_LAST_NAME = "Джордж";
	private static final int EXPECTED_USER_AGE = 33;
	private static final int EXPECTED_USER_DETAILS_ID = 2;
	
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
	
	UserDao userDao;
	
	@Before
	public void setUp() throws Exception {
		DaoFactory daoFactory = new DaoFactoryImpl();
		userDao = daoFactory.getUserDao();
	}

	@Test
	public void findUserByLoginTest() {
		User gotUser = userDao.findUserByLogin(EXPECTED_USER_LOGIN);
		
		assertEquals(EXPECTED_USER_ID, gotUser.getId());
		assertEquals(EXPECTED_USER_LOGIN, gotUser.getLogin());
		assertEquals(EXPECTED_USER_PSW, gotUser.getPassword());
		assertEquals(EXPECTED_USER_ROLE, gotUser.getUserRole());
		assertEquals(EXPECTED_USER_FIRST_NAME, gotUser.getUserDetails().getFirstName());
		assertEquals(EXPECTED_USER_LAST_NAME, gotUser.getUserDetails().getLastName());
		assertEquals(EXPECTED_USER_AGE, gotUser.getUserDetails().getAge());
		assertEquals(EXPECTED_USER_DETAILS_ID, gotUser.getUserDetails().getId());
	}
	
	@Test
	public void findUserByIdTest() {
		User gotUser = userDao.findUserById(EXPECTED_USER_ID);
		
		assertEquals(EXPECTED_USER_ID, gotUser.getId());
		assertEquals(EXPECTED_USER_LOGIN, gotUser.getLogin());
		assertEquals(EXPECTED_USER_PSW, gotUser.getPassword());
		assertEquals(EXPECTED_USER_ROLE, gotUser.getUserRole());
		assertEquals(EXPECTED_USER_FIRST_NAME, gotUser.getUserDetails().getFirstName());
		assertEquals(EXPECTED_USER_LAST_NAME, gotUser.getUserDetails().getLastName());
		assertEquals(EXPECTED_USER_AGE, gotUser.getUserDetails().getAge());
		assertEquals(EXPECTED_USER_DETAILS_ID, gotUser.getUserDetails().getId());
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
		
		userDao.deleteUser(gotUser);
		
		assertNull(userDao.findUserById(gotUser.getId()));
	}
}
