/**
 * 
 */
package com.flipkart.service;

import java.sql.SQLException;

import com.flipkart.bean.Payment;
import com.flipkart.exception.FeeRecieptNotFoundException;

/**
 * The Interface PaymentInterface.
 *
 * @author yashsoni501
 */
public interface PaymentInterface {

	/**
	 * Gets the fee reciept.
	 *
	 * @param studentId the student id
	 * @param semester  the semester
	 * @return the fee reciept
	 * @throws SQLException                the SQL exception
	 * @throws FeeRecieptNotFoundException when no repeipt found
	 */
	public Payment getFeeReciept(String studentId, int semester) throws SQLException, FeeRecieptNotFoundException;

	/**
	 * Online payment.
	 *
	 * @param studentId the student id
	 * @param amount    the amount
	 * @param semester  the semester
	 * @return the payment
	 * @throws SQLException the SQL exception
	 */
	public Payment onlinePayment(String studentId, float amount, int semester) throws SQLException;

	/**
	 * Offline payment.
	 *
	 * @param studentId the student id
	 * @param amount    the amount
	 * @param semester  the semester
	 * @return the payment
	 * @throws SQLException the SQL exception
	 */
	public Payment offlinePayment(String studentId, float amount, int semester) throws SQLException;
}
