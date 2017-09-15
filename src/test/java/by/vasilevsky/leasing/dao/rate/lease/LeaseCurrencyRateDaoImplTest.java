package by.vasilevsky.leasing.dao.rate.lease;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import by.vasilevsky.leasing.dao.DaoFactory;
import by.vasilevsky.leasing.dao.DaoFactoryImpl;
import by.vasilevsky.leasing.domain.currency.Currency;
import by.vasilevsky.leasing.domain.rate.lease.LeaseCurrencyRate;

public class LeaseCurrencyRateDaoImplTest {
	private static final Currency GIVEN_CURRENCY = Currency.USD;
	private static final float EXPECTED_RATE = 0.09f;
	
	private LeaseCurrencyRateDao rateDao;
	
	@Before
	public void setUp() throws Exception {
		DaoFactory daoFactory = new DaoFactoryImpl();
		rateDao = daoFactory.getLeaseCurrencyRateDao();
	}

	@Test
	public void findLeaseRateByCurrencyTest() {
		LeaseCurrencyRate gotRate = rateDao.findLeaseRateByCurrency(GIVEN_CURRENCY);
		
		assertEquals(EXPECTED_RATE, gotRate.getCurrencyRate(), 0.0001);
	}
}
