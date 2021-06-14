/**
 * 
 */
package com.flipkart.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.flipkart.bean.Course;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.EmptyCourseListExcpetion;

/**
 * The Interface CourseInterface.
 *
 * @author Tanmay
 */
public interface CourseInterface {

	/**
	 * Gets the course.
	 *
	 * @param courseId the course id
	 * @return the course
	 * @throws CourseNotFoundException the course not found exception
	 * @throws SQLException            the SQL exception
	 */
	public Course getCourse(String courseId) throws CourseNotFoundException, SQLException;

	/**
	 * Gets the all courses.
	 *
	 * @return the all courses
	 * @throws SQLException             the SQL exception
	 * @throws EmptyCourseListExcpetion the empty course list excpetion
	 */
	public ArrayList<Course> getAllCourses() throws SQLException, EmptyCourseListExcpetion;
}
