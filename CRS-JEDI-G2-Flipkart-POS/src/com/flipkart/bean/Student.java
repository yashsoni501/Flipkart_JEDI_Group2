/**
 * 
 */
package com.flipkart.bean;

/**
 * @author jagru
 *
 */
public class Student {

	private int studentID;
	private String studentName;
	private String studentAddress;
	private String department;
	private String emailID;
	private String session;
	
	/**
	 * @return the emailID
	 */
	public String getEmailID() {
		return emailID;
	}
	/**
	 * @param emailID the emailID to set
	 */
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	/**
	 * @return the studentID
	 */
	public int getStudentID() {
		return studentID;
	}
	/**
	 * @param studentID the studentID to set
	 */
	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}
	/**
	 * @return the studentName
	 */
	public String getStudentName() {
		return studentName;
	}
	/**
	 * @param studentName the studentName to set
	 */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	/**
	 * @return the studentAddress
	 */
	public String getStudentAddress() {
		return studentAddress;
	}
	/**
	 * @param studentAddress the studentAddress to set
	 */
	public void setStudentAddress(String studentAddress) {
		this.studentAddress = studentAddress;
	}
	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}
	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}
	/**
	 * @return the session
	 */
	public String getSession() {
		return session;
	}
	/**
	 * @param session the session to set
	 */
	public void setSession(String session) {
		this.session = session;
	}
	
	
}