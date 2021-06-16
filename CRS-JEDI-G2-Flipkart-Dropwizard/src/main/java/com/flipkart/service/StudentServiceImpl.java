/**
 * 
 */
package com.flipkart.service;

import java.util.ArrayList;
import java.sql.SQLException;
import com.flipkart.bean.CourseCatalog;
import com.flipkart.bean.Student;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.DAO.StudentDAOImpl;
import com.flipkart.DAO.StudentDAOInterface;

/**
 * The Class StudentServiceImpl.
 *
 * @author Aeron
 */

public class StudentServiceImpl implements StudentInterface {

	/** The instance. */
	private static volatile StudentServiceImpl instance = null;

	/** The student DAO. */
	StudentDAOInterface studentDAO = StudentDAOImpl.getInstance();

	/**
	 * Gets the single instance of StudentServiceImpl.
	 *
	 * @return single instance of StudentServiceImpl
	 */
	public static StudentServiceImpl getInstance() {
		if (instance == null) {
			// This is a synchronized block, when multiple threads will access this instance
			synchronized (StudentServiceImpl.class) {
				instance = new StudentServiceImpl();
			}
		}
		return instance;
	}

	/**
	 * Fetch registered courses.
	 *
	 * @param studentId the student id
	 * @param semester  the semester
	 * @return the array list
	 * @throws SQLException            the SQL exception
	 * @throws CourseNotFoundException the course not found exception
	 */
	@Override
	public ArrayList<CourseCatalog> fetchRegisteredCourses(String studentId, int semester)
			throws SQLException, CourseNotFoundException {
		// Auto-generated method stub
		return studentDAO.fetchRegisteredCourses(studentId, semester);
	}

	/**
	 * Gets the student by id.
	 *
	 * @param userId the user id
	 * @return the student by id
	 * @throws SQLException          the SQL exception
	 * @throws UserNotFoundException the user not found exception
	 */
	@Override
	public Student getStudentById(String userId) throws SQLException, UserNotFoundException {
		// Auto-generated method stub
		return studentDAO.getStudentById(userId);
	}

	/**
	 * Gets the all students.
	 *
	 * @param session the session
	 * @return the all students
	 * @throws SQLException the SQL exception
	 */
	@Override
	public ArrayList<Student> getAllStudents(String session) throws SQLException {
		// Auto-generated method stub
		return studentDAO.getAllStudents(session);
	}

}