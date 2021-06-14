/**
 * 
 */
package com.flipkart.exception;

/**
 * @author Tanmay
 *
 */
public class InvalidCCSessionSemesterException extends Exception {
	String session;
	int semester;

	public InvalidCCSessionSemesterException(String session, int semester) {
		this.session = session;
		this.semester = semester;
	}

	/**
	 * Getter function for semester
	 * 
	 * @return
	 */
	public int getSemester() {
		return semester;
	}

	/**
	 * Getter function for session
	 * 
	 * @return
	 */
	public String getSession() {
		return session;
	}

	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() {
		return "No Course Catalog entry with semester: " + semester + " and session: " + session + " not found.";
	}

}
