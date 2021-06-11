package com.flipkart.service;

import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.CourseRegistration;

public class AdminServiceImpl implements AdminInterface {

	public static volatile AdminServiceImpl instance = null;
	
	public static AdminServiceImpl getInstance()
	{
		if(instance==null)
		{
			// This is a synchronized block, when multiple threads will access this instance
			synchronized(AdminServiceImpl.class){
				instance=new AdminServiceImpl();
			}
		}
		return instance;
	}
	@Override
	public boolean addCourse(int course_id, String courseName, String Department) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addCourseCatalog(Course course) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void dropCourseCatalog(int courseId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean removeCourse(int courseId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addProfessor(int professorId, String name, String address, String department, String emailId,
			String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addStudent(int student_id, String name, String address, String department, String email,
			String password) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean enableOrDisableCourseRegistration(boolean flag) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean enableOrDisablePayment(boolean flag) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean generateReportCard() {
		// TODO Auto-generated method stub
		return false;
	}



}
