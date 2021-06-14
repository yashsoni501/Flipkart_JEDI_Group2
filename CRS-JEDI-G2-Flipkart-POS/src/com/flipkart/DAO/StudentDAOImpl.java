/**
 * 
 */
package com.flipkart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.flipkart.bean.Student;
import com.flipkart.utils.DBUtils;
import com.flipkart.constant.SQLQuery;
import com.flipkart.bean.CourseCatalog;

/**
 * @author Aeron
 *
 */

public class StudentDAOImpl implements StudentDAOInterface {
	private static volatile StudentDAOImpl instance = null;

	/**
	 * Method to make StudentDAOImpl Singleton
	 * 
	 * @return
	 */
	public static StudentDAOImpl getInstance() {
		if (instance == null) {
			synchronized (StudentDAOImpl.class) {
				instance = new StudentDAOImpl();
			}
		}
		return instance;
	}

	@Override
	public ArrayList<CourseCatalog> fetchRegisteredCourses(String studentId, int sem) throws SQLException {
		Connection conn = DBUtils.getConnection();

		PreparedStatement stmt = conn.prepareStatement(SQLQuery.STUDENT_REGISTERED_COURSES);
		stmt.setInt(1, Integer.parseInt(studentId));
		stmt.setInt(2, sem);
		ResultSet myRs = stmt.executeQuery();

		PreparedStatement stamnt = conn.prepareStatement(SQLQuery.GET_COURSE_CATALOG_BY_ID);
		ResultSet newRs;
		ArrayList<CourseCatalog> RegisteredCourseList = new ArrayList<CourseCatalog>();

		while (myRs.next()) {

			String course = myRs.getString("courseid");
			stamnt.setString(1, course);
			newRs = stamnt.executeQuery();
			while (newRs.next()) {

				CourseCatalog courseFound = new CourseCatalog();
				courseFound.setCourseId(newRs.getString("courseid"));
				courseFound.setCredits(newRs.getFloat("credits"));
				courseFound.setProfessorId(newRs.getString("profid"));
				courseFound.setSession(newRs.getString("session"));
				courseFound.setSemester(newRs.getInt("semester"));

				RegisteredCourseList.add(courseFound);
			}
		}

		return RegisteredCourseList;
	}

	@Override
	public ArrayList<Student> getAllStudents(String session) throws SQLException {
		Connection conn = DBUtils.getConnection();

		PreparedStatement stmt = conn.prepareStatement(SQLQuery.STUDENTS_IN_SESSION);
		stmt.setString(1, session);
		ResultSet myRs = stmt.executeQuery();

		ArrayList<Student> studentsFound = new ArrayList<Student>();

		while (myRs.next()) {

			Student currstudent = new Student();
			currstudent.setStudentID(myRs.getString(1));
			currstudent.setEmailID(myRs.getString(2));
			currstudent.setStudentName(myRs.getString(3));
			currstudent.setDepartment(myRs.getString(4));
			currstudent.setSession(myRs.getString(5));

			studentsFound.add(currstudent);
		}

		return studentsFound;

	}

	@Override
	public Student getStudentById(String userId) throws SQLException {
		Connection conn = DBUtils.getConnection();

		PreparedStatement stmt = conn.prepareStatement(SQLQuery.STUDENT_BY_ID);
		stmt.setInt(1, Integer.parseInt(userId));
		ResultSet myRs = stmt.executeQuery();

		Student currstudent = new Student();
		while (myRs.next()) {

			currstudent.setStudentID(myRs.getString(1));
			currstudent.setEmailID(myRs.getString(2));
			currstudent.setStudentName(myRs.getString(3));
			currstudent.setDepartment(myRs.getString(4));
			currstudent.setSession(myRs.getString(5));
		}

		return currstudent;
	}
}