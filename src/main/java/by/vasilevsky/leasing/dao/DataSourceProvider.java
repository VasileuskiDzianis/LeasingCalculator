package by.vasilevsky.leasing.dao;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public final class DataSourceProvider {
	private static Properties properties;

	private BasicDataSource basicDataSource = new BasicDataSource();

	private DataSourceProvider() {
		basicDataSource.setDriverClassName(properties.getProperty("driver"));
		basicDataSource.setUrl(properties.getProperty("url"));
		basicDataSource.setUsername(properties.getProperty("user"));
		basicDataSource.setPassword(properties.getProperty("password"));
		basicDataSource.setInitialSize(Integer.parseInt(properties.getProperty("poolSize")));
		basicDataSource.setConnectionProperties(properties.getProperty("connectionProperties"));
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
	
		return basicDataSource;
	}
}
