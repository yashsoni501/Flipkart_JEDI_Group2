/**
 * 
 */
package com.flipkart.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.flipkart.bean.CourseCatalog;
import com.flipkart.exception.CourseCatalogEntryNotFoundException;
import com.flipkart.exception.EmptyCourseCatalogListExcpetion;
import com.flipkart.exception.InvalidCCSessionSemesterException;
import com.flipkart.exception.InvalidDepartmentException;

/**
 * @author Tanmay
 *
 */
public interface CourseCatalogInterface {

	public CourseCatalog getCourseCatalog(String courseId) throws CourseCatalogEntryNotFoundException, SQLException;

	public ArrayList<CourseCatalog> getCourseCatalogBySessionSemester(String session, int semester) throws InvalidCCSessionSemesterException, SQLException;

	public ArrayList<CourseCatalog> getDepartmentCourseCatalog(String department) throws InvalidDepartmentException, SQLException;

	public boolean updateProfessorId(String courseId, String professorId) throws SQLException;

	public ArrayList<CourseCatalog> getCourseCatalogByProfessorId(String userId) throws SQLException, EmptyCourseCatalogListExcpetion;

	public ArrayList<CourseCatalog> getAllCourseCatalog() throws SQLException, EmptyCourseCatalogListExcpetion;
}