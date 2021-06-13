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
	public ArrayList<Student> getEnrolledStudents(String courseId, int semester, String session) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addRegisteredCourse(String courseId, int semester, String grade, String session, String studentID) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public ArrayList<RegisteredCourse> getRegisteredCourses(String studentId, int semester) {
		// TODO Auto-generated method stub
		return null;
	}

}
