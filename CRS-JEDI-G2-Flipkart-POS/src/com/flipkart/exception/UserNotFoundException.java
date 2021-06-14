/**
 * 
 */
package com.flipkart.exception;

/**
 * Exception to check if user exists
 * 
 * @author Aeron
 *
 */
public class UserNotFoundException extends Exception {

	private String userId;

	/***
	 * Getter function for UserId
	 * 
	 * @param userId
	 */
	public UserNotFoundException(String userId) {
		this.userId = userId;
	}

	/**
	 * Message thrown by exception
	 */
	@Override
	public String getMessage() {
		return "Student with Id: " + userId + " not found.";
	}

}
