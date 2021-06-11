/**
 * 
 */
package com.flipkart.service;

/**
 * @author aysh
 *
 */
public interface AuthInterface {

	public boolean verifyUserWithEmailPassword (String email, String paasword);

	public boolean verifyUserWithToken (String access_token, String email);
	
	public boolean generateAndStoreToken (int student_id);
	
	public boolean logout();
	
	public boolean updatePassword(String email, String oldPassword, String newPassword);
}
