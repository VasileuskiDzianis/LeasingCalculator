package by.vasilevsky.leasing.dao.rate.insurance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import by.vasilevsky.leasing.dao.DataSourceProvider;
import by.vasilevsky.leasing.domain.lease_object.LeaseObjectType;
import by.vasilevsky.leasing.domain.rate.insurance.LeaseTypeInsurance;

public class LeaseTypeInsuranceDaoImpl implements LeaseTypeInsuranceDao {
	private final static String REQUEST_FIND_LEASE_RATE = "SELECT lti.id, lti.insurance FROM leaseTypeInsurance AS lti JOIN leaseObjectType AS lot ON lti.objectTypeId=lot.id WHERE lot.objectType=?;";

	private DataSource ds;

	@Override
	public LeaseTypeInsurance findInsuranceByObjectType(LeaseObjectType objectType) {
		LeaseTypeInsurance insurance = new LeaseTypeInsurance();

		ds = DataSourceProvider.getInstance().getDataSource();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		insurance.setObjectType(objectType);

		try {
			con = ds.getConnection();
			stmt = con.prepareStatement(REQUEST_FIND_LEASE_RATE);
			stmt.setString(1, objectType.toString());
			rs = stmt.executeQuery();
			if (rs.next()) {
				insurance.setInsuranceRate(rs.getFloat("insurance"));
				insurance.setId(rs.getInt("id"));
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
