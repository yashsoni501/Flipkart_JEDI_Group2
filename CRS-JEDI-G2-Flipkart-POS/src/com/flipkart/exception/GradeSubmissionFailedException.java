/**
 * 
 */
package com.flipkart.exception;

/**
 * Exception to check if the grade was not successfully submitted by professor
 * 
 * @author jagru
 *
 */
public class GradeSubmissionFailedException extends Exception {

	private String courseId;
	private String studentId;

	public GradeSubmissionFailedException(String courseId, String studentId) {
		this.courseId = courseId;
		this.studentId = studentId;
	}

	/**
	 * @return the courseId
	 */
	public String getCourseId() {
		return courseId;
	}

	/**
	 * @return the studentId
	 */
	public String getStudentId() {
		return studentId;
	}

	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() {
		return "Grade submission failed for Student ID : " + studentId + "Course ID : " + courseId + ".";
	}

}
