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
public class UserServiceImpl implements UserInterface {

	public static volatile UserServiceImpl instance = null;
	
	public static UserServiceImpl getInstance()
	{
		if(instance==null)
		{
			// This is a synchronized block, when multiple threads will access this instance
			synchronized(UserServiceImpl.class){
				instance=new UserServiceImpl();
			}
		}
		return instance;
	}

	@Override
	public ArrayList<CourseCatalog> fetchCourses() {
		// TODO Auto-generated method stub
		ArrayList<CourseCatalog> newList = new ArrayList<CourseCatalog>();
		return newList;
	}

	@Override
	public boolean updateUserProfile(String studentId) {
		// TODO Auto-generated method stub
		return false;
	}

}
