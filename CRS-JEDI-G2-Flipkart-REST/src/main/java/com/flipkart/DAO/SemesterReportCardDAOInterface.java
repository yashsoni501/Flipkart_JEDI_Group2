/**
 * 
 */
package com.flipkart.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import com.flipkart.bean.SemesterReportCard;
import com.flipkart.exception.SemesterReportCardNotFound;

/**
 * The Interface SemesterReportCardDAOInterface.
 *
 * @author yashsoni501
 */
public interface SemesterReportCardDAOInterface {

	/**
	 * Adds the semester report card.
	 *
	 * @param studentId the student id
	 * @param semester  the semester
	 * @param sgpa      the sgpa
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 */
	boolean addSemesterReportCard(String studentId, int semester, float sgpa) throws SQLException;

	/**
	 * Gets the semester report card by student id.
	 *
	 * @param studentId the student id
	 * @return the semester report card by student id
	 * @throws SQLException               the SQL exception
	 * @throws SemesterReportCardNotFound the semester report card not found
	 */
	ArrayList<SemesterReportCard> getSemesterReportCardByStudentId(String studentId)
			throws SQLException, SemesterReportCardNotFound;
}
