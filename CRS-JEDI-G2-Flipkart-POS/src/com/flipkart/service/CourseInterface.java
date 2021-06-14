/**
 * 
 */
package com.flipkart.service;

import java.util.ArrayList;

import com.flipkart.bean.Course;
import com.flipkart.exception.CourseNotFoundException;

/**
 * @author Lenovo
 *
 */
public interface CourseInterface {
	public Course getCourse(String courseId) throws CourseNotFoundException;

	public ArrayList<Course> getAllCourses();
}
