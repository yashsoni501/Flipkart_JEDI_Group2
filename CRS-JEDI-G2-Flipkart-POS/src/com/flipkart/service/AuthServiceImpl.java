/**
 * 
 */
package com.flipkart.service;

/**
 * @author aysh
 *
 */
public class AuthServiceImpl implements AuthInterface {
	
	public static volatile AuthServiceImpl instance = null;
	
	public static AuthServiceImpl getInstance()
	{
		if(instance==null)
		{
			// This is a synchronized block, when multiple threads will access this instance
			synchronized(AuthServiceImpl.class){
				instance=new AuthServiceImpl();
			}
		}
		return instance;
	}

	@Override
	public String verifyUserWithEmailPassword(String email, String paasword) {
		// TODO Auto-generated method stub
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
		return false;
	}

	@Override
	public boolean updatePassword(String email, String oldPassword, String newPassword) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getRole(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
