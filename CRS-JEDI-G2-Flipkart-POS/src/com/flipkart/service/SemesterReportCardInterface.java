/**
 * 
 */
package com.flipkart.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.flipkart.bean.SemesterReportCard;
import com.flipkart.exception.SemesterReportCardNotFound;

/**
 * @author aysh
 *
 */
public interface SemesterReportCardInterface {

	boolean addSemesterReportCard(String studentID, int semester, float sgpa) throws SQLException;

	ArrayList<SemesterReportCard> getSemesterReportCardByStudentId(String userId) throws SQLException, SemesterReportCardNotFound;

}
