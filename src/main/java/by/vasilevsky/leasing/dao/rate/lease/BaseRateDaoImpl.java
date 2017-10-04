package by.vasilevsky.leasing.dao.rate.lease;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import by.vasilevsky.leasing.dao.BaseDao;
import by.vasilevsky.leasing.dao.DataSourceProvider;
import by.vasilevsky.leasing.domain.currency.Currency;
import by.vasilevsky.leasing.domain.rate.lease.BaseRate;

public class BaseRateDaoImpl extends BaseDao implements BaseRateDao {
	private final static String REQUEST_FIND_LEASE_RATE = "SELECT br.currencyId, br.currencyRate FROM baseRate AS br JOIN currency AS c ON br.currencyId=c.id WHERE c.currencyName=?;";

	private static final String BASE_RATE_DB_MAPPING_ID = "currencyId";
	private static final String BASE_RATE_DB_MAPPING_VALUE = "currencyRate";

	@Override
	public BaseRate findLeaseRateByCurrency(Currency currency) {
		DataSource ds = DataSourceProvider.getInstance().getDataSource();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		BaseRate rate = new BaseRate();
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
			closeResources(rs, stmt, con);
		}
		
		return rate;
	}
}
