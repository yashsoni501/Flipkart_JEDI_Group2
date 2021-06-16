/**
 * 
 */
package com.flipkart.exception;

/**
 * Exception to check if there are no enrolled students for a course and session
 * 
 * @author jagru
 *
 */
public class NoEnrolledStudentsException extends Exception {

	private String courseId;
	private String session;

	public NoEnrolledStudentsException(String courseId, String session) {
		this.courseId = courseId;
		this.session = session;
	}

	/**
	 * @return the courseId
	 */
	public String getCourseId() {
		return courseId;
	}

	/**
	 * @return the session
	 */
	public String getSession() {
		return session;
	}

	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() {
		return "No Enrolled Students Found for Course ID : " + courseId + "and session : " + session + ".";
	}

}
