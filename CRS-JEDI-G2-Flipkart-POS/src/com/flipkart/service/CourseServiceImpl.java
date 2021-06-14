/**
 * 
 */
package com.flipkart.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.flipkart.DAO.CourseDAOImpl;
import com.flipkart.DAO.CourseDAOInterface;
import com.flipkart.bean.Course;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.EmptyCourseListExcpetion;

/**
 * @author yashsoni501
 *
 */
public class CourseServiceImpl implements CourseInterface {

	public static volatile CourseServiceImpl instance = null;
	CourseDAOInterface courseDAO = CourseDAOImpl.getInstance();

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
	public Course getCourse(String courseId) throws CourseNotFoundException, SQLException {

		return courseDAO.getCourse(courseId);
	}

	@Override
	public ArrayList<Course> getAllCourses() throws SQLException, EmptyCourseListExcpetion {

		return courseDAO.getAllCourses();
	}

}
