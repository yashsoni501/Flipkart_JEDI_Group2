/**
 * 
 */
package com.flipkart.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.flipkart.bean.SemesterReportCard;
import com.flipkart.exception.SemesterReportCardNotFound;

/**
 * The Interface SemesterReportCardInterface.
 *
 * @author aysh
 */
public interface SemesterReportCardInterface {

	/**
	 * Adds the semester report card.
	 *
	 * @param studentID the student ID
	 * @param semester  the semester
	 * @param sgpa      the sgpa
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 */
	boolean addSemesterReportCard(String studentID, int semester, float sgpa) throws SQLException;

	/**
	 * Gets the semester report card by student id.
	 *
	 * @param userId the user id
	 * @return the semester report card by student id
	 * @throws SQLException               the SQL exception
	 * @throws SemesterReportCardNotFound the semester report card not found
	 */
	ArrayList<SemesterReportCard> getSemesterReportCardByStudentId(String userId)
			throws SQLException, SemesterReportCardNotFound;

}
