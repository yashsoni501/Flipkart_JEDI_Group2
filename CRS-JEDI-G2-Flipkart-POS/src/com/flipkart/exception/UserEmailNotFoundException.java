/**
 * 
 */
package com.flipkart.exception;

/**
 * Exception to check if user exists
 * 
 * @author JEDI-03
 *
 */
public class UserEmailNotFoundException extends Exception {

	private String emailId;

	/***
	 * Getter function for UserId
	 * 
	 * @param userId
	 */
	public UserEmailNotFoundException(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * Message thrown by exception
	 */
	@Override
	public String getMessage() {
		return "No Account exists with email: " + emailId;
	}

}
