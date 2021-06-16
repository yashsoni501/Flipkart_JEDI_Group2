/**
 * 
 */
package com.flipkart.exception;

/**
 * Exception to check if semester report case exists
 * 
 * @author yashsoni501
 *
 */
public class SemesterReportCardNotFound extends Exception {

	private String userId;

	/***
	 * Getter function for UserId
	 * 
	 * @param userId
	 */
	public SemesterReportCardNotFound(String userId) {
		this.userId = userId;
	}

	/**
	 * Message thrown by exception
	 */
	@Override
	public String getMessage() {
		return "No report card found for student with userId: " + userId;
	}

}
