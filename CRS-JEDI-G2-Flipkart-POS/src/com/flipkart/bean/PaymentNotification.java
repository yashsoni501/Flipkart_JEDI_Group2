/**
 * 
 */
package com.flipkart.bean;

/**
 * @author Nihal
 *
 */
public class PaymentNotification {
	
	private String student_id;
	private String notification_id;
	private String notification_msg;
	private String timestamp;
	
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
	 * @return the notification_id
	 */
	public String getNotification_id() {
		return notification_id;
	}
	/**
	 * @param notification_id the notification_id to set
	 */
	public void setNotification_id(String notification_id) {
		this.notification_id = notification_id;
	}
	/**
	 * @return the notification_msg
	 */
	public String getNotification_msg() {
		return notification_msg;
	}
	/**
	 * @param notification_msg the notification_msg to set
	 */
	public void setNotification_msg(String notification_msg) {
		this.notification_msg = notification_msg;
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
	
	
	
}
