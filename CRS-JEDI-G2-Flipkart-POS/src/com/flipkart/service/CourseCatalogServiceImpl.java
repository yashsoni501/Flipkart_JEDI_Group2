/**
 * 
 */
package com.flipkart.service;

import java.util.ArrayList;

import com.flipkart.DAO.CourseDAOImp;
import com.flipkart.DAO.CourseDAOInterface;
import com.flipkart.bean.CourseCatalog;

/**
 * @author yashsoni501
 *
 */
public class CourseCatalogServiceImpl implements CourseCatalogInterface {

	public static volatile CourseCatalogServiceImpl instance = null;

	CourseDAOInterface courseDAO = CourseDAOImp.getInstance();

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

		return courseDAO.getCourseCatalog(courseId);
	}

	@Override
	public ArrayList<CourseCatalog> getCourseCatalogBySessionSemester(String session, int semester) {

		return courseDAO.getCourseCatalogBySessionSemester(session, semester);
	}

	@Override
	public ArrayList<CourseCatalog> getDepartmentCourseCatalog(String department) {

		return courseDAO.getDepartmentCourseCatalog(department);
	}

	@Override
	public boolean updateProfessorId(String courseId, String professorId) {

		return courseDAO.updateProfessorId(courseId, professorId);
	}

	@Override
	public ArrayList<CourseCatalog> getCourseCatalogByProfessorId(String userId) {

		return courseDAO.getCourseCatalogByProfessorId(userId);
	}

	@Override
	public ArrayList<CourseCatalog> getAllCourseCatalog() {
		// Auto-generated method stub
		return courseDAO.getAllCourseCatalog();
	}

}
