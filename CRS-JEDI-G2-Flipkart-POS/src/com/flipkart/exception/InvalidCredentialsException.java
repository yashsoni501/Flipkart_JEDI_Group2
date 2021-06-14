/**
 * 
 */
package com.flipkart.exception;

/**
 * @author aysh
 *
 */
public class InvalidCredentialsException extends Exception {

	private String email;

	public InvalidCredentialsException(String email) {
		this.email = email;
	}

	@Override
	public String getMessage() {
		return "Incorrect password for the email: " + email;
	}

}
