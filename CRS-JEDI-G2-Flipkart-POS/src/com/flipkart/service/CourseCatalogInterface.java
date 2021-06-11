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

 	public ArrayList<CourseCatalog> getAllCourseCatalog(String session, int semester);
 	public ArrayList<CourseCatalog> getDepartmentCourseCatalog(String session, int semester, String department);
 	
 	public boolean updateProfessorId(String courseId, String professorId);
 	public boolean updateIsOffered(String courseId);

}