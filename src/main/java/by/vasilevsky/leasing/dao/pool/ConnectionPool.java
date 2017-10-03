package by.vasilevsky.leasing.dao.pool;

import java.io.Closeable;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

public interface ConnectionPool extends DataSource, Closeable {

	void initPoolData() throws SQLException;

	Connection getConnection() throws SQLException;

	void close();

	PrintWriter getLogWriter() throws SQLException;

	void setLogWriter(PrintWriter out) throws SQLException;

	void setLoginTimeout(int seconds) throws SQLException;

	int getLoginTimeout() throws SQLException;

	Logger getParentLogger() throws SQLFeatureNotSupportedException;

	<T> T unwrap(Class<T> iface) throws SQLException;

	boolean isWrapperFor(Class<?> iface) throws SQLException;

	Connection getConnection(String username, String password) throws SQLException;

	void setProperties(Properties prop);

}