/**
 * 
 */
package com.flipkart.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import com.flipkart.bean.SemesterReportCard;
import com.flipkart.exception.SemesterReportCardNotFound;

/**
 * @author yashsoni501
 *
 */
public interface SemesterReportCardDAOInterface {

	boolean addSemesterReportCard(String studentId, int semester, float sgpa) throws SQLException;

	ArrayList<SemesterReportCard> getSemesterReportCardByStudentId(String studentId)
			throws SQLException, SemesterReportCardNotFound;
}
