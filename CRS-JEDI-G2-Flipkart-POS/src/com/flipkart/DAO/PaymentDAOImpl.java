/**
 * 
 */
package com.flipkart.DAO;

import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.flipkart.bean.Payment;

/**
 * @author yashsoni501
 *
 */
public class PaymentDAOImpl implements PaymentDAOInterface {

	private static volatile PaymentDAOImpl instance = null;
	DAOConnectionInterface instanceDAO = new DAOConnectionImpl();

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

		Payment feePayment = new Payment();
		PreparedStatement stmt = instanceDAO.conn.prepareStatement("SELECT * FROM payment WHERE stuid="
				+ studentId.toString() + "AND semster=" + String.valueOf(semester));
		ResultSet rs = stmt.executeQuery("SELECT id, name ,address, location FROM payment");
		String status = null;
		if (rs.getFetchSize() == 0) {
			status = "NOTPAID";
		} else {
			status = "PAID";
		}
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

		String transactionId = "onl123";
		PreparedStatement stmt = instanceDAO.conn.prepareStatement("insert into employee values(?,?,?,?,?,?,?)");

		stmt.setString(1, transactionId);
		stmt.setString(2, studentId);
		stmt.setString(3, "success");
		stmt.setFloat(4, amount);
		Date today = new Date();
		stmt.setString(5, today.toString());
		stmt.setInt(6, semester);
		stmt.setString(7, "ONLINE");
		stmt.executeUpdate();

		return new Payment();
	}

	@Override
	public Payment offlinePayment(String studentId, float amount, int semester) throws SQLException {

		String transactionId = "onl123";
		PreparedStatement stmt = instanceDAO.conn.prepareStatement("insert into employee values(?,?,?,?,?,?,?)");

		stmt.setString(1, transactionId);
		stmt.setString(2, studentId);
		stmt.setString(3, "success");
		stmt.setFloat(4, amount);
		Date today = new Date();
		stmt.setString(5, today.toString());
		stmt.setInt(6, semester);
		stmt.setString(7, "OFFLINE");
		stmt.executeUpdate();

		return new Payment();
	}
}
