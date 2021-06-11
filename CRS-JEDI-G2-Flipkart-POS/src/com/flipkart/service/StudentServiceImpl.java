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
public class StudentServiceImpl implements StudentInterface {
	
	private static volatile StudentServiceImpl instance=null;

	public static StudentServiceImpl getInstance()
	{
		if(instance==null)
		{
			// This is a synchronized block, when multiple threads will access this instance
			synchronized(StudentServiceImpl.class){
				instance=new StudentServiceImpl();
			}
		}
		return instance;
	}

	@Override
	public ArrayList<Course> fetchRegisteredCourses(String studentId, int semester) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean payFee(String studentId, int modeOfPayment) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isFeePaid(String studentId, int semester) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ReportCard viewReportCard(String studentId) {
		// TODO Auto-generated method stub
		return null;
	}

}
