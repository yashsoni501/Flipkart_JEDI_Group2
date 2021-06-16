package com.flipkart.exception;

/**
 * Exception to check if course is available in catalog
 * 
 * @author Aeron
 *
 */
public class CourseNotFoundException extends Exception {
	private String courseid;

	public CourseNotFoundException(String courseid) {
		this.courseid = courseid;
	}

	/**
	 * Getter function for course id
	 * 
	 * @return
	 */
	public String getCourseId() {
		return courseid;
	}

	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() {
		return "Course with courseCode: " + courseid + " not found in Catalog";

	}
}
