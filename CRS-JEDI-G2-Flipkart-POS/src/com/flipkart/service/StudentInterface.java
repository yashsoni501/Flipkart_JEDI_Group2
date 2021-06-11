/**
 * 
 */
package com.flipkart.service;

import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.ReportCard;

/**
 * @author aysh
 *
 */
public interface StudentInterface {
	
	public List<Course> fetchRegisteredCourses(int studentId);

	public boolean FeePaid(int studentId, int semester);
	
	public boolean viewFeeReciept(int studentId, int semester);
	
	public ReportCard viewReportCard(int studentId);
	
	public boolean payfee(int studentId, int modeOfPayment);
	
	public boolean registerCourses(int studentId, int courseId);
}
