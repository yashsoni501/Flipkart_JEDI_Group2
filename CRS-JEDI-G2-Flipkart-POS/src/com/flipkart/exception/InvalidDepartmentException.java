/**
 * 
 */
package com.flipkart.exception;

/**
 * @author Tanmay
 *
 */
public class InvalidDepartmentException extends Exception {
	String department;

	public InvalidDepartmentException(String department) {
		this.department = department;
	}

	/**
	 * Getter function for department
	 * 
	 * @return
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() {
		return "Course Catalog entries with department: " + department + " not found.";
	}

}
