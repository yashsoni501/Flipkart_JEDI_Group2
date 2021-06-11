/**
 * 
 */
package com.flipkart.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.flipkart.bean.CourseCatalog;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

/**
 * @author jagru
 *
 */
public interface ProfessorInterface {

	public boolean optInCourse(String professorId, String courseId) throws SQLException;
	public ArrayList<CourseCatalog> viewOptedCourses(String professorId) throws SQLException ;
	public Professor getProfessorDetails(String userId) throws SQLException;
	public ArrayList<CourseCatalog> getDepartmentCourses(String department) throws SQLException;
	public ArrayList<Student> viewEnrolledStudents(String courseId) throws SQLException;
	public boolean submitGrade(int courseId, int studentId, int semester, String session, String grade ) throws SQLException;

	public Professor getProfessorById(String userId);

}
