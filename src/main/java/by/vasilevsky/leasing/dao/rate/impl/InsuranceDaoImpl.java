package by.vasilevsky.leasing.dao.rate.impl;

import java.sql.*;

import by.vasilevsky.leasing.dao.BaseDao;
import by.vasilevsky.leasing.dao.rate.InsuranceDao;
import by.vasilevsky.leasing.domain.lease_object.PropertyType;
import by.vasilevsky.leasing.domain.rate.Insurance;

public class InsuranceDaoImpl extends BaseDao implements InsuranceDao {
	private final static String REQUEST_FIND_LEASE_RATE = "SELECT i.id, i.insurance FROM insurance AS i JOIN leaseObjectType AS lot ON i.objectTypeId=lot.id WHERE lot.objectType=?;";
	private static final String INSURANCE_DB_MAPPING_ID = "id";
	private static final String INSURANCE_DB_MAPPING_VALUE = "insurance";

	@Override
	public Insurance findInsuranceByObjectType(PropertyType objectType) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		Insurance insurance = new Insurance();
		insurance.setObjectType(objectType);
		try {
			con = dataSource.getConnection();
			stmt = con.prepareStatement(REQUEST_FIND_LEASE_RATE);
			stmt.setString(1, objectType.toString());
			rs = stmt.executeQuery();
			if (rs.next()) {
				insurance.setRate(rs.getFloat(INSURANCE_DB_MAPPING_VALUE));
				insurance.setId(rs.getInt(INSURANCE_DB_MAPPING_ID));
			}
		} catch (SQLException e) {
			throw new RuntimeException("Finding insurance rate exception: ", e);
		} finally {
			closeResources(rs, stmt, con);
		}
		
		return insurance;
	}
}
