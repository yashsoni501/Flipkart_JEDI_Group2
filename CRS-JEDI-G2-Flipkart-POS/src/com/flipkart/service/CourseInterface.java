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
 * @author Tanmay
 *
 */
public interface CourseInterface {
	public Course getCourse(String courseId) throws CourseNotFoundException, SQLException;

	public ArrayList<Course> getAllCourses() throws SQLException, EmptyCourseListExcpetion;
}
