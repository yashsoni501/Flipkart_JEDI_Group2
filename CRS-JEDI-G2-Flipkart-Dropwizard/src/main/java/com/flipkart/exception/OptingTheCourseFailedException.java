/**
 * 
 */
package com.flipkart.exception;

/**
 * Exception to check if professor failed in opting a course
 * 
 * @author jagru
 *
 */
public class OptingTheCourseFailedException extends Exception {

	private String courseCode;

	public OptingTheCourseFailedException(String courseCode) {
		this.courseCode = courseCode;
	}

	/**
	 * @return the courseCode
	 */
	public String getCourseCode() {
		return courseCode;
	}

	@Override
	public String getMessage() {
		return "Opting the Course with Course ID : " + courseCode
				+ "failed as the course may be already opted or the course is unavailable.";
	}

}
