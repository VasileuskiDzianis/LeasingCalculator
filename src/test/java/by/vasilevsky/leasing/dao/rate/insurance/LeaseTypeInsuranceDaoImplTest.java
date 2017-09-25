package by.vasilevsky.leasing.dao.rate.insurance;

import static org.junit.Assert.assertEquals;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import by.vasilevsky.leasing.dao.DaoFactory;
import by.vasilevsky.leasing.dao.DataSourceProvider;
import by.vasilevsky.leasing.domain.lease_object.PropertyType;
import by.vasilevsky.leasing.domain.rate.insurance.LeaseTypeInsurance;

public class LeaseTypeInsuranceDaoImplTest {
	private static final String DEFAULT_DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
	private static final String DEFAULT_DB_URL = "jdbc:mysql://localhost:3306/vls";
	private static final String DEFAULT_DB_USER = "dan";
	private static final String DEFAULT_DB_PASSWORD = "pilotpen";
	private static final String DEFAULT_CON_PROP = "useUnicode=yes;characterEncoding=utf8";
	private static final String DEFAULT_CONN_POOL_SIZE = "5";

	private static final PropertyType GIVEN_OBJ_TYPE = PropertyType.TRUCK;
	private static final float EXPECTED_INSURANCE = 0.02f;

	private LeaseTypeInsuranceDao insuranceDao;

	@Before
	public void setUp() throws Exception {
		DaoFactory daoFactory = DaoFactory.getInstance();
		insuranceDao = daoFactory.getLeaseTypeInsuranceDao();
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
	public void findInsuranceByObjectTypeTest() {
		LeaseTypeInsurance gotInsurance = insuranceDao.findInsuranceByObjectType(GIVEN_OBJ_TYPE);

		assertEquals(EXPECTED_INSURANCE, gotInsurance.getInsuranceRate(), 0.0001);
	}
}
