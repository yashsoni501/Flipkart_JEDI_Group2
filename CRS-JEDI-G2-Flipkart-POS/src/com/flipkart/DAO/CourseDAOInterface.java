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
 * @author Tanmay
 *
 */
public interface CourseDAOInterface {

	public Course getCourse(String courseId) throws CourseNotFoundException, SQLException;

	public ArrayList<Course> getAllCourses() throws SQLException, EmptyCourseListExcpetion;

	public CourseCatalog getCourseCatalog(String courseId) throws CourseCatalogEntryNotFoundException, SQLException;

	public ArrayList<CourseCatalog> getCourseCatalogBySessionSemester(String session, int semester)
			throws InvalidCCSessionSemesterException, SQLException;

	public ArrayList<CourseCatalog> getDepartmentCourseCatalog(String department)
			throws InvalidDepartmentException, SQLException;

	public ArrayList<CourseCatalog> getAllCourseCatalog() throws SQLException, EmptyCourseCatalogListExcpetion;

	public ArrayList<CourseCatalog> getCourseCatalogByProfessorId(String userId)
			throws SQLException, EmptyCourseCatalogListExcpetion;

	public boolean updateProfessorId(String courseId, String professorId) throws SQLException;

}
