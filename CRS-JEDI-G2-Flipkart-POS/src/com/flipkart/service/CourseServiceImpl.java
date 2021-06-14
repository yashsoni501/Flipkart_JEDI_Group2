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
 * The Class CourseServiceImpl.
 *
 * @author yashsoni501
 */
public class CourseServiceImpl implements CourseInterface {

	/** The instance. */
	public static volatile CourseServiceImpl instance = null;

	/** The course DAO. */
	CourseDAOInterface courseDAO = CourseDAOImpl.getInstance();

	/**
	 * Gets the single instance of CourseServiceImpl.
	 *
	 * @return single instance of CourseServiceImpl
	 */
	public static CourseServiceImpl getInstance() {
		if (instance == null) {
			// This is a synchronized block, when multiple threads will access this instance
			synchronized (CourseServiceImpl.class) {
				instance = new CourseServiceImpl();
			}
		}
		return instance;
	}

	/**
	 * Gets the course.
	 *
	 * @param courseId the course id
	 * @return the course
	 * @throws CourseNotFoundException the course not found exception
	 * @throws SQLException            the SQL exception
	 */
	@Override
	public Course getCourse(String courseId) throws CourseNotFoundException, SQLException {

		return courseDAO.getCourse(courseId);
	}

	/**
	 * Gets the all courses.
	 *
	 * @return the all courses
	 * @throws SQLException             the SQL exception
	 * @throws EmptyCourseListExcpetion the empty course list excpetion
	 */
	@Override
	public ArrayList<Course> getAllCourses() throws SQLException, EmptyCourseListExcpetion {

		return courseDAO.getAllCourses();
	}

}
