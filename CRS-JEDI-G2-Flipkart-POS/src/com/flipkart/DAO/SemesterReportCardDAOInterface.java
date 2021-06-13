/**
 * 
 */
package com.flipkart.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import com.flipkart.bean.SemesterReportCard;

/**
 * @author yashsoni501
 *
 */
public interface SemesterReportCardDAOInterface {

	boolean addSemesterReportCard(String studentId, int semester, float sgpa) throws SQLException;

	ArrayList<SemesterReportCard> getSemesterReportCardByStudentId(String studentId) throws SQLException;
}
