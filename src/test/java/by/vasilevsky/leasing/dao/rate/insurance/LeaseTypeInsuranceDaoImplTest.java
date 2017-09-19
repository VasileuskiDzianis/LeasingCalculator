package by.vasilevsky.leasing.dao.rate.insurance;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import by.vasilevsky.leasing.dao.DaoFactory;
import by.vasilevsky.leasing.dao.DaoFactoryImpl;
import by.vasilevsky.leasing.domain.lease_object.PropertyType;
import by.vasilevsky.leasing.domain.rate.insurance.LeaseTypeInsurance;

public class LeaseTypeInsuranceDaoImplTest {
	private static final PropertyType GIVEN_OBJ_TYPE = PropertyType.TRUCK;
	private static final float EXPECTED_INSURANCE = 0.02f;

	private LeaseTypeInsuranceDao insuranceDao;

	@Before
	public void setUp() throws Exception {
		DaoFactory daoFactory = new DaoFactoryImpl();
		insuranceDao = daoFactory.getLeaseTypeInsuranceDao();
	}

	@Test
	public void findInsuranceByObjectTypeTest() {
		LeaseTypeInsurance gotInsurance = insuranceDao.findInsuranceByObjectType(GIVEN_OBJ_TYPE);

		assertEquals(EXPECTED_INSURANCE, gotInsurance.getInsuranceRate(), 0.0001);
	}
}
