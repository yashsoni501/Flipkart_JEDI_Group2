/**
 * 
 */
package com.flipkart.DAO;

import java.sql.SQLException;

import com.flipkart.bean.Payment;

/**
 * @author aysh
 *
 */
public interface PaymentDAOInterface {

	public Payment getFeeReciept(String studentId, int semester) throws SQLException;
	public String onlinePayment(String studentId, float amount, int semester) throws SQLException;
	public String offlinePayment(String studentId, float amount, int semester) throws SQLException;
}
