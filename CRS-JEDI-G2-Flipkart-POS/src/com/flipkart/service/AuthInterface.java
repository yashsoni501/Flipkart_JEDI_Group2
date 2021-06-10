/**
 * 
 */
package com.flipkart.service;

/**
 * @author aysh
 *
 */
public interface AuthInterface {

	public void verifyUserWithEmailPassword (String email, String paasword);

	public void verifyUserWithToken (String access_token );
	
	public void generateAndStoreToken (int student_id);
	
	public void logout();
	
	public void updatePassword();
}
