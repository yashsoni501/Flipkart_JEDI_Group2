/**
 * 
 */
package com.flipkart.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import com.flipkart.bean.CourseCatalog;
/**
 * @author aysh
 *
 */
public interface ProfessorDAOInterface {

	public ArrayList<CourseCatalog> viewOptedCourses(String professorId) throws SQLException;
	public boolean optInCourse(String professorId, String courseId) throws SQLException;
}
