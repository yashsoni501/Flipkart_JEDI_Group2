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
 * @author yashsoni501
 *
 */
public class PaymentServiceImpl implements PaymentInterface {

	public static volatile PaymentServiceImpl instance = null;
	PaymentDAOInterface paymentDAO = PaymentDAOImpl.getInstance();

	public static PaymentServiceImpl getInstance() {
		if (instance == null) {
			// This is a synchronized block, when multiple threads will access this instance
			synchronized (PaymentServiceImpl.class) {
				instance = new PaymentServiceImpl();
			}
		}
		return instance;
	}

	@Override
	public Payment getFeeReciept(String studentId, int semester) throws SQLException, FeeRecieptNotFoundException {
		// Auto-generated method stub
		return paymentDAO.getFeeReciept(studentId, semester);
	}

	@Override
	public Payment onlinePayment(String studentId, float amount, int semester) throws SQLException {

		return paymentDAO.onlinePayment(studentId, amount, semester);
	}

	@Override
	public Payment offlinePayment(String studentId, float amount, int semester) throws SQLException {

		return paymentDAO.offlinePayment(studentId, amount, semester);
	}

}
