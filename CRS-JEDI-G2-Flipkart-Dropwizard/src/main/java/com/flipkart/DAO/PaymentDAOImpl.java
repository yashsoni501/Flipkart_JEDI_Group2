/**
 * 
 */
package com.flipkart.DAO;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.flipkart.bean.Payment;
import com.flipkart.constant.Constants;
import com.flipkart.utils.DBUtils;
import com.flipkart.constant.SQLQuery;
import com.flipkart.exception.FeeRecieptNotFoundException;

/**
 * The Class PaymentDAOImpl.
 *
 * @author yashsoni501
 */
public class PaymentDAOImpl implements PaymentDAOInterface {

	Logger logger = LoggerFactory.getLogger(PaymentDAOImpl.class.getName());
	/** The instance. */
	private static volatile PaymentDAOImpl instance = null;

	/**
	 * Method to make PaymentDAOImpl Singleton.
	 *
	 * @return single instance of PaymentDAOImpl
	 */
	public static PaymentDAOImpl getInstance() {
		if (instance == null) {
			synchronized (PaymentDAOImpl.class) {
				instance = new PaymentDAOImpl();
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
		Connection conn = DBUtils.getConnection();
		Payment feePayment = new Payment();

		PreparedStatement stmt = conn.prepareStatement(SQLQuery.GET_FEE_RECIEPT);
		stmt.setString(1, studentId);
		stmt.setInt(2, semester);
		stmt.setString(3, Constants.PAYMENT_SUCCESS);

		ResultSet rs = stmt.executeQuery();

		if (!rs.next()) {
			
			FeeRecieptNotFoundException e = new FeeRecieptNotFoundException(studentId, semester);
			logger.error(e.getMessage());
			throw e;
			
		} else {
			feePayment.setStudentId(studentId);
			feePayment.setStatus(rs.getString("status"));
			feePayment.setSemester(semester);
			feePayment.setDateOfPayment(rs.getString("dateOfPayment"));
			feePayment.setAmount(rs.getFloat("amount"));
			feePayment.setModeOfPayment(rs.getString("modeOfPayment"));
			feePayment.setReferenceId(rs.getString("referenceId"));
			return feePayment;
		}
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
