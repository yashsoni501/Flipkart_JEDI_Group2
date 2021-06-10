/**
 * 
 */
package com.flipkart.bean;

/**
 * @author Nihal
 *
 */
public class Payment {
	
	private String student_id;
	private String status;
	private int semester;
	private String session;
	private String timestamp;
	private float amount;
	private String payment_method;
	private String reference_id;
	
	/**
	 * @return the student_id
	 */
	public String getStudent_id() {
		return student_id;
	}
	/**
	 * @param student_id the student_id to set
	 */
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
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
	 * @return the payment_method
	 */
	public String getPayment_method() {
		return payment_method;
	}
	/**
	 * @param payment_method the payment_method to set
	 */
	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}
	/**
	 * @return the reference_id
	 */
	public String getReference_id() {
		return reference_id;
	}
	/**
	 * @param reference_id the reference_id to set
	 */
	public void setReference_id(String reference_id) {
		this.reference_id = reference_id;
	}
	
	
}
