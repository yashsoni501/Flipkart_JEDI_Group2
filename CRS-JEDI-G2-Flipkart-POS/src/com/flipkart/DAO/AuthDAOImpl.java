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
	public String verifyUserWithEmailPassword(String email, String password) {
		Connection conn = DBUtils.getConnection();
		try {

			PreparedStatement stmt = conn.prepareStatement(SQLQuery.VERIFY_USER);
			stmt.setString(1, email);

			ResultSet resultSet = stmt.executeQuery();

			if (!resultSet.next()) {
				return null;
			} else {
				String savedPassword = resultSet.getString("password");
				String uid = resultSet.getString("uid");

				if (password.equals(savedPassword)) {
					return uid;
				}
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return null;
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
	public boolean updatePassword(String email, String oldPassword, String newPassword) {
		String uid = verifyUserWithEmailPassword(email, oldPassword);

		Connection conn = DBUtils.getConnection();

		if (!uid.isEmpty()) {

			try {

				PreparedStatement stmt = conn.prepareStatement(SQLQuery.UPDATE_PASSWORD);
				stmt.setString(1, newPassword);
				stmt.setString(2, email);

				int row = stmt.executeUpdate();

				if (row == 1) {
					return true;
				} else {
					return false;
				}
			} catch (Exception e) {
				e.getMessage();
			}
		}
		return false;
	}

	@Override
	public String getRole(String userId) {
		Connection conn = DBUtils.getConnection();

		try {
			PreparedStatement stmt = conn.prepareStatement(SQLQuery.GET_ROLE);
			stmt.setString(1, userId);

			ResultSet resultSet = stmt.executeQuery();

			if (resultSet.next()) {
				return resultSet.getString("userRole");
			}
		} catch (SQLException se) {
			se.getMessage();
		} catch (Exception e) {
			e.getMessage();
		}
		return "";
	}
}
