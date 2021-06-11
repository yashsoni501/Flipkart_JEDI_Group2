/**
 * 
 */
package com.flipkart.service;

import com.flipkart.bean.Payment;

/**
 * @author yashsoni501
 *
 */
public interface PaymentInterface {
	
	public boolean sendNotification(Payment payment);
	public boolean initiatePayment(String studentId, float amount, int semester, String session);
	public Payment generateFeeReciept(Payment payment);
	public String onlinePayment(String studentId, float amount);
	public String offlinePayment(String studentId, float amount);
}
