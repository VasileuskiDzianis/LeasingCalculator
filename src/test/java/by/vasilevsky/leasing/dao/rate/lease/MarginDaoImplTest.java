package by.vasilevsky.leasing.dao.rate.lease;

import static org.junit.Assert.*;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import by.vasilevsky.leasing.dao.DaoFactory;
import by.vasilevsky.leasing.dao.DataSourceProvider;
import by.vasilevsky.leasing.domain.lease_object.PropertyType;
import by.vasilevsky.leasing.domain.rate.lease.Margin;

public class MarginDaoImplTest {
	private static final String DEFAULT_DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
	private static final String DEFAULT_DB_URL = "jdbc:mysql://localhost:3306/vls";
	private static final String DEFAULT_DB_USER = "dan";
	private static final String DEFAULT_DB_PASSWORD = "pilotpen";
	private static final String DEFAULT_CON_PROP = "useUnicode=yes;characterEncoding=utf8";
	private static final String DEFAULT_CONN_POOL_SIZE = "5";

	private static final PropertyType GIVEN_OBJECT_TYPE = PropertyType.LORRY;
	private static final int GIVEN_OBJECT_AGE = 2;
	private static final float EXPECTED_RATE = 0.023f;

	private MarginDao marginDao;

	@Before
	public void setUp() {
		DaoFactory daoFactory = DaoFactory.getInstance();
		marginDao = daoFactory.getMarginDao();

		Properties database = new Properties();

		database.put("driver", DEFAULT_DRIVER_CLASS_NAME);
		database.put("url", DEFAULT_DB_URL);
		database.put("user", DEFAULT_DB_USER);
		database.put("password", DEFAULT_DB_PASSWORD);
		database.put("poolSize", DEFAULT_CONN_POOL_SIZE);
		database.put("connectionProperties", DEFAULT_CON_PROP);

		DataSourceProvider.setProperties(database);
	}

	@Test
	public void findLeaseRateByTypeAndAgeTest() {
		Margin margin = marginDao.findMarginByTypeAndAge(GIVEN_OBJECT_TYPE, GIVEN_OBJECT_AGE);

		assertEquals(EXPECTED_RATE, margin.getMargin(), 0.00001);
	}
}
