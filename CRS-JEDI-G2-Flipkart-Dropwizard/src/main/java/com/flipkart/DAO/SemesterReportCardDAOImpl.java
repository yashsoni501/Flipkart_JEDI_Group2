/**
 * 
 */
package com.flipkart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.flipkart.bean.SemesterReportCard;
import com.flipkart.utils.DBUtils;
import com.flipkart.constant.SQLQuery;
import com.flipkart.exception.SemesterReportCardNotFound;

/**
 * The Class SemesterReportCardDAOImpl.
 *
 * @author yashsoni501
 */
public class SemesterReportCardDAOImpl implements SemesterReportCardDAOInterface {

	Logger logger = Logger.getLogger(SemesterReportCardDAOImpl.class.getName());
	/** The instance. */
	private static volatile SemesterReportCardDAOImpl instance = null;

	/**
	 * Method to make SemesterReportCardDAOImpl Singleton.
	 *
	 * @return single instance of SemesterReportCardDAOImpl
	 */
	public static SemesterReportCardDAOImpl getInstance() {
		if (instance == null) {
			synchronized (SemesterReportCardDAOImpl.class) {
				instance = new SemesterReportCardDAOImpl();
			}
		}
		return instance;
	}

	/**
	 * Adds the semester report card.
	 *
	 * @param studentId the student id
	 * @param semester  the semester
	 * @param sgpa      the sgpa
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 */
	@Override
	public boolean addSemesterReportCard(String studentId, int semester, float sgpa) throws SQLException {

		Connection conn = DBUtils.getConnection();

		PreparedStatement stmt = conn.prepareStatement(SQLQuery.ADD_SEMESTER_REPORT);

		stmt.setString(1, studentId);
		stmt.setFloat(2, sgpa);
		stmt.setInt(3, semester);

		if (stmt.executeUpdate() > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Gets the semester report card by student id.
	 *
	 * @param studentId the student id
	 * @return the semester report card by student id
	 * @throws SQLException               the SQL exception
	 * @throws SemesterReportCardNotFound the semester report card not found
	 */
	@Override
	public ArrayList<SemesterReportCard> getSemesterReportCardByStudentId(String studentId)
			throws SQLException, SemesterReportCardNotFound {

		ArrayList<SemesterReportCard> allReports = new ArrayList<SemesterReportCard>();

		Connection conn = DBUtils.getConnection();

		PreparedStatement stmt = conn.prepareStatement(SQLQuery.GET_ALL_REPORTS);

		stmt.setString(1, studentId);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			SemesterReportCard report = new SemesterReportCard();

			report.setCurrentSem(rs.getInt("semester"));
			report.setSgpa(rs.getFloat("sgpa"));
			report.setStudentID(studentId);

			allReports.add(report);
		}

		if (allReports.size() == 0) {
			SemesterReportCardNotFound e = new SemesterReportCardNotFound(studentId);
			logger.error(e.getMessage());
			throw e;
		}
		return allReports;
	}

}