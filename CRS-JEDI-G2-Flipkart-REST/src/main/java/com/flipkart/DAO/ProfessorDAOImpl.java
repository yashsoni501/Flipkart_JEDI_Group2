/**
 * 
 */
package com.flipkart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.flipkart.bean.CourseCatalog;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.utils.DBUtils;
import com.flipkart.constant.SQLQuery;
import com.flipkart.exception.GradeSubmissionFailedException;
import com.flipkart.exception.NoEnrolledStudentsException;
import com.flipkart.exception.NoOptedCoursesException;
import com.flipkart.exception.NoProfessorsFoundException;
import com.flipkart.exception.OptingTheCourseFailedException;
import com.flipkart.exception.ProfessorNotAddedException;

/**
 * The Class ProfessorDAOImpl.
 *
 * @author jagru
 */
public class ProfessorDAOImpl implements ProfessorDAOInterface {

	Logger logger = Logger.getLogger(ProfessorDAOImpl.class.getName());

	/** The instance. */
	private static volatile ProfessorDAOImpl instance = null;

	/**
	 * Method to make ProfessorDAOImpl Singleton.
	 *
	 * @return single instance of ProfessorDAOImpl
	 */
	public static ProfessorDAOImpl getInstance() {
		if (instance == null) {
			synchronized (ProfessorDAOImpl.class) {
				instance = new ProfessorDAOImpl();
			}
		}
		return instance;
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
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = conn.prepareStatement(SQLQuery.SELECT_OPTED_COURSES_PROF);
		stmt.setString(1, professorId);
		ResultSet rs = stmt.executeQuery();
		ArrayList<CourseCatalog> arr = new ArrayList<CourseCatalog>();
		while (rs.next()) {
			String id = rs.getString("courseid");
			String profid = rs.getString("profid");
			String session = rs.getString("session");
			int semester = rs.getInt("semester");
			float credit = rs.getFloat("credits");

			CourseCatalog temp = new CourseCatalog();
			temp.setCourseId(id);
			temp.setProfessorId(profid);
			temp.setSession(session);
			temp.setSemester(semester);

			temp.setCredits(credit);

			arr.add(temp);
		}
		if (arr.size() == 0) {
			NoOptedCoursesException e = new NoOptedCoursesException(professorId);
			logger.error(e.getMessage());
			throw e;
		}
		return arr;
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
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = conn.prepareStatement(SQLQuery.OPT_IN_COURSE_PROF);
		stmt.setString(1, professorId);
		stmt.setString(2, courseId);
		int rows = stmt.executeUpdate();
		if (rows <= 0) {
			OptingTheCourseFailedException e = new OptingTheCourseFailedException(courseId);
			logger.error(e.getMessage());
			throw e;
		}

		return rows > 0;
	}

	/**
	 * Gets the professor details.
	 *
	 * @param professorId the professor id
	 * @return the professor details
	 * @throws SQLException               the SQL exception
	 * @throws ProfessorNotAddedException the professor not added exception
	 */
	@Override
	public Professor getProfessorDetails(String professorId) throws SQLException, ProfessorNotAddedException {
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = conn.prepareStatement(SQLQuery.GET_PROFESSOR_DETAIL);
		stmt.setString(1, professorId);
		ResultSet rs = stmt.executeQuery();
		Professor p = new Professor();
		if (rs.next()) {
			p.setProfessorId(rs.getString("profid"));
			p.setEmailID(rs.getString("email"));
			p.setProfessorName(rs.getString("name"));
			p.setDepartment(rs.getString("department"));
		} else {
			ProfessorNotAddedException e = new ProfessorNotAddedException(professorId);
			logger.error(e.getMessage());
			throw e;
		}
		return p;
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
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = conn.prepareStatement(SQLQuery.VIEW_ENROLLED_STUDENTS);
		stmt.setString(1, courseId);
		stmt.setString(2, session);
		ResultSet rs = stmt.executeQuery();
		ArrayList<Student> arr = new ArrayList<Student>();
		while (rs.next()) {
			String id = rs.getString("stuid");

			System.out.println("StudentID: " + id + '\n');
			Student s = new Student();
			s.setStudentID(id);
			stmt = conn.prepareStatement(SQLQuery.STUDENT_BY_ID);
			stmt.setString(1, id);
			ResultSet rs1 = stmt.executeQuery();
			if (rs1.next()) {
				String name = rs1.getString("name");
				s.setStudentName(name);
				String email = rs1.getString("email");
				s.setEmailID(email);
				String dept = rs1.getString("department");
				s.setDepartment(dept);
				String sess = rs1.getString("session");
				s.setSession(sess);
			}
			arr.add(s);
		}
		if (arr.size() == 0) {
			NoEnrolledStudentsException e = new NoEnrolledStudentsException(courseId, session);
			logger.error(e.getMessage());
			throw e;
		}
		return arr;
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
		System.out.println(courseId + " " + studentId + " " + grade + '\n');

		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = conn.prepareStatement(SQLQuery.SUBMIT_GRADES_PROF);

		stmt.setString(1, grade);
		stmt.setString(2, courseId);
		stmt.setString(3, studentId);

		int rows = stmt.executeUpdate();
		if (rows <= 0) {
			GradeSubmissionFailedException e = new GradeSubmissionFailedException(courseId, studentId);
			logger.error(e.getMessage());
			throw e;
		}
		return rows > 0;
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
		Connection conn = DBUtils.getConnection();

		PreparedStatement stmt = conn.prepareStatement(SQLQuery.SELECT_ALL_PROFS);
		ResultSet myRs = stmt.executeQuery();

		ArrayList<Professor> profFound = new ArrayList<Professor>();

		while (myRs.next()) {

			Professor currprof = new Professor();
			currprof.setProfessorId(myRs.getString("profid"));
			currprof.setEmailID(myRs.getString("email"));
			currprof.setProfessorName(myRs.getString("name"));
			currprof.setDepartment(myRs.getString("department"));

			profFound.add(currprof);
		}

		if (profFound.size() == 0) {
			NoProfessorsFoundException e = new NoProfessorsFoundException();
			logger.error(e.getMessage());
			throw e;
		}
		return profFound;
	}
}
