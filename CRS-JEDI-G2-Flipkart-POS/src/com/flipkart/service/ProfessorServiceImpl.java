/**
 * 
 */
package com.flipkart.service;

import java.util.ArrayList;

import java.sql.SQLException;

import com.flipkart.DAO.ProfessorDAOImpl;
import com.flipkart.DAO.ProfessorDAOInterface;
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
 * The Class ProfessorServiceImpl.
 *
 * @author jagru
 */
public class ProfessorServiceImpl implements ProfessorInterface {

	/** The instance. */
	public static volatile ProfessorServiceImpl instance = null;

	/** The professor DAO. */
	ProfessorDAOInterface professorDAO = ProfessorDAOImpl.getInstance();

	/**
	 * Gets the single instance of ProfessorServiceImpl.
	 *
	 * @return single instance of ProfessorServiceImpl
	 */
	public static ProfessorServiceImpl getInstance() {
		if (instance == null) {
			// This is a synchronized block, when multiple threads will access this instance
			synchronized (ProfessorServiceImpl.class) {
				instance = new ProfessorServiceImpl();
			}
		}
		return instance;
	}

	/**
	 * Opt in course.
	 *
	 * @param professorId the professor id
	 * @param courseId    the course id
	 * @return true, if successful
	 * @throws SQLException                   the SQL exception
	 * @throws OptingTheCourseFailedException the opting the course failed exception
	 */
	@Override
	public boolean optInCourse(String professorId, String courseId)
			throws SQLException, OptingTheCourseFailedException {
		// Auto-generated method stub
		return professorDAO.optInCourse(professorId, courseId);
	}

	/**
	 * View opted courses.
	 *
	 * @param professorId the professor id
	 * @return the array list
	 * @throws SQLException            the SQL exception
	 * @throws NoOptedCoursesException the no opted courses exception
	 */
	@Override
	public ArrayList<CourseCatalog> viewOptedCourses(String professorId) throws SQLException, NoOptedCoursesException {
		// Auto-generated method stub
		return professorDAO.viewOptedCourses(professorId);
	}

	/**
	 * Gets the professor details.
	 *
	 * @param userId the user id
	 * @return the professor details
	 * @throws SQLException               the SQL exception
	 * @throws ProfessorNotAddedException the professor not added exception
	 */
	@Override
	public Professor getProfessorDetails(String userId) throws SQLException, ProfessorNotAddedException {
		// Auto-generated method stub
		return professorDAO.getProfessorDetails(userId);
	}

	/**
	 * View enrolled students.
	 *
	 * @param courseId the course id
	 * @param session  the session
	 * @return the array list
	 * @throws SQLException                the SQL exception
	 * @throws NoEnrolledStudentsException the no enrolled students exception
	 */
	@Override
	public ArrayList<Student> viewEnrolledStudents(String courseId, String session)
			throws SQLException, NoEnrolledStudentsException {
		// Auto-generated method stub
		return professorDAO.viewEnrolledStudents(courseId, session);
	}

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
	@Override
	public boolean submitGrade(String courseId, String studentId, String grade)
			throws SQLException, GradeSubmissionFailedException {
		// Auto-generated method stub
		return professorDAO.submitGrade(courseId, studentId, grade);
	}

	/**
	 * Gets the all professor.
	 *
	 * @return the all professor
	 * @throws SQLException               the SQL exception
	 * @throws NoProfessorsFoundException the no professors found exception
	 */
	@Override
	public ArrayList<Professor> getAllProfessor() throws SQLException, NoProfessorsFoundException {
		// Auto-generated method stub
		return professorDAO.getAllProfessor();
	}

}
