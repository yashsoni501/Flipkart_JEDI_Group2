/**
 * 
 */
package com.flipkart.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;
import com.flipkart.exception.NoRegisteredCoursesException;
import com.flipkart.exception.NoStudentEnrolledException;
import com.flipkart.DAO.RegisteredCourseDAOInterface;
import com.flipkart.DAO.RegisteredCourseDAOImpl;

/**
 * The Class RegisteredCourseServiceImpl.
 *
 * @author Aeron
 */
public class RegisteredCourseServiceImpl implements RegisteredCourseInterface {

	/** The instance. */
	public static volatile RegisteredCourseServiceImpl instance = null;

	/** The registered course DAO. */
	RegisteredCourseDAOInterface registeredCourseDAO = RegisteredCourseDAOImpl.getInstance();

	/**
	 * Gets the single instance of RegisteredCourseServiceImpl.
	 *
	 * @return single instance of RegisteredCourseServiceImpl
	 */
	public static RegisteredCourseServiceImpl getInstance() {
		if (instance == null) {
			// This is a synchronized block, when multiple threads will access this instance
			synchronized (RegisteredCourseServiceImpl.class) {
				instance = new RegisteredCourseServiceImpl();
			}
		}
		return instance;
	}

	/**
	 * Gets the enrolled students.
	 *
	 * @param courseId the course id
	 * @param semester the semester
	 * @param session  the session
	 * @return the enrolled students
	 * @throws SQLException               the SQL exception
	 * @throws NoStudentEnrolledException the no student enrolled exception
	 */
	@Override
	public ArrayList<Student> getEnrolledStudents(String courseId, int semester, String session)
			throws SQLException, NoStudentEnrolledException {
		// Auto-generated method stub
		return registeredCourseDAO.getEnrolledStudents(courseId, semester, session);
	}

	/**
	 * Adds the registered course.
	 *
	 * @param courseId  the course id
	 * @param semester  the semester
	 * @param grade     the grade
	 * @param session   the session
	 * @param studentID the student ID
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 */
	@Override
	public boolean addRegisteredCourse(String courseId, int semester, String grade, String session, String studentID)
			throws SQLException {
		// Auto-generated method stub
		return registeredCourseDAO.addRegisteredCourse(courseId, semester, grade, session, studentID);
	}

	/**
	 * Gets the registered courses.
	 *
	 * @param studentId the student id
	 * @param semester  the semester
	 * @return the registered courses
	 * @throws SQLException                 the SQL exception
	 * @throws NoRegisteredCoursesException the no registered courses exception
	 */
	@Override
	public ArrayList<RegisteredCourse> getRegisteredCourses(String studentId, int semester)
			throws SQLException, NoRegisteredCoursesException {
		// Auto-generated method stub
		return registeredCourseDAO.getRegisteredCourses(studentId, semester);
	}

}
