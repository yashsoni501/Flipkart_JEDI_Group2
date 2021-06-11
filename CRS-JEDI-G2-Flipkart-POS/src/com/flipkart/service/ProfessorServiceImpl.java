/**
 * 
 */
package com.flipkart.service;

import java.util.ArrayList;

import com.flipkart.bean.CourseCatalog;

/**
 * @author aysh
 *
 */
public class ProfessorServiceImpl implements ProfessorInterface {


	public static volatile ProfessorServiceImpl instance = null;
	
	public static ProfessorServiceImpl getInstance()
	{
		if(instance==null)
		{
			// This is a synchronized block, when multiple threads will access this instance
			synchronized(ProfessorServiceImpl.class){
				instance=new ProfessorServiceImpl();
			}
		}
		return instance;
	}

	@Override
	public boolean optInCourse(String professorId, String courseId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<CourseCatalog> viewOptedCourses(String professorId) {
		// TODO Auto-generated method stub
		return null;
	}

}
