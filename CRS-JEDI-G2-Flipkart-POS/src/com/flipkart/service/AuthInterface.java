/**
 * 
 */
package com.flipkart.service;

import java.sql.SQLException;

import com.flipkart.exception.InvalidCredentialsException;
import com.flipkart.exception.UserEmailNotFoundException;
import com.flipkart.exception.UserNotFoundException;

/**
 * @author aysh
 *
 */
public interface AuthInterface {

	public String verifyUserWithEmailPassword(String email, String paasword)
			throws InvalidCredentialsException, SQLException, UserEmailNotFoundException;

	public boolean verifyUserWithToken(String access_token, String email);

	public boolean generateAndStoreToken(int student_id);

	public boolean logout();

	public boolean updatePassword(String email, String oldPassword, String newPassword)
			throws InvalidCredentialsException, SQLException, UserEmailNotFoundException;

	public String getRole(String userId) throws UserNotFoundException, SQLException;

//	public String addUserWithEmailPassword(String userEmail, String password, String userRole);

}
