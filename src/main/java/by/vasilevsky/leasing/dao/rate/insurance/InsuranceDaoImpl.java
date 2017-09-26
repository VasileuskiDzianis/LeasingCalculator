package by.vasilevsky.leasing.dao.rate.insurance;

import java.sql.*;

import javax.sql.DataSource;

import by.vasilevsky.leasing.dao.DataSourceProvider;
import by.vasilevsky.leasing.domain.lease_object.PropertyType;
import by.vasilevsky.leasing.domain.rate.insurance.Insurance;

public class InsuranceDaoImpl implements InsuranceDao {
	private final static String REQUEST_FIND_LEASE_RATE = "SELECT i.id, i.insurance FROM insurance AS i JOIN leaseObjectType AS lot ON i.objectTypeId=lot.id WHERE lot.objectType=?;";

	private DataSource ds;
	private static volatile InsuranceDaoImpl instance;

	private InsuranceDaoImpl() {

	}

	public static InsuranceDao getInstance() {
		InsuranceDaoImpl localInstance = instance;
		if (localInstance == null) {
			synchronized (InsuranceDaoImpl.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new InsuranceDaoImpl();
				}
			}
		}
		return localInstance;
	}

	@Override
	public Insurance findInsuranceByObjectType(PropertyType objectType) {
		Insurance insurance = new Insurance();

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
				insurance.setRate(rs.getFloat("insurance"));
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
