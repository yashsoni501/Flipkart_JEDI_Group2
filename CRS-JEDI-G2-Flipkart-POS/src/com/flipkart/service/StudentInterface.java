/**
 * 
 */
package com.flipkart.service;

import java.util.ArrayList;

import com.flipkart.bean.Course;
import com.flipkart.bean.ReportCard;

/**
 * @author aysh
 *
 */
public interface StudentInterface {
	
	public ArrayList<Course> fetchRegisteredCourses(String studentId, int semester);
	public boolean payFee(String studentId, int modeOfPayment);
	public boolean isFeePaid(String studentId, int semester);
	public ReportCard viewReportCard(String studentId);
	public void getAllStudents(String session);
}