/**
 * 
 */
package com.flipkart.bean;

/**
 * @author Nihal
 *
 */
public class Payment {
	
	private String studentId;
	private String status;
	private int semester;
	private String session;
	private String timestamp;
	private float amount;
	private String modeOfPayment;
	private String referenceId;
	/**
	 * @return the studentId
	 */
	public String getStudentId() {
		return studentId;
	}
	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the semester
	 */
	public int getSemester() {
		return semester;
	}
	/**
	 * @param semester the semester to set
	 */
	public void setSemester(int semester) {
		this.semester = semester;
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
	/**
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}
	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	/**
	 * @return the amount
	 */
	public float getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(float amount) {
		this.amount = amount;
	}
	/**
	 * @return the modeOfPayment
	 */
	public String getModeOfPayment() {
		return modeOfPayment;
	}
	/**
	 * @param modeOfPayment the modeOfPayment to set
	 */
	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}
	/**
	 * @return the referenceId
	 */
	public String getReferenceId() {
		return referenceId;
	}
	/**
	 * @param referenceId the referenceId to set
	 */
	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}
		
}
