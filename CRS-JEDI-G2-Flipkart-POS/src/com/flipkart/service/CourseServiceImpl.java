/**
 * 
 */
package com.flipkart.service;

import java.util.ArrayList;

import com.flipkart.DAO.CourseDAOImp;
import com.flipkart.DAO.CourseDAOInterface;
import com.flipkart.bean.Course;

/**
 * @author yashsoni501
 *
 */
public class CourseServiceImpl implements CourseInterface {

	public static volatile CourseServiceImpl instance = null;
	CourseDAOInterface courseDAO = CourseDAOImp.getInstance();

	public static CourseServiceImpl getInstance() {
		if (instance == null) {
			// This is a synchronized block, when multiple threads will access this instance
			synchronized (CourseServiceImpl.class) {
				instance = new CourseServiceImpl();
			}
		}
		return instance;
	}

	@Override
	public Course getCourse(String courseId) {

		return courseDAO.getCourse(courseId);
	}

	@Override
	public ArrayList<Course> getAllCourses() {

		return courseDAO.getAllCourses();
	}

}
