/**
 * 
 */
package com.flipkart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.flipkart.utils.DBUtils;
import com.flipkart.constant.SQLQuery;
import com.flipkart.exception.InvalidCredentialsException;
import com.flipkart.exception.UserEmailNotFoundException;
import com.flipkart.exception.UserNotFoundException;

/**
 * The Class AuthDAOImpl.
 *
 * @author aysh
 */
public class AuthDAOImpl implements AuthDAOInterface {

	Logger logger = Logger.getLogger(AuthDAOImpl.class.getName());

	/** The instance. */
	private static volatile AuthDAOImpl instance = null;

	/**
	 * Instantiates a new auth DAO impl.
	 */
	private AuthDAOImpl() {
	}

	/**
	 * Gets the single instance of AuthDAOImpl.
	 *
	 * @return single instance of AuthDAOImpl
	 */
	public static AuthDAOImpl getInstance() {
		if (instance == null) {
			// This is a synchronized block, when multiple threads will access this instance
			synchronized (AuthDAOImpl.class) {
				instance = new AuthDAOImpl();
			}
		}
		return instance;
	}

	/**
	 * Verify user with email password.
	 *
	 * @param email    the email
	 * @param password the password
	 * @return the string
	 * @throws InvalidCredentialsException the invalid credentials exception
	 * @throws UserEmailNotFoundException  the user email not found exception
	 * @throws SQLException                the SQL exception
	 */
	@Override
	public String verifyUserWithEmailPassword(String email, String password)
			throws InvalidCredentialsException, UserEmailNotFoundException, SQLException {
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = conn.prepareStatement(SQLQuery.VERIFY_USER);
		stmt.setString(1, email);

		ResultSet resultSet = stmt.executeQuery();

		if (!resultSet.next()) {
			UserEmailNotFoundException e = new UserEmailNotFoundException(email);
			logger.error(e.getMessage());
			throw e;
		} else {
			String savedPassword = resultSet.getString("password");
			String uid = resultSet.getString("uid");

			if (password.equals(savedPassword)) {
				return uid;
			} else {
				InvalidCredentialsException e = new InvalidCredentialsException(email);
				logger.error(e.getMessage());
				throw e;
			}
		}
	}

	/**
	 * Verify user with token.
	 *
	 * @param access_token the access token
	 * @param email        the email
	 * @return true, if successful
	 */
	@Override
	public boolean verifyUserWithToken(String access_token, String email) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Generate and store token.
	 *
	 * @param student_id the student id
	 * @return true, if successful
	 */
	@Override
	public boolean generateAndStoreToken(int student_id) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Logout.
	 *
	 * @return true, if successful
	 */
	@Override
	public boolean logout() {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * Update password.
	 *
	 * @param email       the email
	 * @param oldPassword the old password
	 * @param newPassword the new password
	 * @return true, if successful
	 * @throws InvalidCredentialsException the invalid credentials exception
	 * @throws UserEmailNotFoundException  the user email not found exception
	 * @throws SQLException                the SQL exception
	 */
	@Override
	public boolean updatePassword(String email, String oldPassword, String newPassword)
			throws InvalidCredentialsException, UserEmailNotFoundException, SQLException {
		String uid = verifyUserWithEmailPassword(email, oldPassword);

		Connection conn = DBUtils.getConnection();

		PreparedStatement stmt = conn.prepareStatement(SQLQuery.UPDATE_PASSWORD);
		stmt.setString(1, newPassword);
		stmt.setString(2, email);

		int row = stmt.executeUpdate();

		if (row == 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Gets the role.
	 *
	 * @param userId the user id
	 * @return the role
	 * @throws UserNotFoundException the user not found exception
	 * @throws SQLException          the SQL exception
	 */
	@Override
	public String getRole(String userId) throws UserNotFoundException, SQLException {
		Connection conn = DBUtils.getConnection();

		PreparedStatement stmt = conn.prepareStatement(SQLQuery.GET_ROLE);
		stmt.setString(1, userId);

		ResultSet resultSet = stmt.executeQuery();

		if (resultSet.next()) {
			return resultSet.getString("userRole");
		} else {
			UserNotFoundException e = new UserNotFoundException(userId);
			logger.error(e.getMessage());
			throw e;
		}
	}
}
