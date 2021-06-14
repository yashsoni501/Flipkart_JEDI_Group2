/**
 * 
 */
package com.flipkart.exception;

/**
 * @author Tanmay
 *
 */
public class EmptyCourseListExcpetion extends Exception {

	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() {
		return "Course list is empty";

	}

}
