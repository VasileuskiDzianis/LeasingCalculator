package by.vasilevsky.leasing.dao.rate.lease;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import by.vasilevsky.leasing.domain.lease_object.LeaseObjectType;
import by.vasilevsky.leasing.domain.rate.lease.LeaseTypeAgeMargin;

public class LeaseTypeAgeMarginDaoImplTest extends LeaseTypeAgeMarginDaoImpl {
	private static final LeaseObjectType GIVEN_OBJECT_TYPE = LeaseObjectType.LORRY;
	private static final int GIVEN_OBJECT_AGE = 2;
	private static final float EXPECTED_RATE = 0.023f;
	
	private LeaseTypeAgeMarginDao marginDao;
	
	@Before
	public void setUp() {
		marginDao = new LeaseTypeAgeMarginDaoImpl();
	}
	
	@Test
	public void findLeaseRateByTypeAndAgeTest() {
		LeaseTypeAgeMargin margin = marginDao.findLeaseRateByTypeAndAge(GIVEN_OBJECT_TYPE, GIVEN_OBJECT_AGE);
		
		assertEquals(EXPECTED_RATE, margin.getMargin(), 0.00001);
	}
}
