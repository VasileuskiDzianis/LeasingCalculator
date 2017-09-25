package by.vasilevsky.leasing.dao.rate.lease;

import static org.junit.Assert.assertEquals;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import by.vasilevsky.leasing.dao.DaoFactory;
import by.vasilevsky.leasing.dao.DataSourceProvider;
import by.vasilevsky.leasing.domain.currency.Currency;
import by.vasilevsky.leasing.domain.rate.lease.LeaseCurrencyRate;

public class LeaseCurrencyRateDaoImplTest {
	private static final String DEFAULT_DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
	private static final String DEFAULT_DB_URL = "jdbc:mysql://localhost:3306/vls";
	private static final String DEFAULT_DB_USER = "dan";
	private static final String DEFAULT_DB_PASSWORD = "pilotpen";
	private static final String DEFAULT_CON_PROP = "useUnicode=yes;characterEncoding=utf8";
	private static final String DEFAULT_CONN_POOL_SIZE = "5";
	
	private static final Currency GIVEN_CURRENCY = Currency.USD;
	private static final float EXPECTED_RATE = 0.09f;
	
	private LeaseCurrencyRateDao rateDao;
	
	@Before
	public void setUp() throws Exception {
		DaoFactory daoFactory = DaoFactory.getInstance();
		rateDao = daoFactory.getLeaseCurrencyRateDao();
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
	public void findLeaseRateByCurrencyTest() {
		LeaseCurrencyRate gotRate = rateDao.findLeaseRateByCurrency(GIVEN_CURRENCY);
		
		assertEquals(EXPECTED_RATE, gotRate.getCurrencyRate(), 0.0001);
	}
}
