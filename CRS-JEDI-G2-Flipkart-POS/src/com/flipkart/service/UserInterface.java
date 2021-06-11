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
public interface UserInterface {

	public ArrayList<CourseCatalog> fetchCourses();
	
	public boolean updateUserProfile(String userId);
}
