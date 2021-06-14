package com.flipkart.exception;

/**
 * @author Tanmay
 *
 */
public class EmptyCourseCatalogListExcpetion extends Exception {

	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() {
		return "Course catalog list is empty";

	}

}
