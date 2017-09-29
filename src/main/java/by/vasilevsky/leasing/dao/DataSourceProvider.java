package by.vasilevsky.leasing.dao;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public final class DataSourceProvider {
	private static final String PROP_MAPPING_DRIVER = "driver";
	private static final String PROP_MAPPING_URL = "url";
	private static final String PROP_MAPPING_USER = "user";
	private static final String PROP_MAPPING_PASSWORD = "password";
	private static final String PROP_MAPPING_POOL_SIZE = "poolSize";
	private static final String PROP_MAPPING_CONNECTION_PROPS = "connectionProperties";
	
	private static Properties properties;

	private BasicDataSource basicDataSource = new BasicDataSource();

	private DataSourceProvider() {
		basicDataSource.setDriverClassName(properties.getProperty(PROP_MAPPING_DRIVER));
		basicDataSource.setUrl(properties.getProperty(PROP_MAPPING_URL));
		basicDataSource.setUsername(properties.getProperty(PROP_MAPPING_USER));
		basicDataSource.setPassword(properties.getProperty(PROP_MAPPING_PASSWORD));
		basicDataSource.setInitialSize(Integer.parseInt(properties.getProperty(PROP_MAPPING_POOL_SIZE)));
		basicDataSource.setConnectionProperties(properties.getProperty(PROP_MAPPING_CONNECTION_PROPS));
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
