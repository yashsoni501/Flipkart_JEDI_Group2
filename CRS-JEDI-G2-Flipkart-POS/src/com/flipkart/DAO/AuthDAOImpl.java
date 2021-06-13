/**
 * 
 */
package com.flipkart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.flipkart.utils.DBUtils;

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

			String VERIFY_USER = "select * from auth where email = ?";

			PreparedStatement stmt = conn.prepareStatement(VERIFY_USER);
			stmt.setString(1, email);

			ResultSet resultSet = stmt.executeQuery();

			if (!resultSet.next()) {
				return "";
			} else {
				String savedPassword = resultSet.getString("password");
				String uid = resultSet.getString("uid");

				if (password.equals(savedPassword)) {
					return uid;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
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

			String UPDATE_PASSWORD = "update auth set password=? where email=?";
			try {

				PreparedStatement stmt = conn.prepareStatement(UPDATE_PASSWORD);
				stmt.setString(1, newPassword);
				stmt.setString(2, email);

				int row = stmt.executeUpdate();

				if (row == 1) {
					return true;
				} else {
					return false;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public String getRole(String userId) {
		Connection conn = DBUtils.getConnection();

		String GET_ROLE = "select userRole from auth where uid=?";
		try {
			PreparedStatement stmt = conn.prepareStatement(GET_ROLE);
			stmt.setString(1, userId);

			ResultSet resultSet = stmt.executeQuery();

			if (resultSet.next()) {
				return resultSet.getString("userRole");
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public String addUserWithEmailPassword(String userEmail, String password, String userRole) {
		Connection conn = DBUtils.getConnection();

		String ADD_USER = "insert into auth (`email`, `password`, `userRole`) values (?, ?, ?)";
		try {
			PreparedStatement stmt = conn.prepareStatement(ADD_USER);
			stmt.setString(1, userEmail);
			stmt.setString(2, password);
			stmt.setString(3, userRole);

			int row = stmt.executeUpdate();
			if (row == 0) {
				System.err.println("Failed to Add user");
			} else {
				return verifyUserWithEmailPassword(userEmail, password);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean removeUser(String profId) {
		// TODO Auto-generated method stub
		return false;
	}

}
