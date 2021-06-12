/**
 * 
 */
package com.flipkart.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.flipkart.bean.Student;
import com.flipkart.bean.Course;

/**
 * @author aysh
 *
 */
public interface StudentInterface {

	public ArrayList<Course> fetchRegisteredCourses(String studentId, int semester) throws SQLException;

	public boolean isFeePaid(String studentId, int semester) throws SQLException;

	public ArrayList<Student> getAllStudents(String session) throws SQLException;

	public Student getStudentById(String userId) throws SQLException;
}