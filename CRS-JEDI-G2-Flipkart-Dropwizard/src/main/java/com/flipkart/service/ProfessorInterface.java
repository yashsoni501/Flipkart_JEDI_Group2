/**
 * 
 */
package com.flipkart.service;

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
 * The Interface ProfessorInterface.
 *
 * @author jagru
 */
public interface ProfessorInterface {

	/**
	 * Opt in course.
	 *
	 * @param professorId the professor id
	 * @param courseId    the course id
	 * @return true, if successful
	 * @throws SQLException                   the SQL exception
	 * @throws OptingTheCourseFailedException the opting the course failed exception
	 */
	public boolean optInCourse(String professorId, String courseId) throws SQLException, OptingTheCourseFailedException;

	/**
	 * View opted courses.
	 *
	 * @param professorId the professor id
	 * @return the array list
	 * @throws SQLException            the SQL exception
	 * @throws NoOptedCoursesException the no opted courses exception
	 */
	public ArrayList<CourseCatalog> viewOptedCourses(String professorId) throws SQLException, NoOptedCoursesException;

	/**
	 * Gets the professor details.
	 *
	 * @param userId the user id
	 * @return the professor details
	 * @throws SQLException               the SQL exception
	 * @throws ProfessorNotAddedException the professor not added exception
	 */
	public Professor getProfessorDetails(String userId) throws SQLException, ProfessorNotAddedException;

	/**
	 * View enrolled students.
	 *
	 * @param courseId the course id
	 * @param session  the session
	 * @return the array list
	 * @throws SQLException                the SQL exception
	 * @throws NoEnrolledStudentsException the no enrolled students exception
	 */
	public ArrayList<Student> viewEnrolledStudents(String courseId, String session)
			throws SQLException, NoEnrolledStudentsException;

	/**
	 * Submit grade.
	 *
	 * @param courseId  the course id
	 * @param studentId the student id
	 * @param grade     the grade
	 * @return true, if successful
	 * @throws SQLException                   the SQL exception
	 * @throws GradeSubmissionFailedException the grade submission failed exception
	 */
	public boolean submitGrade(String courseId, String studentId, String grade)
			throws SQLException, GradeSubmissionFailedException;

	/**
	 * Gets the all professor.
	 *
	 * @return the all professor
	 * @throws SQLException               the SQL exception
	 * @throws NoProfessorsFoundException the no professors found exception
	 */
	public ArrayList<Professor> getAllProfessor() throws SQLException, NoProfessorsFoundException;

}
