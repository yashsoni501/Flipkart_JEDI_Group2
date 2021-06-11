/**
 * 
 */
package com.flipkart.service;

import java.util.ArrayList;

import com.flipkart.bean.Course;

/**
 * @author yashsoni501
 *
 */
public class CourseServiceImpl implements CourseInterface {

	public static volatile CourseServiceImpl instance = null;
	
	public static CourseServiceImpl getInstance()
	{
		if(instance==null)
		{
			// This is a synchronized block, when multiple threads will access this instance
			synchronized(CourseServiceImpl.class){
				instance=new CourseServiceImpl();
			}
		}
		return instance;
	}

	@Override
	public Course getCourse(String courseId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Course> getAllCourses() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
