/**
 * 
 */
package com.flipkart.service;

import java.sql.SQLException;

import com.flipkart.DAO.PaymentDAOImpl;
import com.flipkart.DAO.PaymentDAOInterface;
import com.flipkart.bean.Payment;
import com.flipkart.exception.FeeRecieptNotFoundException;

/**
 * The Class PaymentServiceImpl.
 *
 * @author yashsoni501
 */
public class PaymentServiceImpl implements PaymentInterface {

	/** The instance. */
	public static volatile PaymentServiceImpl instance = null;

	/** The payment DAO. */
	PaymentDAOInterface paymentDAO = PaymentDAOImpl.getInstance();

	/**
	 * Gets the single instance of PaymentServiceImpl.
	 *
	 * @return single instance of PaymentServiceImpl
	 */
	public static PaymentServiceImpl getInstance() {
		if (instance == null) {
			// This is a synchronized block, when multiple threads will access this instance
			synchronized (PaymentServiceImpl.class) {
				instance = new PaymentServiceImpl();
			}
		}
		return instance;
	}

	/**
	 * Gets the fee reciept.
	 *
	 * @param studentId the student id
	 * @param semester  the semester
	 * @return the fee reciept
	 * @throws SQLException the SQL exception
	 */
	@Override
	public Payment getFeeReciept(String studentId, int semester) throws SQLException, FeeRecieptNotFoundException {
		// Auto-generated method stub
		return paymentDAO.getFeeReciept(studentId, semester);
	}

	/**
	 * Online payment.
	 *
	 * @param studentId the student id
	 * @param amount    the amount
	 * @param semester  the semester
	 * @return the payment
	 * @throws SQLException the SQL exception
	 */
	@Override
	public Payment onlinePayment(String studentId, float amount, int semester) throws SQLException {

		return paymentDAO.onlinePayment(studentId, amount, semester);
	}

	/**
	 * Offline payment.
	 *
	 * @param studentId the student id
	 * @param amount    the amount
	 * @param semester  the semester
	 * @return the payment
	 * @throws SQLException the SQL exception
	 */
	@Override
	public Payment offlinePayment(String studentId, float amount, int semester) throws SQLException {

		return paymentDAO.offlinePayment(studentId, amount, semester);
	}

}
