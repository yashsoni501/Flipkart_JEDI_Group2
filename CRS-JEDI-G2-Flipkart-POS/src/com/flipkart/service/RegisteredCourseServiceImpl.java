/**
 * 
 */
package com.flipkart.service;

import java.util.ArrayList;

import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;

/**
 * @author yashsoni501
 *
 */
public class RegisteredCourseServiceImpl implements RegisteredCourseInterface {

	public static volatile RegisteredCourseServiceImpl instance = null;

	public static RegisteredCourseServiceImpl getInstance() {
		if (instance == null) {
			// This is a synchronized block, when multiple threads will access this instance
			synchronized (RegisteredCourseServiceImpl.class) {
				instance = new RegisteredCourseServiceImpl();
			}
		}
		return instance;
	}

	@Override
	public boolean modifyGrade(String studentId, String courseId, String grade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String viewGrade(String studentId, String courseId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Student> getEnrolledStudents(String courseId, int semester, String session) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addRegisteredCourse(String courseId, int semester, String grade, String session, String studentID) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<RegisteredCourse> getRegisteredCourses(String studentId, String session, int semester) {
		// TODO Auto-generated method stub
		return null;
	}

}
