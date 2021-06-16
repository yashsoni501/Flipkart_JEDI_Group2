/**
 * 
 */
package com.flipkart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flipkart.bean.Student;
import com.flipkart.utils.DBUtils;
import com.flipkart.constant.SQLQuery;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.bean.CourseCatalog;

/**
 * The Class StudentDAOImpl.
 *
 * @author Aeron
 */

public class StudentDAOImpl implements StudentDAOInterface {

	Logger logger = LoggerFactory.getLogger(StudentDAOImpl.class.getName());

	/** The instance. */
	private static volatile StudentDAOImpl instance = null;

	/**
	 * Method to make StudentDAOImpl Singleton.
	 *
	 * @return single instance of StudentDAOImpl
	 */
	public static StudentDAOImpl getInstance() {
		if (instance == null) {
			synchronized (StudentDAOImpl.class) {
				instance = new StudentDAOImpl();
			}
		}
		return instance;
	}

	/**
	 * Fetch registered courses.
	 *
	 * @param studentId the student id
	 * @param sem       the sem
	 * @return the array list
	 * @throws SQLException            the SQL exception
	 * @throws CourseNotFoundException the course not found exception
	 */
	@Override
	public ArrayList<CourseCatalog> fetchRegisteredCourses(String studentId, int sem)
			throws SQLException, CourseNotFoundException {
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
			if (newRs.next()) {

				CourseCatalog courseFound = new CourseCatalog();
				courseFound.setCourseId(newRs.getString("courseid"));
				courseFound.setCredits(newRs.getFloat("credits"));
				courseFound.setProfessorId(newRs.getString("profid"));
				courseFound.setSession(newRs.getString("session"));
				courseFound.setSemester(newRs.getInt("semester"));

				RegisteredCourseList.add(courseFound);
			} else {
				CourseNotFoundException e = new CourseNotFoundException(course);
				logger.error(e.getMessage());
				throw e;
			}
		}

		return RegisteredCourseList;
	}

	/**
	 * Gets the all students.
	 *
	 * @param session the session
	 * @return the all students
	 * @throws SQLException the SQL exception
	 */
	@Override
	public ArrayList<Student> getAllStudents(String session) throws SQLException {
		Connection conn = DBUtils.getConnection();

		PreparedStatement stmt = conn.prepareStatement(SQLQuery.STUDENTS_IN_SESSION);
		stmt.setString(1, session);
		ResultSet myRs = stmt.executeQuery();

		ArrayList<Student> studentsFound = new ArrayList<Student>();

		while (myRs.next()) {

			Student currstudent = new Student();
			currstudent.setStudentID(myRs.getString("stuid"));
			currstudent.setEmailID(myRs.getString("email"));
			currstudent.setStudentName(myRs.getString("name"));
			currstudent.setDepartment(myRs.getString("department"));
			currstudent.setSession(myRs.getString("session"));
			currstudent.setApprovalStatus(myRs.getString("approved"));
			studentsFound.add(currstudent);
		}

		return studentsFound;

	}

	/**
	 * Gets the student by id.
	 *
	 * @param userId the user id
	 * @return the student by id
	 * @throws SQLException          the SQL exception
	 * @throws UserNotFoundException the user not found exception
	 */
	@Override
	public Student getStudentById(String userId) throws SQLException, UserNotFoundException {
		Connection conn = DBUtils.getConnection();

		PreparedStatement stmt = conn.prepareStatement(SQLQuery.STUDENT_BY_ID);
		stmt.setInt(1, Integer.parseInt(userId));
		ResultSet myRs = stmt.executeQuery();

		Student currstudent = new Student();
		if (myRs.next()) {

			currstudent.setStudentID(myRs.getString("stuid"));
			currstudent.setEmailID(myRs.getString("email"));
			currstudent.setStudentName(myRs.getString("name"));
			currstudent.setDepartment(myRs.getString("department"));
			currstudent.setSession(myRs.getString("session"));
			currstudent.setApprovalStatus(myRs.getString("approved"));

		} else {
			UserNotFoundException e = new UserNotFoundException(userId);
			logger.error(e.getMessage());
			throw e;
		}

		return currstudent;
	}
}