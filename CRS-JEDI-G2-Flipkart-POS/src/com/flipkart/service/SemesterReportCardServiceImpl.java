package com.flipkart.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.flipkart.DAO.SemesterReportCardDAOImpl;
import com.flipkart.DAO.SemesterReportCardDAOInterface;
import com.flipkart.bean.SemesterReportCard;
import com.flipkart.exception.SemesterReportCardNotFound;

/**
 * The Class SemesterReportCardServiceImpl.
 */
public class SemesterReportCardServiceImpl implements SemesterReportCardInterface {

	/** The instance. */
	public static volatile SemesterReportCardServiceImpl instance = null;

	/** The report instance. */
	SemesterReportCardDAOInterface reportInstance = SemesterReportCardDAOImpl.getInstance();

	/**
	 * Gets the single instance of SemesterReportCardServiceImpl.
	 *
	 * @return single instance of SemesterReportCardServiceImpl
	 */
	public static SemesterReportCardServiceImpl getInstance() {
		if (instance == null) {
			// This is a synchronized block, when multiple threads will access this instance
			synchronized (SemesterReportCardServiceImpl.class) {
				instance = new SemesterReportCardServiceImpl();
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

		return reportInstance.addSemesterReportCard(studentId, semester, sgpa);
	}

	/**
	 * Gets the semester report card by student id.
	 *
	 * @param userId the user id
	 * @return the semester report card by student id
	 * @throws SQLException               the SQL exception
	 * @throws SemesterReportCardNotFound the semester report card not found
	 */
	@Override
	public ArrayList<SemesterReportCard> getSemesterReportCardByStudentId(String userId)
			throws SQLException, SemesterReportCardNotFound {

		return reportInstance.getSemesterReportCardByStudentId(userId);
	}

}
