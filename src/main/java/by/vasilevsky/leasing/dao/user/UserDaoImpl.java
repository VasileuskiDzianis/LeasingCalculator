package by.vasilevsky.leasing.dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import by.vasilevsky.leasing.dao.BaseDao;
import by.vasilevsky.leasing.dao.DaoException;
import by.vasilevsky.leasing.domain.user.User;
import by.vasilevsky.leasing.domain.user.UserDetails;
import by.vasilevsky.leasing.domain.user.UserRole;

public class UserDaoImpl extends BaseDao implements UserDao {
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

	private static final String USER_DB_MAPPING_ID = "id";
	private static final String USER_DB_MAPPING_LOGIN = "login";
	private static final String USER_DB_MAPPING_PSW = "password";
	private static final String USER_DB_MAPPING_ROLE = "role";
	private static final String USER_DB_MAPPING_DETAILS_ID = "detailsId";
	private static final String USER_DB_MAPPING_FIRST_NAME = "firstName";
	private static final String USER_DB_MAPPING_LAST_NAME = "lastName";
	private static final String USER_DB_MAPPING_AGE = "age";

	private static final int NOT_EXISTENT_ROLE_ID = 0;

	@Override
	public void updateUser(User user) throws DaoException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		updateUserDetails(user.getUserDetails());
		int userRoleId = findUserRoleId(user.getUserRole());

		try {
			con = dataSource.getConnection();
			stmt = con.prepareStatement(REQ_UPDATE_USER);
			stmt.setString(1, user.getLogin());
			stmt.setString(2, user.getPassword());
			stmt.setInt(3, userRoleId);
			stmt.setInt(4, user.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("User updating exception", e);
		} finally {
			closeResources(rs, stmt, con);
		}
	}

	@Override
	public void deleteUserById(int id) throws DaoException {
		Connection con = null;
		PreparedStatement stmtDeleteUser = null;
		PreparedStatement stmtDeleteUserDetails = null;

		User user = findUserById(id);

		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			
			stmtDeleteUser = con.prepareStatement(REQ_DELETE_USER);
			stmtDeleteUser.setInt(1, user.getId());
			stmtDeleteUser.executeUpdate();

			stmtDeleteUserDetails = con.prepareStatement(REQ_DELETE_DETAILS);
			stmtDeleteUserDetails.setInt(1, user.getUserDetails().getId());
			stmtDeleteUserDetails.executeUpdate();

			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException ex) {
				throw new DaoException("transaction rollback exception", ex);
			}
			throw new DaoException("User deleting exception", e);
		} finally {
			closeResources(null, stmtDeleteUser, con);
			closeResources(null, stmtDeleteUserDetails, null);
		}
	}

	@Override
	public User findUserById(int id) throws DaoException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			con = dataSource.getConnection();
			stmt = con.prepareStatement(REQ_FIND_USER_BY_ID);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				User user = new User();
				user.setId(rs.getInt(USER_DB_MAPPING_ID));
				user.setLogin(rs.getString(USER_DB_MAPPING_LOGIN));
				user.setPassword(rs.getString(USER_DB_MAPPING_PSW));
				user.setUserRole(UserRole.valueOf(rs.getString(USER_DB_MAPPING_ROLE)));
				UserDetails userDetails = new UserDetails();
				userDetails.setId(rs.getInt(USER_DB_MAPPING_DETAILS_ID));
				userDetails.setFirstName(rs.getString(USER_DB_MAPPING_FIRST_NAME));
				userDetails.setLastName(rs.getString(USER_DB_MAPPING_LAST_NAME));
				userDetails.setAge(rs.getInt(USER_DB_MAPPING_AGE));
				user.setUserDetails(userDetails);

				return user;
			}
			
		} catch (SQLException e) {
			throw new DaoException("findUserById exception", e);
		} finally {
			closeResources(rs, stmt, con);
		}
		
		return null;
	}

	@Override
	public User findUserByLogin(String login) throws DaoException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			con = dataSource.getConnection();
			stmt = con.prepareStatement(REQ_FIND_USER_BY_LOGIN);
			stmt.setString(1, login);
			rs = stmt.executeQuery();

			if (rs.next()) {
				User user = new User();
				user.setId(rs.getInt(USER_DB_MAPPING_ID));
				user.setLogin(rs.getString(USER_DB_MAPPING_LOGIN));
				user.setPassword(rs.getString(USER_DB_MAPPING_PSW));
				user.setUserRole(UserRole.valueOf(rs.getString(USER_DB_MAPPING_ROLE)));
				UserDetails userDetails = new UserDetails();
				userDetails.setId(rs.getInt(USER_DB_MAPPING_DETAILS_ID));
				userDetails.setFirstName(rs.getString(USER_DB_MAPPING_FIRST_NAME));
				userDetails.setLastName(rs.getString(USER_DB_MAPPING_LAST_NAME));
				userDetails.setAge(rs.getInt(USER_DB_MAPPING_AGE));
				user.setUserDetails(userDetails);

				return user;
			}
		} catch (SQLException e) {
			throw new DaoException("findUserByLogin exception", e);
		} finally {
			closeResources(rs, stmt, con);
		}
		return null;
	}

	@Override
	public List<User> findAll() {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<User> users;
		try {
			con = dataSource.getConnection();
			stmt = con.prepareStatement(REQ_FIND_ALL_USERS);
			rs = stmt.executeQuery();
			users = new ArrayList<User>();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt(USER_DB_MAPPING_ID));
				user.setLogin(rs.getString(USER_DB_MAPPING_LOGIN));
				user.setPassword(rs.getString(USER_DB_MAPPING_PSW));
				user.setUserRole(UserRole.valueOf(rs.getString(USER_DB_MAPPING_ROLE)));
				UserDetails userDetails = new UserDetails();
				userDetails.setId(rs.getInt(USER_DB_MAPPING_DETAILS_ID));
				userDetails.setFirstName(rs.getString(USER_DB_MAPPING_FIRST_NAME));
				userDetails.setLastName(rs.getString(USER_DB_MAPPING_LAST_NAME));
				userDetails.setAge(rs.getInt(USER_DB_MAPPING_AGE));
				user.setUserDetails(userDetails);

				users.add(user);
			}
			return users;
		} catch (SQLException e) {
			LOGGER.error("Finding all users error", e);
			
			users = Collections.emptyList();

			return users;
		} finally {
			closeResources(rs, stmt, con);
		}
	}

	@Override
	public void saveUser(User user) throws DaoException {
		Connection con = null;
		PreparedStatement stmtSaveUser = null;
		ResultSet rsSaveUser = null;
		PreparedStatement stmtSaveDetails = null;
		ResultSet rsSaveDetails = null;

		int userRoleId = findUserRoleId(user.getUserRole());

		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);

			stmtSaveDetails = con.prepareStatement(REQ_SAVE_USER_DETAILS, Statement.RETURN_GENERATED_KEYS);
			stmtSaveDetails.setString(1, user.getUserDetails().getFirstName());
			stmtSaveDetails.setString(2, user.getUserDetails().getLastName());
			stmtSaveDetails.setInt(3, user.getUserDetails().getAge());
			stmtSaveDetails.executeUpdate();
			rsSaveDetails = stmtSaveDetails.getGeneratedKeys();
			if (rsSaveDetails.next()) {
				user.getUserDetails().setId(rsSaveDetails.getInt(1));
			}

			stmtSaveUser = con.prepareStatement(REQ_SAVE_USER, Statement.RETURN_GENERATED_KEYS);
			stmtSaveUser.setString(1, user.getLogin());
			stmtSaveUser.setString(2, user.getPassword());
			stmtSaveUser.setInt(3, user.getUserDetails().getId());
			stmtSaveUser.setInt(4, userRoleId);
			stmtSaveUser.executeUpdate();
			rsSaveUser = stmtSaveUser.getGeneratedKeys();
			if (rsSaveUser.next()) {
				user.setId(rsSaveUser.getInt(1));
			}
			
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException ex) {
				throw new DaoException("transaction rollback exception", ex);
			}
			throw new DaoException("saveUser() exception", e);
		} finally {
			closeResources(rsSaveUser, stmtSaveUser, null);
			closeResources(rsSaveDetails, stmtSaveDetails, con);
		}
	}

	private int findUserRoleId(UserRole userRole) throws DaoException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			con = dataSource.getConnection();
			stmt = con.prepareStatement(REQ_GET_ROLE_ID);
			stmt.setString(1, userRole.toString());
			rs = stmt.executeQuery();

			if (rs.next()) {
				return rs.getInt("id");
			}

		} catch (SQLException e) {
			throw new DaoException("findUserRoleId exception", e);
		} finally {
			closeResources(rs, stmt, con);
		}
		
		return NOT_EXISTENT_ROLE_ID;
	}

	@Override
	public void updateUserDetails(UserDetails userDetails) throws DaoException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			con = dataSource.getConnection();
			stmt = con.prepareStatement(REQ_UPDATE_DETAILS);
			stmt.setString(1, userDetails.getFirstName());
			stmt.setString(2, userDetails.getLastName());
			stmt.setInt(3, userDetails.getAge());
			stmt.setInt(4, userDetails.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("saveUserDetails exception", e);
		} finally {
			closeResources(rs, stmt, con);
		}
	}
}
