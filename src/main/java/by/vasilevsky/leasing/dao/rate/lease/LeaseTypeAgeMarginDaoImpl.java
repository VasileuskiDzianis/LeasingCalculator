package by.vasilevsky.leasing.dao.rate.lease;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import by.vasilevsky.leasing.dao.DataSourceProvider;
import by.vasilevsky.leasing.domain.lease_object.LeaseObjectType;
import by.vasilevsky.leasing.domain.rate.lease.LeaseTypeAgeMargin;

public class LeaseTypeAgeMarginDaoImpl implements LeaseTypeAgeMarginDao {
	private final static String REQUEST_FIND_LEASE_RATE = "SELECT ltam.id, ltam.margin FROM leaseTypeAgeMargin AS ltam JOIN leaseObjectType AS lot ON ltam.objectTypeId=lot.id WHERE ltam.objectAge=? AND lot.objectType=?;";

	private DataSource ds;

	@Override
	public LeaseTypeAgeMargin findLeaseRateByTypeAndAge(LeaseObjectType objectType, int age) {
		LeaseTypeAgeMargin margin = new LeaseTypeAgeMargin();

		ds = DataSourceProvider.getInstance().getDataSource();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		margin.setObjectAge(age);
		margin.setObjectType(objectType);

		try {
			con = ds.getConnection();
			stmt = con.prepareStatement(REQUEST_FIND_LEASE_RATE);
			stmt.setInt(1, age);
			stmt.setString(2, objectType.toString());
			rs = stmt.executeQuery();
			if (rs.next()) {
				margin.setMargin(rs.getFloat("margin"));
				margin.setId(rs.getInt("id"));
			}
		} catch (SQLException e) {
			throw new RuntimeException("Finding lease rate exception: ", e);
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
		return margin;
	}
}
