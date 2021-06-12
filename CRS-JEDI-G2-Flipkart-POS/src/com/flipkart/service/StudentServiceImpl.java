/**
 * 
 */
package com.flipkart.service;

import java.util.ArrayList;
import java.sql.SQLException;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.DAO.StudentDAOImpl;
import com.flipkart.DAO.StudentDAOInterface;

/**
 * @author Aeron
 *
 */

public class StudentServiceImpl implements StudentInterface {

	private static volatile StudentServiceImpl instance = null;
	StudentDAOInterface studentDAO = StudentDAOImpl.getInstance();

	public static StudentServiceImpl getInstance() {
		if (instance == null) {
			// This is a synchronized block, when multiple threads will access this instance
			synchronized (StudentServiceImpl.class) {
				instance = new StudentServiceImpl();
			}
		}
		return instance;
	}

	@Override
	public ArrayList<Course> fetchRegisteredCourses(String studentId, int semester) {
		// TODO Auto-generated method stub
		return studentDAO.fetchRegisteredCourses(studentId, semester);
	}

	@Override
	public boolean payFee(String studentId, int modeOfPayment) {
		// TODO Auto-generated method stub
		return studentDAO.payFee(studentId, modeOfPayment);
	}

	@Override
	public boolean isFeePaid(String studentId, int semester) {
		// TODO Auto-generated method stub
		return studentDAO.isFeePaid(studentId, semester);
	}

	@Override
	public Student getStudentById(String userId) {
		// TODO Auto-generated method stub
		return studentDAO.getStudentById(userId);
	}

	@Override
	public ArrayList<Student> getAllStudents(String session) {
		// TODO Auto-generated method stub
		return studentDAO.getAllStudents(session);
	}

}