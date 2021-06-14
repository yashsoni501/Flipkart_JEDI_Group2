/**
 * 
 */
package com.flipkart.exception;

public class UserEmailAlreadyInUseException extends Exception {

	private static final long serialVersionUID = 1561740888758383209L;

	private String userId;

	public UserEmailAlreadyInUseException(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setProfessorId(String userId) {
		this.userId = userId;
	}

	@Override
	public String getMessage() {
		return "userId: " + userId + " is already in use.";
	}

}
