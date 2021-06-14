/**
 * 
 */
package com.flipkart.service;

import java.sql.SQLException;

import com.flipkart.exception.InvalidCredentialsException;
import com.flipkart.exception.UserEmailNotFoundException;
import com.flipkart.exception.UserNotFoundException;

/**
 * The Interface AuthInterface.
 *
 * @author aysh
 */
public interface AuthInterface {

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
	public String verifyUserWithEmailPassword(String email, String paasword)
			throws InvalidCredentialsException, SQLException, UserEmailNotFoundException;

	/**
	 * Verify user with token.
	 *
	 * @param access_token the access token
	 * @param email        the email
	 * @return true, if successful
	 */
	public boolean verifyUserWithToken(String access_token, String email);

	/**
	 * Generate and store token.
	 *
	 * @param student_id the student id
	 * @return true, if successful
	 */
	public boolean generateAndStoreToken(int student_id);

	/**
	 * Logout.
	 *
	 * @return true, if successful
	 */
	public boolean logout();

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
	public boolean updatePassword(String email, String oldPassword, String newPassword)
			throws InvalidCredentialsException, SQLException, UserEmailNotFoundException;

	/**
	 * Gets the role.
	 *
	 * @param userId the user id
	 * @return the role
	 * @throws UserNotFoundException the user not found exception
	 * @throws SQLException          the SQL exception
	 */
	public String getRole(String userId) throws UserNotFoundException, SQLException;

//	public String addUserWithEmailPassword(String userEmail, String password, String userRole);

}
