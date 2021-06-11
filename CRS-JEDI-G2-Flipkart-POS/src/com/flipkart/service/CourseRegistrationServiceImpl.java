/**
 * 
 */
package com.flipkart.service;

import java.util.ArrayList;

import com.flipkart.bean.CourseCatalog;

/**
 * @author yashsoni501
 *
 */
public class CourseRegistrationServiceImpl implements CourseRegistrationInterface {

	public static volatile CourseRegistrationServiceImpl instance = null;
	ArrayList<CourseCatalog> selectedCourses = new ArrayList<CourseCatalog>();
	
	public static CourseRegistrationServiceImpl getInstance()
	{
		if(instance==null)
		{
			// This is a synchronized block, when multiple threads will access this instance
			synchronized(CourseRegistrationServiceImpl.class){
				instance=new CourseRegistrationServiceImpl();
			}
		}
		return instance;
	}
	
	@Override
	public boolean submitCourseRegistrationForm(ArrayList<CourseCatalog> courselist) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addCourse(String courseId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean dropCourse(String courseId) {
		// TODO Auto-generated method stub
		return false;
	}

}
