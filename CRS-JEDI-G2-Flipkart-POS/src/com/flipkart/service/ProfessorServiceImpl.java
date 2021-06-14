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
 * @author jagru
 *
 */
public class ProfessorServiceImpl implements ProfessorInterface {

	public static volatile ProfessorServiceImpl instance = null;
	ProfessorDAOInterface professorDAO = ProfessorDAOImpl.getInstance();

	public static ProfessorServiceImpl getInstance() {
		if (instance == null) {
			// This is a synchronized block, when multiple threads will access this instance
			synchronized (ProfessorServiceImpl.class) {
				instance = new ProfessorServiceImpl();
			}
		}
		return instance;
	}

	@Override
	public boolean optInCourse(String professorId, String courseId)
			throws SQLException, OptingTheCourseFailedException {
		// Auto-generated method stub
		return professorDAO.optInCourse(professorId, courseId);
	}

	@Override
	public ArrayList<CourseCatalog> viewOptedCourses(String professorId) throws SQLException, NoOptedCoursesException {
		// Auto-generated method stub
		return professorDAO.viewOptedCourses(professorId);
	}

	@Override
	public Professor getProfessorDetails(String userId) throws SQLException, ProfessorNotAddedException {
		// Auto-generated method stub
		return professorDAO.getProfessorDetails(userId);
	}

	@Override
	public ArrayList<Student> viewEnrolledStudents(String courseId, String session)
			throws SQLException, NoEnrolledStudentsException {
		// Auto-generated method stub
		return professorDAO.viewEnrolledStudents(courseId, session);
	}

	@Override
	public boolean submitGrade(String courseId, String studentId, String grade)
			throws SQLException, GradeSubmissionFailedException {
		// Auto-generated method stub
		return professorDAO.submitGrade(courseId, studentId, grade);
	}

	@Override
	public ArrayList<Professor> getAllProfessor() throws SQLException, NoProfessorsFoundException {
		// Auto-generated method stub
		return professorDAO.getAllProfessor();
	}

}
