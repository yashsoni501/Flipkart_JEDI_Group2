/**
 * 
 */
package com.flipkart.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import com.flipkart.bean.Student;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.bean.CourseCatalog;

/**
 * The Interface StudentDAOInterface.
 *
 * @author Aeron
 */
public interface StudentDAOInterface {

	/**
	 * Fetch registered courses.
	 *
	 * @param studentId the student id
	 * @param semester  the semester
	 * @return the array list
	 * @throws SQLException            the SQL exception
	 * @throws CourseNotFoundException the course not found exception
	 */
	public ArrayList<CourseCatalog> fetchRegisteredCourses(String studentId, int semester)
			throws SQLException, CourseNotFoundException;

	/**
	 * Gets the all students.
	 *
	 * @param session the session
	 * @return the all students
	 * @throws SQLException the SQL exception
	 */
	public ArrayList<Student> getAllStudents(String session) throws SQLException;

	/**
	 * Gets the student by id.
	 *
	 * @param userId the user id
	 * @return the student by id
	 * @throws SQLException          the SQL exception
	 * @throws UserNotFoundException the user not found exception
	 */
	public Student getStudentById(String userId) throws SQLException, UserNotFoundException;
}
