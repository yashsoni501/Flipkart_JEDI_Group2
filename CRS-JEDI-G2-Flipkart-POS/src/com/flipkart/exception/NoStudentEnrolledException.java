/**
 * 
 */
package com.flipkart.exception;

/**
 * @author Tanmay
 *
 */

public class NoStudentEnrolledException extends Exception {

	private String courseCode;
	private int semester;
	private String session;

	/**
	 * Constructor
	 * 
	 * @param courseCode, semester, session
	 */
	public NoStudentEnrolledException(String courseCode, int semester, String session) {
		this.courseCode = courseCode;
		this.semester = semester;
		this.session = session;
	}

	/**
	 * Getter method
	 * 
	 * @return course code
	 */
	public String getCourseCode() {
		return courseCode;
	}

	/**
	 * Getter method
	 * 
	 * @return semester
	 */
	public int getSemester() {
		return semester;
	}

	/**
	 * Getter method
	 * 
	 * @return session
	 */
	public String getSession() {
		return session;
	}

	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() {
		return "No student entries found for course code: " + courseCode + " , semester: " + semester + " , session: "
				+ session;
	}

}
