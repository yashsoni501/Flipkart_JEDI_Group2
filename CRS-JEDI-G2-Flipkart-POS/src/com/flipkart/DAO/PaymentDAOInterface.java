/**
 * 
 */
package com.flipkart.DAO;

import java.sql.SQLException;

import com.flipkart.bean.Payment;

// TODO: Auto-generated Javadoc
/**
 * The Interface PaymentDAOInterface.
 *
 * @author aysh
 */
public interface PaymentDAOInterface {

	/**
	 * Gets the fee reciept.
	 *
	 * @param studentId the student id
	 * @param semester  the semester
	 * @return the fee reciept
	 * @throws SQLException the SQL exception
	 */
	public Payment getFeeReciept(String studentId, int semester) throws SQLException;

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
