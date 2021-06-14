/**
 * 
 */
package com.flipkart.exception;

/**
 * @author Tanmay
 *
 */
public class NoRegisteredCoursesException extends Exception {

	private String studentId;
	private int semester;

	/**
	 * Constructor
	 * 
	 * @param studentId, semester
	 */
	public NoRegisteredCoursesException(String studentId, int semester) {
		this.studentId = studentId;
		this.semester = semester;
	}

	/**
	 * Getter method
	 * 
	 * @return student id
	 */
	public String getstudentId() {
		return studentId;
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
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() {
		return "No registered course found for student id: " + studentId + " , semester: " + semester;
	}

}
