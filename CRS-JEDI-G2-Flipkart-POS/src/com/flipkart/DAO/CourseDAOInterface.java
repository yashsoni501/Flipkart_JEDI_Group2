/**
 * 
 */
package com.flipkart.DAO;

import com.flipkart.bean.Course;
import com.flipkart.bean.CourseCatalog;
import com.flipkart.exception.CourseCatalogEntryNotFoundException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.EmptyCourseCatalogListExcpetion;
import com.flipkart.exception.EmptyCourseListExcpetion;
import com.flipkart.exception.InvalidCCSessionSemesterException;
import com.flipkart.exception.InvalidDepartmentException;

import java.sql.SQLException;
import java.util.ArrayList;;

/**
 * The Interface CourseDAOInterface.
 *
 * @author Tanmay
 */
public interface CourseDAOInterface {

	/**
	 * Gets the course.
	 *
	 * @param courseId the course id
	 * @return the course
	 * @throws CourseNotFoundException the course not found exception
	 * @throws SQLException            the SQL exception
	 */
	public Course getCourse(String courseId) throws CourseNotFoundException, SQLException;

	/**
	 * Gets the all courses.
	 *
	 * @return the all courses
	 * @throws SQLException             the SQL exception
	 * @throws EmptyCourseListExcpetion the empty course list excpetion
	 */
	public ArrayList<Course> getAllCourses() throws SQLException, EmptyCourseListExcpetion;

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
	 * Gets the all course catalog.
	 *
	 * @return the all course catalog
	 * @throws SQLException                    the SQL exception
	 * @throws EmptyCourseCatalogListExcpetion the empty course catalog list
	 *                                         excpetion
	 */
	public ArrayList<CourseCatalog> getAllCourseCatalog() throws SQLException, EmptyCourseCatalogListExcpetion;

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
	 * Update professor id.
	 *
	 * @param courseId    the course id
	 * @param professorId the professor id
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 */
	public boolean updateProfessorId(String courseId, String professorId) throws SQLException;

}
