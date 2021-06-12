/**
 * 
 */
package com.flipkart.DAO;

import com.flipkart.bean.Course;
import com.flipkart.bean.CourseCatalog;

import java.util.ArrayList;;

/**
 * @author Tanmay
 *
 */
public interface CourseDAOInterface {
	
	public Course getCourse(String courseId);
	public ArrayList<Course> getAllCourses();
	public CourseCatalog getCourseCatalog(String courseId);
	public ArrayList<CourseCatalog> getCourseCatalogBySessionSemester(String session, int semester);
	public ArrayList<CourseCatalog> getDepartmentCourseCatalog(String department);
//	void in CourseCatalogInterface ??
	public ArrayList<CourseCatalog> getAllCourseCatalog();
	public ArrayList<CourseCatalog> getCourseCatalogByProfessorId(String userId);
	
	public boolean updateProfessorId(String courseId, String professorId);
	
//	public boolean updateProfessorId(String courseId, String professorId);
//	public boolean updateIsOffered(String courseId);
//	public void getCourseCatalogByProfessorId(String userId);
//	public void getAllCourseCatalog();
	

}
