package by.vasilevsky.leasing.dao.rate.lease;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import by.vasilevsky.leasing.dao.DataSourceProvider;
import by.vasilevsky.leasing.domain.currency.Currency;
import by.vasilevsky.leasing.domain.rate.lease.BaseRate;

public class BaseRateDaoImpl implements BaseRateDao {
	private final static String REQUEST_FIND_LEASE_RATE = "SELECT br.currencyId, br.currencyRate FROM baseRate AS br JOIN currency AS c ON br.currencyId=c.id WHERE c.currencyName=?;";

	private static final String BASE_RATE_DB_MAPPING_ID = "currencyId";
	private static final String BASE_RATE_DB_MAPPING_VALUE = "currencyRate";

	private DataSource ds;

	@Override
	public BaseRate findLeaseRateByCurrency(Currency currency) {
		BaseRate rate = new BaseRate();

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
				rate.setRate(rs.getFloat(BASE_RATE_DB_MAPPING_VALUE));
				rate.setId(rs.getInt(BASE_RATE_DB_MAPPING_ID));
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
