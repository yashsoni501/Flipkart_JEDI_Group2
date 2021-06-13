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
		String SELECT_OPTED_COURSES_PROF = "select * from courseCatalog where profId = ?";
		PreparedStatement stmt = conn.prepareStatement(SELECT_OPTED_COURSES_PROF);
		stmt.setString(1, professorId);
		ResultSet rs = stmt.executeQuery();
		ArrayList<CourseCatalog> arr = new ArrayList<CourseCatalog>();
		while (rs.next()) {
			int id = rs.getInt("courseId");
			CourseCatalog temp = new CourseCatalog();
			temp.setCourseId(String.valueOf(id));
//	        temp.setCourseId(name);

			arr.add(temp);
		}
		return arr;
	}

	public boolean optInCourse(String professorId, String courseId) throws SQLException {
		Connection conn = DBUtils.getConnection();
		String OPT_In_COURSE_PROF = "update courseCatalog set profId = ? where courseId = ?";
		PreparedStatement stmt = conn.prepareStatement(OPT_In_COURSE_PROF);
		stmt.setString(1, professorId);
		stmt.setString(2, courseId);

		int rows = stmt.executeUpdate();
		System.out.println("Rows impacted : " + rows);

		return rows==1;
	}

	@Override
	public Professor getProfessorDetails(String userId) throws SQLException {
		Connection conn = DBUtils.getConnection();
		String GET_PROFESSOR_DetAIL = "Select * from professor where profid = ?";
		PreparedStatement stmt = conn.prepareStatement(GET_PROFESSOR_DetAIL);
		stmt.setString(1, userId);
		ResultSet rs = stmt.executeQuery();
		Professor p = new Professor();
		while (rs.next()) {
			p.setProfessorId(rs.getInt("profid"));
			p.setEmailID(rs.getString("email"));
			p.setProfessorName(rs.getString("name"));
			p.setDepartment(rs.getString("department"));
		}
		return p;
	}

	@Override
	public ArrayList<Student> viewEnrolledStudents(String courseId) throws SQLException {
		Connection conn = DBUtils.getConnection();
		String VIEW_ENROLLED_STU = "select stuId from registeredCourse where courseId = ?";
		PreparedStatement stmt = conn.prepareStatement(VIEW_ENROLLED_STU);
		stmt.setString(1, courseId);
		ResultSet rs = stmt.executeQuery();
		ArrayList<Student> arr = new ArrayList<Student>();
		while (rs.next()) {
			int id = rs.getInt("courseId");
			Student s = new Student();
			s.setStudentID(String.valueOf(id));
			String VIEW_STUDENT_DET = "select * from student where stuid = ?";
			PreparedStatement stmt1 = conn.prepareStatement(VIEW_STUDENT_DET);
			stmt.setString(1, String.valueOf(id));
			ResultSet rs1 = stmt1.executeQuery();
			if (rs1.next()) {
				String name = rs.getString("name");
				s.setStudentName(name);
				String email = rs.getString("email");
				s.setEmailID(email);
				String dept = rs.getString("department");
				s.setDepartment(dept);
				String sess = rs.getString("session");
				s.setSession(sess);
			}
			arr.add(s);
		}
		return arr;
	}

	@Override
	public boolean submitGrade(String courseId, String studentId, String grade) throws SQLException {
		Connection conn = DBUtils.getConnection();
		String GRADE_SUBMISSION = "update registeredCourse set grade = ? where courseId = ? and stuid = ? and semester = ? and session = ?";
		PreparedStatement stmt = conn.prepareStatement(GRADE_SUBMISSION);
		stmt.setString(1, grade);
		stmt.setString(2, courseId);
		stmt.setString(3, studentId);

		int rows = stmt.executeUpdate();
		System.out.println("Rows impacted : " + rows);
		return rows==1;
	}
}
