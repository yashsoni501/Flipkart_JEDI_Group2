/**
 * 
 */
package com.flipkart.exception;

/**
 * Exception to check if there are no opted courses
 * 
 * @author jagru
 *
 */
public class NoOptedCoursesException extends Exception {

	private String professorId;

	public NoOptedCoursesException(String professorId) {
		this.professorId = professorId;
	}

	/**
	 * @return the professorId
	 */
	public String getProfessorId() {
		return professorId;
	}

	@Override
	public String getMessage() {
		return "No Opted Courses Found for Professor ID : " + professorId + ".";
	}
}
