package by.vasilevsky.leasing.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class BaseDao {
	protected void closeResources(ResultSet rs, Statement stmt, Connection con) {
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
}