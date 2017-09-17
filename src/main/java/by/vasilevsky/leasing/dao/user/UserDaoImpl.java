package by.vasilevsky.leasing.dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import by.vasilevsky.leasing.dao.DataSourceProvider;
import by.vasilevsky.leasing.domain.user.User;
import by.vasilevsky.leasing.domain.user.UserDetails;
import by.vasilevsky.leasing.domain.user.UserRole;

public class UserDaoImpl implements UserDao {
	private static final String REQ_FIND_USER_BY_LOGIN = "SELECT u.*, r.role, d.firstName, d.lastName, d.age, d.detailsId "
			+ "FROM user AS u JOIN userDetails AS d ON u.detailsId=d.detailsId JOIN userRole AS r ON u.roleId=r.id WHERE u.login=?;";
	private static final String REQ_FIND_USER_BY_ID = "SELECT u.*, r.role, d.firstName, d.lastName, d.age, d.detailsId "
			+ "FROM user AS u JOIN userDetails AS d ON u.detailsId=d.detailsId JOIN userRole AS r ON u.roleId=r.id WHERE u.id=?;";
	private static final String REQ_FIND_ALL_USERS = "SELECT u.*, r.role, d.firstName, d.lastName, d.age, d.detailsId "
			+ "FROM user AS u JOIN userDetails AS d ON u.detailsId=d.detailsId JOIN userRole AS r ON u.roleId=r.id;";
	private static final String REQ_SAVE_USER = "INSERT INTO user(login,password,detailsId,roleId) VALUES(?,?,?,?);";
	private static final String REQ_SAVE_USER_DETAILS = "INSERT INTO userDetails(firstName,lastName,age) VALUES(?,?,?);";
	private static final String REQ_GET_ROLE_ID = "SELECT id FROM userRole WHERE role=?;";
	private static final String REQ_DELETE_DETAILS = "DELETE FROM userDetails WHERE detailsId=?;";
	private static final String REQ_DELETE_USER = "DELETE FROM user WHERE id=?;";
	private static final String REQ_UPDATE_DETAILS = "UPDATE userDetails SET firstName=?, lastName=?, age=? WHERE detailsId=?;";
	private static final String REQ_UPDATE_USER = "UPDATE user SET login=?, password=?, roleId=? WHERE id=?;";

	private DataSource ds;
	private static volatile UserDaoImpl instance;

	private UserDaoImpl() {

	}

	public static UserDao getInstance() {
		UserDaoImpl localInstance = instance;
		if (localInstance == null) {
			synchronized (UserDaoImpl.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new UserDaoImpl();
				}
			}
		}
		return localInstance;
	}

	@Override
	public void updateUser(User user) {

		ds = DataSourceProvider.getInstance().getDataSource();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		updateUserDetails(user.getUserDetails());
		int userRoleId = findUserRoleId(user.getUserRole());

		try {
			con = ds.getConnection();
			stmt = con.prepareStatement(REQ_UPDATE_USER);
			stmt.setString(1, user.getLogin());
			stmt.setString(2, user.getPassword());
			stmt.setInt(3, userRoleId);
			stmt.setInt(4, user.getId());
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("saveUser() exception", e);
		} finally {
			closeResources(rs, stmt, con);
		}
	}

	@Override
	public void deleteUser(User user) {
		ds = DataSourceProvider.getInstance().getDataSource();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		deleteUserDetails(user.getUserDetails());

		try {
			con = ds.getConnection();
			stmt = con.prepareStatement(REQ_DELETE_USER);
			stmt.setInt(1, user.getId());
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("deleteUser exception", e);
		} finally {
			closeResources(rs, stmt, con);
		}
	}

	@Override
	public User findUserById(int id) {
		ds = DataSourceProvider.getInstance().getDataSource();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			stmt = con.prepareStatement(REQ_FIND_USER_BY_ID);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setLogin(rs.getString("login"));
				user.setPassword(rs.getString("password"));
				user.setUserRole(UserRole.valueOf(rs.getString("role")));
				UserDetails userDetails = new UserDetails();
				userDetails.setId(rs.getInt("detailsId"));
				userDetails.setFirstName(rs.getString("firstName"));
				userDetails.setLastName(rs.getString("lastName"));
				userDetails.setAge(rs.getInt("age"));
				user.setUserDetails(userDetails);

				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("findUserById exception", e);
		} finally {
			closeResources(rs, stmt, con);
		}
		return null;
	}

	@Override
	public User findUserByLogin(String login) {
		ds = DataSourceProvider.getInstance().getDataSource();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			stmt = con.prepareStatement(REQ_FIND_USER_BY_LOGIN);
			stmt.setString(1, login);
			rs = stmt.executeQuery();

			if (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setLogin(rs.getString("login"));
				user.setPassword(rs.getString("password"));
				user.setUserRole(UserRole.valueOf(rs.getString("role")));
				UserDetails userDetails = new UserDetails();
				userDetails.setId(rs.getInt("detailsId"));
				userDetails.setFirstName(rs.getString("firstName"));
				userDetails.setLastName(rs.getString("lastName"));
				userDetails.setAge(rs.getInt("age"));
				user.setUserDetails(userDetails);

				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("findUserByLogin exception", e);
		} finally {
			closeResources(rs, stmt, con);
		}
		return null;
	}

	@Override
	public List<User> findAll() {
		ds = DataSourceProvider.getInstance().getDataSource();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<User> users;
		try {
			con = ds.getConnection();
			stmt = con.prepareStatement(REQ_FIND_ALL_USERS);
			rs = stmt.executeQuery();
			users = new ArrayList<User>();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setLogin(rs.getString("login"));
				user.setPassword(rs.getString("password"));
				user.setUserRole(UserRole.valueOf(rs.getString("role")));
				UserDetails userDetails = new UserDetails();
				userDetails.setId(rs.getInt("detailsId"));
				userDetails.setFirstName(rs.getString("firstName"));
				userDetails.setLastName(rs.getString("lastName"));
				userDetails.setAge(rs.getInt("age"));
				user.setUserDetails(userDetails);
				
				users.add(user);
			}
			return users;
		} catch (SQLException e) {
			users = Collections.emptyList();
			
			return users;
		} finally {
			closeResources(rs, stmt, con);
		}
	}

	@Override
	public void saveUser(User user) {
		ds = DataSourceProvider.getInstance().getDataSource();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		saveUserDetails(user.getUserDetails());
		int userRoleId = findUserRoleId(user.getUserRole());

		try {
			con = ds.getConnection();
			stmt = con.prepareStatement(REQ_SAVE_USER, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, user.getLogin());
			stmt.setString(2, user.getPassword());
			stmt.setInt(3, user.getUserDetails().getId());
			stmt.setInt(4, userRoleId);
			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();

			if (rs.next()) {
				user.setId(rs.getInt(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("saveUser() exception", e);
		} finally {
			closeResources(rs, stmt, con);
		}
	}

	private int findUserRoleId(UserRole userRole) {
		ds = DataSourceProvider.getInstance().getDataSource();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			stmt = con.prepareStatement(REQ_GET_ROLE_ID);
			stmt.setString(1, userRole.toString());
			rs = stmt.executeQuery();

			if (rs.next()) {

				return rs.getInt("id");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("findUserRoleId exception", e);
		} finally {
			closeResources(rs, stmt, con);
		}
		return 0;
	}

	private void deleteUserDetails(UserDetails userDetails) {
		ds = DataSourceProvider.getInstance().getDataSource();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			stmt = con.prepareStatement(REQ_DELETE_DETAILS);
			stmt.setInt(1, userDetails.getId());
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("deleteUserDetails exception", e);
		} finally {
			closeResources(rs, stmt, con);
		}
	}

	private void updateUserDetails(UserDetails userDetails) {
		ds = DataSourceProvider.getInstance().getDataSource();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			stmt = con.prepareStatement(REQ_UPDATE_DETAILS);
			stmt.setString(1, userDetails.getFirstName());
			stmt.setString(2, userDetails.getLastName());
			stmt.setInt(3, userDetails.getAge());
			stmt.setInt(4, userDetails.getId());
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("saveUserDetails exception", e);
		} finally {
			closeResources(rs, stmt, con);
		}
	}

	private void saveUserDetails(UserDetails userDetails) {
		ds = DataSourceProvider.getInstance().getDataSource();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			stmt = con.prepareStatement(REQ_SAVE_USER_DETAILS, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, userDetails.getFirstName());
			stmt.setString(2, userDetails.getLastName());
			stmt.setInt(3, userDetails.getAge());
			stmt.executeUpdate();

			rs = stmt.getGeneratedKeys();

			if (rs.next()) {
				userDetails.setId(rs.getInt(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("saveUserDetails exception", e);
		} finally {
			closeResources(rs, stmt, con);
		}
	}

	private void closeResources(ResultSet rs, Statement stmt, Connection con) {
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
