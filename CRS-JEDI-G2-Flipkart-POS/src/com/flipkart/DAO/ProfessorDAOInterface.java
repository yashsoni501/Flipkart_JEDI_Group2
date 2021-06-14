/**
 * 
 */
package com.flipkart.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import com.flipkart.bean.CourseCatalog;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.exception.GradeSubmissionFailedException;
import com.flipkart.exception.NoEnrolledStudentsException;
import com.flipkart.exception.NoOptedCoursesException;
import com.flipkart.exception.NoProfessorsFoundException;
import com.flipkart.exception.OptingTheCourseFailedException;
import com.flipkart.exception.ProfessorNotAddedException;

/**
 * @author jagru
 *
 */
public interface ProfessorDAOInterface {

	public ArrayList<CourseCatalog> viewOptedCourses(String professorId) throws SQLException, NoOptedCoursesException;

	public boolean optInCourse(String professorId, String courseId) throws SQLException, OptingTheCourseFailedException;

	public Professor getProfessorDetails(String userId) throws SQLException, ProfessorNotAddedException;

	public ArrayList<Student> viewEnrolledStudents(String courseId, String session)
			throws SQLException, NoEnrolledStudentsException;

	public boolean submitGrade(String courseId, String studentId, String grade)
			throws SQLException, GradeSubmissionFailedException;

	public ArrayList<Professor> getAllProfessor() throws SQLException, NoProfessorsFoundException;
}
