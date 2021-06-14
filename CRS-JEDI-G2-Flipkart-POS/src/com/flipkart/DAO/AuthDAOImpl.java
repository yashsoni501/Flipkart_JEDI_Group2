/**
 * 
 */
package com.flipkart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.flipkart.utils.DBUtils;
import com.flipkart.constant.SQLQuery;
import com.flipkart.exception.InvalidCredentialsException;
import com.flipkart.exception.UserEmailNotFoundException;
import com.flipkart.exception.UserNotFoundException;

/**
 * @author aysh
 *
 */
public class AuthDAOImpl implements AuthDAOInterface {

	private static volatile AuthDAOImpl instance = null;

	private AuthDAOImpl() {
	}

	public static AuthDAOImpl getInstance() {
		if (instance == null) {
			// This is a synchronized block, when multiple threads will access this instance
			synchronized (AuthDAOImpl.class) {
				instance = new AuthDAOImpl();
			}
		}
		return instance;
	}

	@Override
	public String verifyUserWithEmailPassword(String email, String password)
			throws InvalidCredentialsException, UserEmailNotFoundException, SQLException {
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = conn.prepareStatement(SQLQuery.VERIFY_USER);
		stmt.setString(1, email);

		ResultSet resultSet = stmt.executeQuery();

		if (!resultSet.next()) {
			throw new UserEmailNotFoundException(email);

		} else {
			String savedPassword = resultSet.getString("password");
			String uid = resultSet.getString("uid");

			if (password.equals(savedPassword)) {
				return uid;
			} else {
				throw new InvalidCredentialsException(email);
			}
		}
	}

	@Override
	public boolean verifyUserWithToken(String access_token, String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean generateAndStoreToken(int student_id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean logout() {
		// TODO Auto-generated method stub
		return true;
	}

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

	@Override
	public String getRole(String userId) throws UserNotFoundException, SQLException {
		Connection conn = DBUtils.getConnection();

		PreparedStatement stmt = conn.prepareStatement(SQLQuery.GET_ROLE);
		stmt.setString(1, userId);

		ResultSet resultSet = stmt.executeQuery();

		if (resultSet.next()) {
			return resultSet.getString("userRole");
		} else {
			throw new UserNotFoundException(userId);
		}
	}
}
