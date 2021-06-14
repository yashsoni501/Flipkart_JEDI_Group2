/**
 * 
 */
package com.flipkart.exception;

/**
 * @author yashsoni501
 *
 */
public class FeeRecieptNotFoundException extends Exception {

	private String studentId;
	private int semester;

	public FeeRecieptNotFoundException(String studentId, int semester) {
		this.studentId = studentId;
		this.semester = semester;
	}

	public String getMessage() {
		return "No fee reciept found for student: " + this.studentId + " for semester " + this.semester;
	}
}
