package by.vasilevsky.leasing.dao.pool;

import java.io.PrintWriter;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.logging.Logger;

public final class ConnectionPoolImpl implements ConnectionPool {
	private static final int DEFAULT_POOL_SIZE = 5;
	private static final String PROP_MAPPING_DRIVER = "driver";
	private static final String PROP_MAPPING_URL = "url";
	private static final String PROP_MAPPING_USER = "user";
	private static final String PROP_MAPPING_PASSWORD = "password";
	private static final String PROP_MAPPING_POOL_SIZE = "poolSize";

	private BlockingQueue<Connection> connectionQueue;
	private BlockingQueue<Connection> givenAwayConQueue;

	private String driverName;
	private String url;
	private String user;
	private String password;
	private int poolSize;

	private static Properties dbProperties;

	public ConnectionPoolImpl() {
		this.driverName = dbProperties.getProperty(PROP_MAPPING_DRIVER);
		this.url = dbProperties.getProperty(PROP_MAPPING_URL);
		this.user = dbProperties.getProperty(PROP_MAPPING_USER);
		this.password = dbProperties.getProperty(PROP_MAPPING_PASSWORD);
		try {
			this.poolSize = Integer.parseInt(dbProperties.getProperty(PROP_MAPPING_POOL_SIZE));
		} catch (NumberFormatException e) {
			this.poolSize = DEFAULT_POOL_SIZE;
		}
	}

	public static void setProperties(Properties prop) {
		dbProperties = prop;
	}

	@Override
	public void initPoolData() throws SQLException {
		Locale.setDefault(Locale.ENGLISH);
		try {
			Class.forName(driverName);
			givenAwayConQueue = new ArrayBlockingQueue<Connection>(poolSize);
			connectionQueue = new ArrayBlockingQueue<>(poolSize);
			for (int i = 0; i < poolSize; i++) {
				Connection connection = DriverManager.getConnection(url, user, password);
				PooledConnection pooledConnection = new PooledConnection(connection);
				connectionQueue.add(pooledConnection);
			}
		} catch (ClassNotFoundException e) {
			throw new SQLException("Can not find database driver class", e);
		}
	}

	@Override
	public Connection getConnection() throws SQLException {
		Connection connection = null;
		try {
			connection = connectionQueue.take();
			givenAwayConQueue.add(connection);
		} catch (InterruptedException e) {
			throw new SQLException("Error occured during connection to the data source", e);
		}
		connection.setAutoCommit(true);
		return connection;
	}

	@Override
	public void close() {
		try {
			closeConnectionsQueue(givenAwayConQueue);
			closeConnectionsQueue(connectionQueue);
		} catch (SQLException e) {
		}
	}

	private void closeConnectionsQueue(BlockingQueue<Connection> queue) throws SQLException {
		Connection connection;
		while ((connection = queue.poll()) != null) {
			((PooledConnection) connection).reallyClose();
		}
	}

	private class PooledConnection implements Connection {
		private Connection connection;

		private PooledConnection(Connection con) throws SQLException {
			this.connection = con;
		}

		@Override
		public void close() throws SQLException {
			if (this.connection.isClosed()) {
				throw new SQLException("Attempting to close closed connection");
			}
			if (this.connection.isReadOnly()) {
				this.connection.setReadOnly(false);
			}
			if (!givenAwayConQueue.remove(this)) {
				throw new SQLException("Error deleting connection from the given away pool");
			}
			if (!connectionQueue.offer(this)) {
				throw new SQLException("Error allocation connection in the pool.");
			}

		}

		private void reallyClose() throws SQLException {

			this.connection.close();
		}

		@Override
		public <T> T unwrap(Class<T> iface) throws SQLException {

			return this.connection.unwrap(iface);
		}

		@Override
		public boolean isWrapperFor(Class<?> iface) throws SQLException {

			return this.connection.isWrapperFor(iface);
		}

		@Override
		public Statement createStatement() throws SQLException {

			return this.connection.createStatement();
		}

		@Override
		public PreparedStatement prepareStatement(String sql) throws SQLException {

			return this.connection.prepareStatement(sql);
		}

		@Override
		public CallableStatement prepareCall(String sql) throws SQLException {

			return this.connection.prepareCall(sql);
		}

		@Override
		public String nativeSQL(String sql) throws SQLException {

			return this.connection.nativeSQL(sql);
		}

		@Override
		public void setAutoCommit(boolean autoCommit) throws SQLException {
			this.connection.setAutoCommit(autoCommit);
		}

		@Override
		public boolean getAutoCommit() throws SQLException {

			return this.connection.getAutoCommit();
		}

		@Override
		public void commit() throws SQLException {
			this.connection.commit();
		}

		@Override
		public void rollback() throws SQLException {
			this.connection.rollback();
		}

		@Override
		public boolean isClosed() throws SQLException {

			return this.connection.isClosed();
		}

		@Override
		public DatabaseMetaData getMetaData() throws SQLException {

			return this.connection.getMetaData();
		}

		@Override
		public void setReadOnly(boolean readOnly) throws SQLException {
			this.connection.setReadOnly(readOnly);
		}

		@Override
		public boolean isReadOnly() throws SQLException {

			return this.connection.isReadOnly();
		}

		@Override
		public void setCatalog(String catalog) throws SQLException {
			this.connection.setCatalog(catalog);
		}

		@Override
		public String getCatalog() throws SQLException {

			return this.connection.getCatalog();
		}

		@Override
		public void setTransactionIsolation(int level) throws SQLException {
			this.connection.setTransactionIsolation(level);
		}

		@Override
		public int getTransactionIsolation() throws SQLException {

			return this.connection.getTransactionIsolation();
		}

		@Override
		public SQLWarning getWarnings() throws SQLException {

			return this.connection.getWarnings();
		}

		@Override
		public void clearWarnings() throws SQLException {
			this.connection.clearWarnings();
		}

		@Override
		public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {

			return this.connection.createStatement(resultSetType, resultSetConcurrency);
		}

		@Override
		public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
				throws SQLException {

			return this.connection.prepareStatement(sql, resultSetType, resultSetConcurrency);
		}

		@Override
		public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency)
				throws SQLException {

			return this.connection.prepareCall(sql, resultSetType, resultSetConcurrency);
		}

		@Override
		public Map<String, Class<?>> getTypeMap() throws SQLException {

			return this.connection.getTypeMap();
		}

		@Override
		public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
			this.connection.setTypeMap(map);
		}

		@Override
		public void setHoldability(int holdability) throws SQLException {
			this.connection.setHoldability(holdability);
		}

		@Override
		public int getHoldability() throws SQLException {

			return this.connection.getHoldability();
		}

		@Override
		public Savepoint setSavepoint() throws SQLException {

			return this.connection.setSavepoint();
		}

		@Override
		public Savepoint setSavepoint(String name) throws SQLException {

			return this.connection.setSavepoint(name);
		}

		@Override
		public void rollback(Savepoint savepoint) throws SQLException {
			this.connection.rollback(savepoint);
		}

		@Override
		public void releaseSavepoint(Savepoint savepoint) throws SQLException {
			this.connection.releaseSavepoint(savepoint);
		}

		@Override
		public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)
				throws SQLException {

			return this.connection.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
		}

		@Override
		public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency,
				int resultSetHoldability) throws SQLException {

			return this.connection.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
		}

		@Override
		public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency,
				int resultSetHoldability) throws SQLException {

			return this.connection.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
		}

		@Override
		public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {

			return this.connection.prepareStatement(sql, autoGeneratedKeys);
		}

		@Override
		public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {

			return this.connection.prepareStatement(sql, columnIndexes);
		}

		@Override
		public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {

			return this.connection.prepareStatement(sql, columnNames);
		}

		@Override
		public Clob createClob() throws SQLException {

			return this.connection.createClob();
		}

		@Override
		public Blob createBlob() throws SQLException {

			return this.connection.createBlob();
		}

		@Override
		public NClob createNClob() throws SQLException {

			return this.connection.createNClob();
		}

		@Override
		public SQLXML createSQLXML() throws SQLException {

			return this.connection.createSQLXML();
		}

		@Override
		public boolean isValid(int timeout) throws SQLException {

			return this.connection.isClosed();
		}

		@Override
		public void setClientInfo(String name, String value) throws SQLClientInfoException {
			this.connection.setClientInfo(name, value);
		}

		@Override
		public void setClientInfo(Properties properties) throws SQLClientInfoException {
			this.connection.setClientInfo(properties);
		}

		@Override
		public String getClientInfo(String name) throws SQLException {

			return this.connection.getClientInfo(name);
		}

		@Override
		public Properties getClientInfo() throws SQLException {

			return this.connection.getClientInfo();
		}

		@Override
		public Array createArrayOf(String typeName, Object[] elements) throws SQLException {

			return this.connection.createArrayOf(typeName, elements);
		}

		@Override
		public Struct createStruct(String typeName, Object[] attributes) throws SQLException {

			return this.connection.createStruct(typeName, attributes);
		}

		@Override
		public void setSchema(String schema) throws SQLException {
			this.connection.setSchema(schema);
		}

		@Override
		public String getSchema() throws SQLException {

			return this.connection.getSchema();
		}

		@Override
		public void abort(Executor executor) throws SQLException {
			this.connection.abort(executor);
		}

		@Override
		public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
			this.connection.setNetworkTimeout(executor, milliseconds);
		}

		@Override
		public int getNetworkTimeout() throws SQLException {

			return this.connection.getNetworkTimeout();
		}

	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		throw new UnsupportedOperationException();
	}
}
