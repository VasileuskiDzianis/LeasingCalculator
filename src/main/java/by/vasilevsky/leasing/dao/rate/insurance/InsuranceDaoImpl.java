package by.vasilevsky.leasing.dao.rate.insurance;

import java.sql.*;

import javax.sql.DataSource;

import by.vasilevsky.leasing.dao.DataSourceProvider;
import by.vasilevsky.leasing.domain.lease_object.PropertyType;
import by.vasilevsky.leasing.domain.rate.insurance.Insurance;

public class InsuranceDaoImpl implements InsuranceDao {
	private final static String REQUEST_FIND_LEASE_RATE = "SELECT i.id, i.insurance FROM insurance AS i JOIN leaseObjectType AS lot ON i.objectTypeId=lot.id WHERE lot.objectType=?;";
	private static final String INSURANCE_DB_MAPPING_ID = "id";
	private static final String INSURANCE_DB_MAPPING_VALUE = "insurance";

	@Override
	public Insurance findInsuranceByObjectType(PropertyType objectType) {
		DataSource ds = DataSourceProvider.getInstance().getDataSource();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		Insurance insurance = new Insurance();
		insurance.setObjectType(objectType);
		try {
			con = ds.getConnection();
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
		return insurance;
	}
}
