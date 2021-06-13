/**
 * 
 */
package com.flipkart.DAO;

import java.util.ArrayList;
import java.sql.SQLException;

import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;

/**
 * @author Aeron
 *
 */
public interface RegisteredCourseDAOInterface {

	public ArrayList<Student> getEnrolledStudents(String courseId, int semester, String session) throws SQLException;

	public ArrayList<RegisteredCourse> getRegisteredCourses(String studentId, int semester) throws SQLException;

	public boolean addRegisteredCourse(String courseId, int semester, String grade, String session, String studentID) throws SQLException;
}
