package by.vasilevsky.leasing.dao.rate.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.vasilevsky.leasing.dao.BaseDao;
import by.vasilevsky.leasing.dao.rate.LeaseRateDao;
import by.vasilevsky.leasing.domain.currency.Currency;
import by.vasilevsky.leasing.domain.lease_object.PropertyType;
import by.vasilevsky.leasing.domain.rate.BaseRate;
import by.vasilevsky.leasing.domain.rate.Margin;

public class LeaseRateDaoImpl extends BaseDao implements LeaseRateDao {
	private final static String REQUEST_FIND_LEASE_RATE = "SELECT m.id, m.margin FROM margin AS m JOIN leaseObjectType AS lot ON m.objectTypeId=lot.id WHERE m.objectAge=? AND lot.objectType=?;";
	private final static String REQUEST_FIND_BASE_RATE = "SELECT br.currencyId, br.currencyRate FROM baseRate AS br JOIN currency AS c ON br.currencyId=c.id WHERE c.currencyName=?;";

	private static final String MARGIN_DB_MAPPING_ID = "id";
	private static final String MARGIN_DB_MAPPING_VALUE = "margin";
	private static final String BASE_RATE_DB_MAPPING_ID = "currencyId";
	private static final String BASE_RATE_DB_MAPPING_VALUE = "currencyRate";

	@Override
	public Margin findMarginByTypeAndAge(PropertyType propertyType, int age) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		Margin margin = new Margin();
		margin.setPropertyAge(age);
		margin.setPropertyType(propertyType);
		try {
			con = dataSource.getConnection();
			stmt = con.prepareStatement(REQUEST_FIND_LEASE_RATE);
			stmt.setInt(1, age);
			stmt.setString(2, propertyType.toString());
			rs = stmt.executeQuery();
			if (rs.next()) {
				margin.setMargin(rs.getFloat(MARGIN_DB_MAPPING_VALUE));
				margin.setId(rs.getInt(MARGIN_DB_MAPPING_ID));
			}
		} catch (SQLException e) {
			throw new RuntimeException("Finding margin exception: ", e);
		} finally {
			closeResources(rs, stmt, con);
		}

		return margin;
	}

	@Override
	public BaseRate findLeaseRateByCurrency(Currency currency) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		BaseRate rate = new BaseRate();
		rate.setCurrency(currency);
		try {
			con = dataSource.getConnection();
			stmt = con.prepareStatement(REQUEST_FIND_BASE_RATE);
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
