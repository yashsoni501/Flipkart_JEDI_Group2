package com.flipkart.exception;

public class UserNotAddedException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5555112561292780409L;
	private String userId;

	public UserNotAddedException(String userId) {
		this.userId = userId;
	}

	/**
	 * Getter function for UserId
	 * 
	 * @return
	 */
	public String getUserId() {
		return this.userId;
	}

	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() {
		return "UserId: " + userId + " is already in use!";
	}
}
