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
 * @author Aeron
 *
 */
public interface StudentDAOInterface {

	public ArrayList<CourseCatalog> fetchRegisteredCourses(String studentId, int semester)
			throws SQLException, CourseNotFoundException;

	public ArrayList<Student> getAllStudents(String session) throws SQLException;

	public Student getStudentById(String userId) throws SQLException, UserNotFoundException;
}
