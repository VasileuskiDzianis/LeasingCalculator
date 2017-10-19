package by.vasilevsky.leasing.web.listener;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.vasilevsky.leasing.dao.DataSourceProvider;

@WebListener
public class DataSourceContextListener implements ServletContextListener {
	private static final Logger LOGGER = LogManager.getLogger(DataSourceContextListener.class);
	
	private static final String DEFAULT_DB_PROP_FILE = "database.properties";

	public void contextInitialized(ServletContextEvent arg0) {
		InputStream input = null;
		Properties dataBaseProperties = new Properties();
		try {
			input = this.getClass().getClassLoader().getResourceAsStream(DEFAULT_DB_PROP_FILE);
			dataBaseProperties.load(input);
		} catch (Exception e) {
			throw new RuntimeException("Database properties reading error", e);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					LOGGER.error("Data base input stream closing error", e);
				}
			}
		}
		DataSourceProvider.setProperties(dataBaseProperties);
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		try {
			((Closeable) DataSourceProvider.getInstance().getDataSource()).close();
		} catch (IOException e) {
			LOGGER.error("Data source closing error while context destroyed", e);
		}	
	}
}
