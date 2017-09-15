package by.vasilevsky.leasing.dao.rate.lease;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import by.vasilevsky.leasing.dao.DataSourceProvider;
import by.vasilevsky.leasing.domain.currency.Currency;
import by.vasilevsky.leasing.domain.rate.lease.LeaseCurrencyRate;

public class LeaseCurrencyRateDaoImpl implements LeaseCurrencyRateDao {
	private final static String REQUEST_FIND_LEASE_RATE = "SELECT lcr.currencyId, lcr.currencyRate FROM leaseCurrencyRate AS lcr JOIN currency AS c ON lcr.currencyId=c.id WHERE c.currencyName=?;";

	private DataSource ds;
	private static LeaseCurrencyRateDaoImpl instance;
	
	private LeaseCurrencyRateDaoImpl() {
		
	}
	
	public static LeaseCurrencyRateDaoImpl getInstance() {
		if (instance == null) {
			instance = new LeaseCurrencyRateDaoImpl();
		}
		return instance;
	}

	@Override
	public LeaseCurrencyRate findLeaseRateByCurrency(Currency currency) {
		LeaseCurrencyRate rate = new LeaseCurrencyRate();

		ds = DataSourceProvider.getInstance().getDataSource();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		rate.setCurrency(currency);

		try {
			con = ds.getConnection();
			stmt = con.prepareStatement(REQUEST_FIND_LEASE_RATE);
			stmt.setString(1, currency.toString());
			rs = stmt.executeQuery();
			if (rs.next()) {
				rate.setCurrencyRate(rs.getFloat("currencyRate"));
				rate.setId(rs.getInt("currencyId"));
			}

		} catch (SQLException e) {
			throw new RuntimeException("Finding currency rate exception: ", e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (SQLException e) {
				throw new RuntimeException("Resources closing exception: ", e);
			}
		}
		return rate;
	}
}
