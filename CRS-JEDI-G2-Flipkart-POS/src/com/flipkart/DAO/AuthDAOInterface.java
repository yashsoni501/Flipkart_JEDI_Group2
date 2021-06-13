/**
 * 
 */
package com.flipkart.DAO;

/**
 * @author aysh
 *
 */
public interface AuthDAOInterface {

	public String verifyUserWithEmailPassword(String email, String paasword);

	public boolean verifyUserWithToken(String access_token, String email);

	public boolean generateAndStoreToken(int student_id);

	public boolean logout();

	public boolean updatePassword(String email, String oldPassword, String newPassword);

	public String getRole(String userId);
}
