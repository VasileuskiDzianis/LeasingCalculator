package by.vasilevsky.leasing.dao;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public final class DataSourceProvider {
	private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/vls?useUnicode=true&characterEncoding=utf8";
	private static final String DB_USER = "dan";
	private static final String DB_PASSWORD = "pilotpen";
	private static final int CONN_POOL_SIZE = 5;

	private BasicDataSource basicDataSource = new BasicDataSource();

	private DataSourceProvider() {
		basicDataSource.setDriverClassName(DRIVER_CLASS_NAME);
		basicDataSource.setUrl(DB_URL);
		basicDataSource.setUsername(DB_USER);
		basicDataSource.setPassword(DB_PASSWORD);
		basicDataSource.setInitialSize(CONN_POOL_SIZE);
	}

	private static class DataSourceHolder {
		private static final DataSourceProvider INSTANCE = new DataSourceProvider();
	}

	public static DataSourceProvider getInstance() {

		return DataSourceHolder.INSTANCE;
	}

	public DataSource getDataSource() {
	
		return basicDataSource;
	}
}
