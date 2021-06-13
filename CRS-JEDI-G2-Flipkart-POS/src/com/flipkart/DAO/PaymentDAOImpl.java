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
import com.flipkart.utils.DBUtils;

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
		final String GET_FEE_RECIEPT = "SELECT * FROM payment WHERE stuid=? AND semester=?";

		PreparedStatement stmt = conn.prepareStatement(GET_FEE_RECIEPT);
		stmt.setString(1, studentId);
		stmt.setInt(2, semester);

		ResultSet rs = stmt.executeQuery();
		String status = null;
		if (rs.getFetchSize() == 0) {
			status = "NOTPAID";
		} else {
			status = "PAID";
		}
		rs.next();
		feePayment.setStudentId(studentId);
		feePayment.setStatus(status);
		feePayment.setSemester(semester);
		feePayment.setDateOfPayment(rs.getString("dateOfPayment"));
		feePayment.setAmount(rs.getFloat("amount"));
		feePayment.setModeOfPayment(rs.getString("modeOfPayment"));
		feePayment.setReferenceId(rs.getString("referenceId"));
		return feePayment;
	}

	@Override
	public Payment onlinePayment(String studentId, float amount, int semester) throws SQLException {
		Connection conn = DBUtils.getConnection();
		String transactionId = "onl123";
		PreparedStatement stmt = conn.prepareStatement("insert into payment values(?,?,?,?,?,?,?)");

		stmt.setString(1, transactionId);
		stmt.setString(2, studentId);
		stmt.setString(3, "success");
		stmt.setFloat(4, amount);
		Date today = new Date();
		stmt.setString(5, today.toString());
		stmt.setInt(6, semester);
		stmt.setString(7, "ONLINE");
		stmt.executeUpdate();

		Payment paymentInfo = new Payment();
		paymentInfo.setStudentId(studentId);
		paymentInfo.setStatus("success");
		paymentInfo.setSemester(semester);
		paymentInfo.setDateOfPayment(today.toString());
		paymentInfo.setReferenceId(transactionId);

		return paymentInfo;
	}

	@Override
	public Payment offlinePayment(String studentId, float amount, int semester) throws SQLException {
		Connection conn = DBUtils.getConnection();
		String transactionId = "onl123";
		PreparedStatement stmt = conn.prepareStatement("insert into payment values(?,?,?,?,?,?,?)");

		stmt.setString(1, transactionId);
		stmt.setString(2, studentId);
		stmt.setString(3, "success");
		stmt.setFloat(4, amount);
		Date today = new Date();
		stmt.setString(5, today.toString());
		stmt.setInt(6, semester);
		stmt.setString(7, "OFFLINE");
		stmt.executeUpdate();

		Payment paymentInfo = new Payment();
		paymentInfo.setStudentId(studentId);
		paymentInfo.setStatus("success");
		paymentInfo.setSemester(semester);
		paymentInfo.setDateOfPayment(today.toString());
		paymentInfo.setReferenceId(transactionId);

		return paymentInfo;
	}
}
