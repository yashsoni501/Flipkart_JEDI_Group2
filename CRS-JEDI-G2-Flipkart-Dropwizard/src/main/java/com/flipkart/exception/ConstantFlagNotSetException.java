/**
 * 
 */
package com.flipkart.exception;

/**
 * @author aysh
 *
 */
public class ConstantFlagNotSetException extends Exception {

	private String flagKey;

	public ConstantFlagNotSetException(String flagKey) {
		this.flagKey = flagKey;
	}

	public String getFlagKey() {
		return flagKey;
	}

	public void setFlagKey(String flagKey) {
		this.flagKey = flagKey;
	}

	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() {
		return "The flag " + flagKey + " is not set in the constants table";
	}

}
