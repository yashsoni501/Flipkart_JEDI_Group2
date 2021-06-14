/**
 * 
 */
package com.flipkart.DAO;

import com.flipkart.bean.Course;
import com.flipkart.bean.CourseCatalog;
import com.flipkart.exception.CourseCatalogEntryNotFoundException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.InvalidCCSessionSemesterException;
import com.flipkart.exception.InvalidDepartmentException;

import java.util.ArrayList;;

/**
 * @author Tanmay
 *
 */
public interface CourseDAOInterface {

	public Course getCourse(String courseId) throws CourseNotFoundException;

	public ArrayList<Course> getAllCourses();

	public CourseCatalog getCourseCatalog(String courseId) throws CourseCatalogEntryNotFoundException;

	public ArrayList<CourseCatalog> getCourseCatalogBySessionSemester(String session, int semester) throws InvalidCCSessionSemesterException;

	public ArrayList<CourseCatalog> getDepartmentCourseCatalog(String department) throws InvalidDepartmentException;

	public ArrayList<CourseCatalog> getAllCourseCatalog();

	public ArrayList<CourseCatalog> getCourseCatalogByProfessorId(String userId);

	public boolean updateProfessorId(String courseId, String professorId);

}
