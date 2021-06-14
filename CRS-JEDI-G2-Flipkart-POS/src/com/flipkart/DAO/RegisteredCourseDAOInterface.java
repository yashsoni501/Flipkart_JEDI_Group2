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
 * @author Aeron
 *
 */
public interface RegisteredCourseDAOInterface {

	public ArrayList<Student> getEnrolledStudents(String courseId, int semester, String session) throws SQLException, NoStudentEnrolledException;

	public ArrayList<RegisteredCourse> getRegisteredCourses(String studentId, int semester) throws SQLException, NoRegisteredCoursesException;

	public boolean addRegisteredCourse(String courseId, int semester, String grade, String session, String studentID)
			throws SQLException;
}
