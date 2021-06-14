/**
 * 
 */
package com.flipkart.exception;

/**
 * Exception to check if the set of professors is empty
 * 
 * @author jagru
 *
 */
public class NoProfessorsFoundException extends Exception {

	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() {
		return "No professors found.";
	}
}
