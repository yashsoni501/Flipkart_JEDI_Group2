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
	private int currentSem;

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
	public int getCurrentSem() {
		return currentSem;
	}

	/**
	 * @param currentSem the currentSem to set
	 */
	public void setCurrentSem(int currentSem) {
		this.currentSem = currentSem;
	}

}
