/**
 * 
 */
package com.flipkart.DAO;

import java.util.ArrayList;
import java.sql.SQLException;

import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;
import com.flipkart.exception.NoRegisteredCoursesException;
import com.flipkart.exception.NoStudentEnrolledException;

/**
 * The Interface RegisteredCourseDAOInterface.
 *
 * @author Aeron
 */
public interface RegisteredCourseDAOInterface {

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
	public ArrayList<Student> getEnrolledStudents(String courseId, int semester, String session)
			throws SQLException, NoStudentEnrolledException;

	/**
	 * Gets the registered courses.
	 *
	 * @param studentId the student id
	 * @param semester  the semester
	 * @return the registered courses
	 * @throws SQLException                 the SQL exception
	 * @throws NoRegisteredCoursesException the no registered courses exception
	 */
	public ArrayList<RegisteredCourse> getRegisteredCourses(String studentId, int semester)
			throws SQLException, NoRegisteredCoursesException;

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
	public boolean addRegisteredCourse(String courseId, int semester, String grade, String session, String studentID)
			throws SQLException;
}
