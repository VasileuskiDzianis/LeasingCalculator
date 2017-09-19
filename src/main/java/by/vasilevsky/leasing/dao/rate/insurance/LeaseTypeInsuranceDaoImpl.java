package by.vasilevsky.leasing.dao.rate.insurance;

import java.sql.*;

import javax.sql.DataSource;

import by.vasilevsky.leasing.dao.DataSourceProvider;
import by.vasilevsky.leasing.domain.lease_object.PropertyType;
import by.vasilevsky.leasing.domain.rate.insurance.LeaseTypeInsurance;

public class LeaseTypeInsuranceDaoImpl implements LeaseTypeInsuranceDao {
	private final static String REQUEST_FIND_LEASE_RATE = "SELECT lti.id, lti.insurance FROM leaseTypeInsurance AS lti JOIN leaseObjectType AS lot ON lti.objectTypeId=lot.id WHERE lot.objectType=?;";

	private DataSource ds;
	private static volatile LeaseTypeInsuranceDaoImpl instance;

	private LeaseTypeInsuranceDaoImpl() {

	}

	public static LeaseTypeInsuranceDao getInstance() {
		LeaseTypeInsuranceDaoImpl localInstance = instance;
		if (localInstance == null) {
			synchronized (LeaseTypeInsuranceDaoImpl.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new LeaseTypeInsuranceDaoImpl();
				}
			}
		}
		return localInstance;
	}

	@Override
	public LeaseTypeInsurance findInsuranceByObjectType(PropertyType objectType) {
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
