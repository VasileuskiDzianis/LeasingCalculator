package by.vasilevsky.leasing.dao.rate.insurance;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import by.vasilevsky.leasing.domain.lease_object.LeaseObjectType;
import by.vasilevsky.leasing.domain.rate.insurance.LeaseTypeInsurance;

public class LeaseTypeInsuranceDaoImplTest extends LeaseTypeInsuranceDaoImpl {
	private static final LeaseObjectType GIVEN_OBJ_TYPE = LeaseObjectType.TRUCK;
	private static final float EXPECTED_INSURANCE = 0.02f;

	private LeaseTypeInsuranceDao insuranceDao;

	@Before
	public void setUp() throws Exception {
		insuranceDao = new LeaseTypeInsuranceDaoImpl();
	}

	@Test
	public void findInsuranceByObjectTypeTest() {
		LeaseTypeInsurance gotInsurance = insuranceDao.findInsuranceByObjectType(GIVEN_OBJ_TYPE);

		assertEquals(EXPECTED_INSURANCE, gotInsurance.getInsuranceRate(), 0.0001);
	}
}
