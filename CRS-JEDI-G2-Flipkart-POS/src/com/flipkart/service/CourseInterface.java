/**
 * 
 */
package com.flipkart.service;

import java.util.ArrayList;

import com.flipkart.bean.Course;

/**
 * @author Lenovo
 *
 */
public interface CourseInterface {
	public Course getCourse(String courseId);

	public ArrayList<Course> getAllCourses();
}
