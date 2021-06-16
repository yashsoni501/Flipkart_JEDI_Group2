/**
 * 
 */
package com.flipkart.exception;

/**
 * @author Tanmay
 *
 */
public class CourseCatalogEntryNotFoundException extends Exception {
	private String courseCode;

	public CourseCatalogEntryNotFoundException(String courseCode) {
		this.courseCode = courseCode;
	}

	/**
	 * Getter function for course code
	 * 
	 * @return
	 */
	public String getCourseCode() {
		return courseCode;
	}

	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() {
		return "Course Catalog entry with courseCode: " + courseCode + " not found.";
	}

}
