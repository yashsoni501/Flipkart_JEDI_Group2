/**
 * 
 */
package com.flipkart.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.flipkart.bean.Student;
import com.flipkart.bean.Course;

/**
 * @author Aeron
 *
 */

public class StudentDAOImpl implements StudentDAOInterface {

	private static volatile StudentDAOImpl instance = null;
	DAOConnectionInterface instanceDAO = new DAOConnectionImpl();

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
	public ArrayList<Course> fetchRegisteredCourses(String studentId, int sem) throws SQLException {

		PreparedStatement stmt = instanceDAO.conn
				.prepareStatement("select * from registeredCourse where stuid = ? and semester = ?");
		stmt.setInt(1, Integer.parseInt(studentId));
		stmt.setInt(2, sem);
		ResultSet myRs = stmt.executeQuery();

		PreparedStatement stamnt = instanceDAO.conn.prepareStatement("select * from course where courseid = ?");
		ResultSet newRs;
		ArrayList<Course> RegisteredCourseList = new ArrayList<Course>();

		while (myRs.next()) {

			int coruse = myRs.getInt("courseid");
			stamnt.setInt(1, coruse);
			newRs = stamnt.executeQuery();
			while (newRs.next()) {

				Course courseFound = new Course();
				courseFound.setCourseID(newRs.getString("courseid"));
				courseFound.setCourseName(newRs.getString("courseName"));
				courseFound.setDepartment(newRs.getString("department"));

				RegisteredCourseList.add(courseFound);
			}
		}

		return RegisteredCourseList;
	}

	@Override
	public boolean isFeePaid(String studentId, int sem) throws SQLException {

		PreparedStatement stmt = instanceDAO.conn
				.prepareStatement("select * from payment where stuid = ? and semester = ?");
		stmt.setInt(1, Integer.parseInt(studentId));
		stmt.setInt(2, sem);
		ResultSet myRs = stmt.executeQuery();

		boolean flag = false;
		while (myRs.next()) {
			if (myRs.getString("status") == "PAID")
				flag = true;
		}

		return flag;
	}

	@Override
	public ArrayList<Student> getAllStudents(String session) throws SQLException {

		PreparedStatement stmt = instanceDAO.conn.prepareStatement("select * from student where session = ?");
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

		PreparedStatement stmt = instanceDAO.conn.prepareStatement("select * from student where stuid = ?");
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