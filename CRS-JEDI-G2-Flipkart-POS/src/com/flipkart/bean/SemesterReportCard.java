/**
 * 
 */
package com.flipkart.bean;

/**
 * @author jagru
 *
 */
public class SemesterReportCard {

	private String studentID;
	private float sgpa;
	private String currentSem;

	/**
	 * @return the studentID
	 */
	public String getStudentID() {
		return studentID;
	}

	/**
	 * @param studentID the studentID to set
	 */
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	/**
	 * @return the sgpa
	 */
	public float getSgpa() {
		return sgpa;
	}

	/**
	 * @param sgpa the sgpa to set
	 */
	public void setSgpa(float sgpa) {
		this.sgpa = sgpa;
	}

	/**
	 * @return the currentSem
	 */
	public String getCurrentSem() {
		return currentSem;
	}

	/**
	 * @param currentSem the currentSem to set
	 */
	public void setCurrentSem(String currentSem) {
		this.currentSem = currentSem;
	}
}
