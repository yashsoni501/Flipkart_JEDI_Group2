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
public class CourseCatalogServiceImpl implements CourseCatalogInterface {

	public static volatile CourseCatalogServiceImpl instance = null;

	public static CourseCatalogServiceImpl getInstance() {
		if (instance == null) {
			// This is a synchronized block, when multiple threads will access this instance
			synchronized (CourseCatalogServiceImpl.class) {
				instance = new CourseCatalogServiceImpl();
			}
		}
		return instance;
	}

	@Override
	public CourseCatalog getCourseCatalog(String courseId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<CourseCatalog> getCourseCatalogBySessionSemester(String session, int semester) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<CourseCatalog> getDepartmentCourseCatalog(String department) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateProfessorId(String courseId, String professorId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateIsOffered(String courseId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void getCourseCatalogByProfessorId(String userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getAllCourseCatalog() {
		// TODO Auto-generated method stub
		
	}



}
