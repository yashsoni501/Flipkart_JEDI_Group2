/**
 * 
 */
package com.flipkart.service;

import java.util.ArrayList;

import com.flipkart.bean.CourseCatalog;
import com.flipkart.exception.CourseCatalogEntryNotFoundException;
import com.flipkart.exception.InvalidCCSessionSemesterException;
import com.flipkart.exception.InvalidDepartmentException;

/**
 * @author Tanmay
 *
 */
public interface CourseCatalogInterface {

	public CourseCatalog getCourseCatalog(String courseId) throws CourseCatalogEntryNotFoundException;

	public ArrayList<CourseCatalog> getCourseCatalogBySessionSemester(String session, int semester) throws InvalidCCSessionSemesterException;

	public ArrayList<CourseCatalog> getDepartmentCourseCatalog(String department) throws InvalidDepartmentException;

	public boolean updateProfessorId(String courseId, String professorId);

	public ArrayList<CourseCatalog> getCourseCatalogByProfessorId(String userId);

	public ArrayList<CourseCatalog> getAllCourseCatalog();
}