/**
 * 
 */
package com.flipkart.DAO;

import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.flipkart.bean.Payment;
import com.flipkart.constant.Constants;
import com.flipkart.utils.DBUtils;
import com.flipkart.constant.SQLQuery;

/**
 * @author yashsoni501
 *
 */
public class PaymentDAOImpl implements PaymentDAOInterface {
	private static volatile PaymentDAOImpl instance = null;

	/**
	 * Method to make PaymentDAOImpl Singleton
	 * 
	 * @return
	 */
	public static PaymentDAOImpl getInstance() {
		if (instance == null) {
			synchronized (PaymentDAOImpl.class) {
				instance = new PaymentDAOImpl();
			}
		}
		return instance;
	}

	@Override
	public Payment getFeeReciept(String studentId, int semester) throws SQLException {
		Connection conn = DBUtils.getConnection();
		Payment feePayment = new Payment();

		PreparedStatement stmt = conn.prepareStatement(SQLQuery.GET_FEE_RECIEPT);
		stmt.setString(1, studentId);
		stmt.setInt(2, semester);
		stmt.setString(3, Constants.PAYMENT_SUCCESS);

		ResultSet rs = stmt.executeQuery();

		if (!rs.next()) {
			feePayment.setStudentId(studentId);
			feePayment.setStatus(Constants.PAYMENT_FAILURE);
			feePayment.setSemester(semester);
		} else {
			feePayment.setStudentId(studentId);
			feePayment.setStatus(rs.getString("status"));
			feePayment.setSemester(semester);
			feePayment.setDateOfPayment(rs.getString("dateOfPayment"));
			feePayment.setAmount(rs.getFloat("amount"));
			feePayment.setModeOfPayment(rs.getString("modeOfPayment"));
			feePayment.setReferenceId(rs.getString("referenceId"));
		}
		return feePayment;
	}

	@Override
	public Payment onlinePayment(String studentId, float amount, int semester) throws SQLException {
		Connection conn = DBUtils.getConnection();
		String transactionId = "onl123";

		PreparedStatement stmt = conn.prepareStatement(SQLQuery.MAKE_PAYMENT);

		stmt.setString(1, transactionId);
		stmt.setString(2, studentId);
		stmt.setString(3, Constants.PAYMENT_SUCCESS);
		stmt.setFloat(4, amount);
		Date today = new Date();
		stmt.setString(5, today.toString());
		stmt.setInt(6, semester);
		stmt.setString(7, Constants.PAYMENT_ONLINE);
		stmt.executeUpdate();

		Payment paymentInfo = new Payment();
		paymentInfo.setStudentId(studentId);
		paymentInfo.setStatus(Constants.PAYMENT_SUCCESS);
		paymentInfo.setSemester(semester);
		paymentInfo.setModeOfPayment(Constants.PAYMENT_ONLINE);
		paymentInfo.setDateOfPayment(today.toString());
		paymentInfo.setReferenceId(transactionId);

		return paymentInfo;
	}

	@Override
	public Payment offlinePayment(String studentId, float amount, int semester) throws SQLException {
		Connection conn = DBUtils.getConnection();
		String transactionId = "onl123";
		PreparedStatement stmt = conn.prepareStatement(SQLQuery.MAKE_PAYMENT);

		stmt.setString(1, transactionId);
		stmt.setString(2, studentId);
		stmt.setString(3, Constants.PAYMENT_SUCCESS);
		stmt.setFloat(4, amount);
		Date today = new Date();
		stmt.setString(5, today.toString());
		stmt.setInt(6, semester);
		stmt.setString(7, Constants.PAYMENT_OFFLINE);
		stmt.executeUpdate();

		Payment paymentInfo = new Payment();
		paymentInfo.setStudentId(studentId);
		paymentInfo.setStatus(Constants.PAYMENT_SUCCESS);
		paymentInfo.setSemester(semester);
		paymentInfo.setModeOfPayment(Constants.PAYMENT_OFFLINE);
		paymentInfo.setDateOfPayment(today.toString());
		paymentInfo.setReferenceId(transactionId);

		return paymentInfo;
	}
}
