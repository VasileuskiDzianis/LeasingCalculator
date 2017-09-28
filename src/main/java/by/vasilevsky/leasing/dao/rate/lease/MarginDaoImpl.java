package by.vasilevsky.leasing.dao.rate.lease;

import java.sql.*;

import javax.sql.DataSource;

import by.vasilevsky.leasing.dao.DataSourceProvider;
import by.vasilevsky.leasing.domain.lease_object.PropertyType;
import by.vasilevsky.leasing.domain.rate.lease.Margin;

public class MarginDaoImpl implements MarginDao {
	private final static String REQUEST_FIND_LEASE_RATE = "SELECT m.id, m.margin FROM margin AS m JOIN leaseObjectType AS lot ON m.objectTypeId=lot.id WHERE m.objectAge=? AND lot.objectType=?;";

	private static final String MARGIN_DB_MAPPING_ID = "id";
	private static final String MARGIN_DB_MAPPING_VALUE = "margin";

	private DataSource ds;

	@Override
	public Margin findMarginByTypeAndAge(PropertyType objectType, int age) {
		Margin margin = new Margin();

		ds = DataSourceProvider.getInstance().getDataSource();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		margin.setPropertyAge(age);
		margin.setPropertyType(objectType);

		try {
			con = ds.getConnection();
			stmt = con.prepareStatement(REQUEST_FIND_LEASE_RATE);
			stmt.setInt(1, age);
			stmt.setString(2, objectType.toString());
			rs = stmt.executeQuery();
			if (rs.next()) {
				margin.setMargin(rs.getFloat(MARGIN_DB_MAPPING_VALUE));
				margin.setId(rs.getInt(MARGIN_DB_MAPPING_ID));
			}
		} catch (SQLException e) {
			throw new RuntimeException("Finding margin exception: ", e);
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
