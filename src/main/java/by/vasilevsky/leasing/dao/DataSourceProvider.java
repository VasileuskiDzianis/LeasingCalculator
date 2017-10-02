package by.vasilevsky.leasing.dao;

import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import by.vasilevsky.leasing.dao.pool.ConnectionPool;
import by.vasilevsky.leasing.dao.pool.ConnectionPoolImpl;

public final class DataSourceProvider {
	private static Properties properties;

	private ConnectionPool connectionPool;

	private DataSourceProvider() {
		ConnectionPoolImpl.setProperties(properties);
		connectionPool = new ConnectionPoolImpl();
		try {
			connectionPool.initPoolData();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void setProperties(Properties prop) {
		properties = prop;
	}

	private static class DataSourceHolder {
		private static final DataSourceProvider INSTANCE = new DataSourceProvider();
	}

	public static DataSourceProvider getInstance() {

		return DataSourceHolder.INSTANCE;
	}

	public DataSource getDataSource() {
	
		return connectionPool;
	}
}
