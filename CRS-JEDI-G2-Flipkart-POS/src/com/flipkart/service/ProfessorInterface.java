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

	public ArrayList<CourseCatalog> viewOptedCourses(String professorId) throws SQLException;

	public Professor getProfessorDetails(String userId) throws SQLException;

	public ArrayList<Student> viewEnrolledStudents(String courseId, String session) throws SQLException;

	public boolean submitGrade(String courseId, String studentId, String grade) throws SQLException;

	public ArrayList<Professor> getAllProfessor() throws SQLException;

}
