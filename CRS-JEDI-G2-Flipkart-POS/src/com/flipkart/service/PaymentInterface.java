/**
 * 
 */
package com.flipkart.service;

import java.sql.SQLException;

import com.flipkart.bean.Payment;

/**
 * @author yashsoni501
 *
 */
public interface PaymentInterface {

	public Payment getFeeReciept(String studentId, int semester) throws SQLException;

	public Payment onlinePayment(String studentId, float amount, int semester) throws SQLException;

	public Payment offlinePayment(String studentId, float amount, int semester) throws SQLException;
}
