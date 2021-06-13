/**
 * 
 */
package com.flipkart.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;
import com.flipkart.DAO.RegisteredCourseDAOInterface;
import com.flipkart.DAO.RegisteredCourseDAOImpl;

/**
 * @author Aeron
 *
 */
public class RegisteredCourseServiceImpl implements RegisteredCourseInterface {

	public static volatile RegisteredCourseServiceImpl instance = null;
	RegisteredCourseDAOInterface registeredCourseDAO = RegisteredCourseDAOImpl.getInstance();
	
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
	public ArrayList<Student> getEnrolledStudents(String courseId, int semester, String session) throws SQLException {
		// Auto-generated method stub
		return registeredCourseDAO.getEnrolledStudents(courseId, semester, session);
	}

	@Override
	public boolean addRegisteredCourse(String courseId, int semester, String grade, String session, String studentID) throws SQLException {
		// Auto-generated method stub
		return registeredCourseDAO.addRegisteredCourse(courseId, semester, grade, session, studentID);
	}

	@Override
	public ArrayList<RegisteredCourse> getRegisteredCourses(String studentId, int semester) throws SQLException {
		// Auto-generated method stub
		return registeredCourseDAO.getRegisteredCourses(studentId, semester);
	}

}
