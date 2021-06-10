/**
 * 
 */
package com.flipkart.bean;

/**
 * @author Nihal
 *
 */
public class OnlinePayment {
	
	private String transaction_type;
	private String transaction_id;
	private String timestamp;
	
	/**
	 * @return the transaction_type
	 */
	public String getTransaction_type() {
		return transaction_type;
	}
	/**
	 * @param transaction_type the transaction_type to set
	 */
	public void setTransaction_type(String transaction_type) {
		this.transaction_type = transaction_type;
	}
	/**
	 * @return the transaction_id
	 */
	public String getTransaction_id() {
		return transaction_id;
	}
	/**
	 * @param transaction_id the transaction_id to set
	 */
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
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
