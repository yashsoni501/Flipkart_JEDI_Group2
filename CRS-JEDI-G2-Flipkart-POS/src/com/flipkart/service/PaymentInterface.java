/**
 * 
 */
package com.flipkart.service;

/**
 * @author yashsoni501
 *
 */
public interface PaymentInterface {
	
	public void sendNotification();
	public void initiatePayment();
	public void generateFeePayment();
}
