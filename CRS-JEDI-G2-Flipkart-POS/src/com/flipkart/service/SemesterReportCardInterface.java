/**
 * 
 */
package com.flipkart.service;

import java.util.ArrayList;

import com.flipkart.bean.SemesterReportCard;

/**
 * @author aysh
 *
 */
public interface SemesterReportCardInterface {

	boolean addSemesterReportCard(String studentID, int semester, float sgpa);

	ArrayList<SemesterReportCard> getSemesterReportCardByStudentId(String userId);

}
