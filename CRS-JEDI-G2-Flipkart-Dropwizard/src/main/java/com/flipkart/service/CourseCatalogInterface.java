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
 * The Interface CourseCatalogInterface.
 *
 * @author Tanmay
 */
public interface CourseCatalogInterface {

	/**
	 * Gets the course catalog.
	 *
	 * @param courseId the course id
	 * @return the course catalog
	 * @throws CourseCatalogEntryNotFoundException the course catalog entry not
	 *                                             found exception
	 * @throws SQLException                        the SQL exception
	 */
	public CourseCatalog getCourseCatalog(String courseId) throws CourseCatalogEntryNotFoundException, SQLException;

	/**
	 * Gets the course catalog by session semester.
	 *
	 * @param session  the session
	 * @param semester the semester
	 * @return the course catalog by session semester
	 * @throws InvalidCCSessionSemesterException the invalid CC session semester
	 *                                           exception
	 * @throws SQLException                      the SQL exception
	 */
	public ArrayList<CourseCatalog> getCourseCatalogBySessionSemester(String session, int semester)
			throws InvalidCCSessionSemesterException, SQLException;

	/**
	 * Gets the department course catalog.
	 *
	 * @param department the department
	 * @return the department course catalog
	 * @throws InvalidDepartmentException the invalid department exception
	 * @throws SQLException               the SQL exception
	 */
	public ArrayList<CourseCatalog> getDepartmentCourseCatalog(String department)
			throws InvalidDepartmentException, SQLException;

	/**
	 * Update professor id.
	 *
	 * @param courseId    the course id
	 * @param professorId the professor id
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 */
	public boolean updateProfessorId(String courseId, String professorId) throws SQLException;

	/**
	 * Gets the course catalog by professor id.
	 *
	 * @param userId the user id
	 * @return the course catalog by professor id
	 * @throws SQLException                    the SQL exception
	 * @throws EmptyCourseCatalogListExcpetion the empty course catalog list
	 *                                         excpetion
	 */
	public ArrayList<CourseCatalog> getCourseCatalogByProfessorId(String userId)
			throws SQLException, EmptyCourseCatalogListExcpetion;

	/**
	 * Gets the all course catalog.
	 *
	 * @return the all course catalog
	 * @throws SQLException                    the SQL exception
	 * @throws EmptyCourseCatalogListExcpetion the empty course catalog list
	 *                                         excpetion
	 */
	public ArrayList<CourseCatalog> getAllCourseCatalog() throws SQLException, EmptyCourseCatalogListExcpetion;
}