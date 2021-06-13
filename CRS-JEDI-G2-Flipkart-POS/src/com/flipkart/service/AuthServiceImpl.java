/**
 * 
 */
package com.flipkart.service;

import com.flipkart.DAO.AuthDAOImpl;
import com.flipkart.DAO.AuthDAOInterface;

/**
 * @author aysh
 *
 */
public class AuthServiceImpl implements AuthInterface {

	public static volatile AuthServiceImpl instance = null;
	AuthDAOInterface authDAO = AuthDAOImpl.getInstance();

	public static AuthServiceImpl getInstance() {
		if (instance == null) {
			// This is a synchronized block, when multiple threads will access this instance
			synchronized (AuthServiceImpl.class) {
				instance = new AuthServiceImpl();
			}
		}
		return instance;
	}

	@Override
	public String verifyUserWithEmailPassword(String email, String paasword) {

		return authDAO.verifyUserWithEmailPassword(email, paasword);
	}

	@Override
	public boolean verifyUserWithToken(String access_token, String email) {

		return authDAO.verifyUserWithToken(access_token, email);
	}

	@Override
	public boolean generateAndStoreToken(int student_id) {
		// Auto-generated method stub
		return authDAO.generateAndStoreToken(student_id);
	}

	@Override
	public boolean logout() {
		// Auto-generated method stub
		return authDAO.logout();
	}

	@Override
	public boolean updatePassword(String email, String oldPassword, String newPassword) {
		// Auto-generated method stub
		return authDAO.updatePassword(email, oldPassword, newPassword);
	}

	@Override
	public String getRole(String userId) {
		// Auto-generated method stub
		return authDAO.getRole(userId);
	}

	@Override
	public String addUserWithEmailPassword(String userEmail, String password, String userRole) {
		// Auto-generated method stub
		return authDAO.addUserWithEmailPassword(userEmail, password, userRole);
	}

	@Override
	public boolean removeUser(String userId) {
		// Auto-generated method stub
		return authDAO.removeUser(userId);
	}

}
