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

import com.flipkart.bean.Student;
import com.flipkart.utils.DBUtils;
import com.flipkart.constant.SQLQuery;
import com.flipkart.exception.NoRegisteredCoursesException;
import com.flipkart.exception.NoStudentEnrolledException;
import com.flipkart.bean.RegisteredCourse;

/**
 * The Class RegisteredCourseDAOImpl.
 *
 * @author Aeron
 */
public class RegisteredCourseDAOImpl implements RegisteredCourseDAOInterface {

	Logger logger = Logger.getLogger(RegisteredCourseDAOImpl.class.getName());
	/** The instance. */
	private static volatile RegisteredCourseDAOImpl instance = null;

	/**
	 * Method to make RegisteredCourseDAOImpl Singleton.
	 *
	 * @return single instance of RegisteredCourseDAOImpl
	 */
	public static RegisteredCourseDAOImpl getInstance() {
		if (instance == null) {
			synchronized (RegisteredCourseDAOImpl.class) {
				instance = new RegisteredCourseDAOImpl();
			}
		}
		return instance;
	}

	/**
	 * Gets the enrolled students.
	 *
	 * @param courseId the course id
	 * @param semester the semester
	 * @param session  the session
	 * @return the enrolled students
	 * @throws SQLException               the SQL exception
	 * @throws NoStudentEnrolledException the no student enrolled exception
	 */
	@Override
	public ArrayList<Student> getEnrolledStudents(String courseId, int semester, String session)
			throws SQLException, NoStudentEnrolledException {
		// Auto-generated method stub
		Connection conn = DBUtils.getConnection();

		PreparedStatement stmt = conn.prepareStatement(SQLQuery.SELECT_STUDENTS_IN_COURSE);
		stmt.setString(1, courseId);
		stmt.setInt(2, semester);
		stmt.setString(3, session);
		ResultSet myRs = stmt.executeQuery();

		ArrayList<Student> studentsFound = new ArrayList<Student>();

		PreparedStatement stamnt = conn.prepareStatement(SQLQuery.SELECT_STUDENT_BY_ID);
		ResultSet newRs;

		while (myRs.next()) {

			int temp = myRs.getInt("stuid");
			stamnt.setInt(1, temp);
			newRs = stamnt.executeQuery();
			while (newRs.next()) {

				Student currstudent = new Student();
				currstudent.setEmailID(newRs.getString("email"));
				currstudent.setStudentID(newRs.getString("stuid"));
				currstudent.setStudentName(newRs.getString("name"));
				currstudent.setDepartment(newRs.getString("department"));
				currstudent.setSession(newRs.getString("session"));

				studentsFound.add(currstudent);
			}
		}

		if (studentsFound.size() == 0) {
			NoStudentEnrolledException e = new NoStudentEnrolledException(courseId, semester, session);
			logger.error(e.getMessage());
			throw e;
		}

		return studentsFound;
	}

	/**
	 * Gets the registered courses.
	 *
	 * @param studentId the student id
	 * @param semester  the semester
	 * @return the registered courses
	 * @throws SQLException                 the SQL exception
	 * @throws NoRegisteredCoursesException the no registered courses exception
	 */
	@Override
	public ArrayList<RegisteredCourse> getRegisteredCourses(String studentId, int semester)
			throws SQLException, NoRegisteredCoursesException {
		// Auto-generated method stub
		Connection conn = DBUtils.getConnection();

		PreparedStatement stmt = conn.prepareStatement(SQLQuery.GET_REGISTERED_COURSES_BY_STUDENT_ID);
		stmt.setInt(1, Integer.parseInt(studentId));
		stmt.setInt(2, semester);
		ResultSet myRs = stmt.executeQuery();

		ArrayList<RegisteredCourse> RegisteredCourseList = new ArrayList<RegisteredCourse>();

		while (myRs.next()) {

			RegisteredCourse courseFound = new RegisteredCourse();
			courseFound.setCourseId(myRs.getString("courseid"));
			courseFound.setGrade(myRs.getString("grade"));
			courseFound.setStudentId(myRs.getString("stuid"));
			courseFound.setSession(myRs.getString("session"));
			courseFound.setSemester(myRs.getInt("semester"));

			RegisteredCourseList.add(courseFound);
		}

		if (RegisteredCourseList.size() == 0) {
			NoRegisteredCoursesException e = new NoRegisteredCoursesException(studentId, semester);
			logger.error(e.getMessage());
			throw e;
		}

		return RegisteredCourseList;
	}

	/**
	 * Adds the registered course.
	 *
	 * @param courseId  the course id
	 * @param semester  the semester
	 * @param grade     the grade
	 * @param session   the session
	 * @param studentID the student ID
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 */
	@Override
	public boolean addRegisteredCourse(String courseId, int semester, String grade, String session, String studentID)
			throws SQLException {
		// Auto-generated method stub
		Connection conn = DBUtils.getConnection();

		PreparedStatement stmt = conn.prepareStatement(SQLQuery.ADD_REGISTERED_COURSE);
		stmt.setInt(1, Integer.parseInt(courseId));
		stmt.setInt(2, Integer.parseInt(studentID));
		stmt.setInt(3, semester);
		stmt.setString(4, session);
		stmt.setString(5, grade);

		int rows = stmt.executeUpdate();
		if (rows == 0) {
			System.out.println("Registration in new course Failed");
			return false;
		} else {
			return true;
		}
	}
}
