/**
 * 
 */
package com.flipkart.bean;

/**
 * @author jagru
 *
 */
public class Professor {

	private int professorID;
	private String professorName;
	private String professorAddress;
	private String department;
	private String emailID;
	/**
	 * @return the professorID
	 */
	public int getProfessorID() {
		return professorID;
	}
	/**
	 * @param professorID the professorID to set
	 */
	public void setProfessorID(int professorID) {
		this.professorID = professorID;
	}
	/**
	 * @return the professorName
	 */
	public String getProfessorName() {
		return professorName;
	}
	/**
	 * @param professorName the professorName to set
	 */
	public void setProfessorName(String professorName) {
		this.professorName = professorName;
	}
	/**
	 * @return the professorAddress
	 */
	public String getProfessorAddress() {
		return professorAddress;
	}
	/**
	 * @param professorAddress the professorAddress to set
	 */
	public void setProfessorAddress(String professorAddress) {
		this.professorAddress = professorAddress;
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
	
}
