/**
 * 
 */
package com.flipkart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.flipkart.bean.SemesterReportCard;
import com.flipkart.utils.DBUtils;
import com.flipkart.constant.SQLQuery;

/**
 * @author yashsoni501
 *
 */
public class SemesterReportCardDAOImpl implements SemesterReportCardDAOInterface {

	private static volatile SemesterReportCardDAOImpl instance = null;

	/**
	 * Method to make SemesterReportCardDAOImpl Singleton
	 * 
	 * @return
	 */
	public static SemesterReportCardDAOImpl getInstance() {
		if (instance == null) {
			synchronized (SemesterReportCardDAOImpl.class) {
				instance = new SemesterReportCardDAOImpl();
			}
		}
		return instance;
	}

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

	@Override
	public ArrayList<SemesterReportCard> getSemesterReportCardByStudentId(String studentId) throws SQLException {

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

		return allReports;
	}

}
