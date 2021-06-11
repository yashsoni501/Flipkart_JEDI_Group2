/**
 * 
 */
package com.flipkart.service;

import java.util.List;

import com.flipkart.bean.Course;

/**
 * @author aysh
 *
 */
public interface UserInterface {

	public List<Course> fetchCourses();
	
	public boolean updateUserProfile(int studentId);
}
