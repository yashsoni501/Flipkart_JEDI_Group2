/**
 * 
 */
package com.flipkart.DAO;

import java.sql.SQLException;

import com.flipkart.bean.Payment;
import com.flipkart.exception.FeeRecieptNotFoundException;

/**
 * @author aysh
 *
 */
public interface PaymentDAOInterface {

	public Payment getFeeReciept(String studentId, int semester) throws SQLException, FeeRecieptNotFoundException;

	public Payment onlinePayment(String studentId, float amount, int semester) throws SQLException;

	public Payment offlinePayment(String studentId, float amount, int semester) throws SQLException;
}
