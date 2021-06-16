/**
 * 
 */
package com.flipkart.service;

import java.sql.SQLException;

import com.flipkart.DAO.AuthDAOImpl;
import com.flipkart.DAO.AuthDAOInterface;
import com.flipkart.exception.InvalidCredentialsException;
import com.flipkart.exception.UserEmailNotFoundException;
import com.flipkart.exception.UserNotFoundException;

/**
 * The Class AuthServiceImpl.
 *
 * @author aysh
 */
public class AuthServiceImpl implements AuthInterface {

	/** The instance. */
	public static volatile AuthServiceImpl instance = null;

	/** The auth DAO. */
	AuthDAOInterface authDAO = AuthDAOImpl.getInstance();

	/**
	 * Gets the single instance of AuthServiceImpl.
	 *
	 * @return single instance of AuthServiceImpl
	 */
	public static AuthServiceImpl getInstance() {
		if (instance == null) {
			// This is a synchronized block, when multiple threads will access this instance
			synchronized (AuthServiceImpl.class) {
				instance = new AuthServiceImpl();
			}
		}
		return instance;
	}

	/**
	 * Verify user with email password.
	 *
	 * @param email    the email
	 * @param paasword the paasword
	 * @return the string
	 * @throws InvalidCredentialsException the invalid credentials exception
	 * @throws SQLException                the SQL exception
	 * @throws UserEmailNotFoundException  the user email not found exception
	 */
	@Override
	public String verifyUserWithEmailPassword(String email, String paasword)
			throws InvalidCredentialsException, SQLException, UserEmailNotFoundException {

		return authDAO.verifyUserWithEmailPassword(email, paasword);
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

		return authDAO.verifyUserWithToken(access_token, email);
	}

	/**
	 * Generate and store token.
	 *
	 * @param student_id the student id
	 * @return true, if successful
	 */
	@Override
	public boolean generateAndStoreToken(int student_id) {
		// Auto-generated method stub
		return authDAO.generateAndStoreToken(student_id);
	}

	/**
	 * Logout.
	 *
	 * @return true, if successful
	 */
	@Override
	public boolean logout() {
		// Auto-generated method stub
		return authDAO.logout();
	}

	/**
	 * Update password.
	 *
	 * @param email       the email
	 * @param oldPassword the old password
	 * @param newPassword the new password
	 * @return true, if successful
	 * @throws InvalidCredentialsException the invalid credentials exception
	 * @throws SQLException                the SQL exception
	 * @throws UserEmailNotFoundException  the user email not found exception
	 */
	@Override
	public boolean updatePassword(String email, String oldPassword, String newPassword)
			throws InvalidCredentialsException, SQLException, UserEmailNotFoundException {
		// Auto-generated method stub
		return authDAO.updatePassword(email, oldPassword, newPassword);
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
		// Auto-generated method stub
		return authDAO.getRole(userId);
	}
}
