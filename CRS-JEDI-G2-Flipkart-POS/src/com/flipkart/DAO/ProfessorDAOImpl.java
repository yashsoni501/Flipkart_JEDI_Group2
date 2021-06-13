/**
 * 
 */
package com.flipkart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.flipkart.bean.CourseCatalog;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.utils.DBUtils;
import com.flipkart.utils.SQLQuery;

/**
 * @author jagru
 *
 */
public class ProfessorDAOImpl implements ProfessorDAOInterface {
	private static volatile ProfessorDAOImpl instance = null;

	/**
	 * Method to make ProfessorDAOImpl Singleton
	 * 
	 * @return
	 */
	public static ProfessorDAOImpl getInstance() {
		if (instance == null) {
			synchronized (ProfessorDAOImpl.class) {
				instance = new ProfessorDAOImpl();
			}
		}
		return instance;
	}

	public ArrayList<CourseCatalog> viewOptedCourses(String professorId) throws SQLException {
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
		return arr;
	}

	public boolean optInCourse(String professorId, String courseId) throws SQLException {
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = conn.prepareStatement(SQLQuery.OPT_IN_COURSE_PROF);
		stmt.setString(1, professorId);
		stmt.setString(2, courseId);
		int rows = stmt.executeUpdate();
		System.out.println("Rows impacted : " + rows);

		return rows == 1;
	}

	@Override
	public Professor getProfessorDetails(String userId) throws SQLException {
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = conn.prepareStatement(SQLQuery.GET_PROFESSOR_DETAIL);
		stmt.setString(1, userId);
		ResultSet rs = stmt.executeQuery();
		Professor p = new Professor();
		while (rs.next()) {
			p.setProfessorId(rs.getString("profid"));
			p.setEmailID(rs.getString("email"));
			p.setProfessorName(rs.getString("name"));
			p.setDepartment(rs.getString("department"));
		}
		return p;
	}

	@Override
	public ArrayList<Student> viewEnrolledStudents(String courseId, String session) throws SQLException {
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
			String VIEW_STUDENT_DET = "select * from student where stuid = ?";
			stmt = conn.prepareStatement(VIEW_STUDENT_DET);
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
		return arr;
	}

	@Override
	public boolean submitGrade(String courseId, String studentId, String grade) throws SQLException {
		System.out.println(courseId + " " + studentId + " " + grade + '\n');

		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = conn.prepareStatement(SQLQuery.SUBMIT_GRADES_PROF);

		stmt.setString(1, grade);
		stmt.setString(2, courseId);
		stmt.setString(3, studentId);

		int rows = stmt.executeUpdate();
		System.out.println("Rows impacted : " + rows);
		return rows > 0;
	}

	@Override
	public ArrayList<Professor> getAllProfessor() throws SQLException {
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

		return profFound;
	}
}
