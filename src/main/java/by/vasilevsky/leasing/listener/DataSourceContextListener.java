package by.vasilevsky.leasing.listener;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import by.vasilevsky.leasing.dao.DataSourceProvider;

@WebListener
public class DataSourceContextListener implements ServletContextListener {
	private static final String DEFAULT_DB_PROP = "database.properties";
	private static final String DEFAULT_DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
	private static final String DEFAULT_DB_URL = "jdbc:mysql://localhost:3306/vls";
	private static final String DEFAULT_DB_USER = "dan";
	private static final String DEFAULT_DB_PASSWORD = "pilotpen";
	private static final String DEFAULT_CON_PROP = "useUnicode=yes;characterEncoding=utf8";
	private static final String DEFAULT_CONN_POOL_SIZE = "5";

	public void contextInitialized(ServletContextEvent arg0) {
		InputStream input = null;
		Properties database = new Properties();
		try {
			input = getClass().getResourceAsStream(DEFAULT_DB_PROP);
			database.load(input);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			database.put("driver", DEFAULT_DRIVER_CLASS_NAME);
			database.put("url", DEFAULT_DB_URL);
			database.put("user", DEFAULT_DB_USER);
			database.put("password", DEFAULT_DB_PASSWORD);
			database.put("poolSize", DEFAULT_CONN_POOL_SIZE);
			database.put("connectionProperties", DEFAULT_CON_PROP);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		DataSourceProvider.setProperties(database);
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}
}
