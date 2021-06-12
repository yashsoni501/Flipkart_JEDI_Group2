/**
 * 
 */
package com.flipkart.service;

import java.util.ArrayList;

import com.flipkart.bean.CourseCatalog;

/**
 * @author Lenovo
 *
 */
public interface CourseCatalogInterface {

	public CourseCatalog getCourseCatalog(String courseId);

	public ArrayList<CourseCatalog> getCourseCatalogBySessionSemester(String session, int semester);

	public ArrayList<CourseCatalog> getDepartmentCourseCatalog(String department);

	public boolean updateProfessorId(String courseId, String professorId);

	public void getCourseCatalogByProfessorId(String userId);

	public void getAllCourseCatalog();

}