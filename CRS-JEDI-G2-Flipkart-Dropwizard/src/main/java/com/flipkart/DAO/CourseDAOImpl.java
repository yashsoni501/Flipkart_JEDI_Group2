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

import com.flipkart.bean.Course;
import com.flipkart.bean.CourseCatalog;
import com.flipkart.utils.DBUtils;
import com.flipkart.constant.SQLQuery;
import com.flipkart.exception.CourseCatalogEntryNotFoundException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.EmptyCourseCatalogListExcpetion;
import com.flipkart.exception.EmptyCourseListExcpetion;
import com.flipkart.exception.InvalidCCSessionSemesterException;
import com.flipkart.exception.InvalidDepartmentException;

/**
 * The Class CourseDAOImpl.
 *
 * @author Tanmay
 */
public class CourseDAOImpl implements CourseDAOInterface {

	Logger logger = LoggerFactory.getLogger(CourseDAOImpl.class.getName());
	/** The instance. */
	public static volatile CourseDAOImpl instance = null;

	/**
	 * Gets the single instance of CourseDAOImpl.
	 *
	 * @return single instance of CourseDAOImpl
	 */
	public static CourseDAOImpl getInstance() {
		if (instance == null) {
			// This is a synchronized block, when multiple threads will access this instance
			synchronized (CourseDAOImpl.class) {
				instance = new CourseDAOImpl();
			}
		}
		return instance;
	}

	/**
	 * Gets the course.
	 *
	 * @param courseId the course id
	 * @return the course
	 * @throws CourseNotFoundException the course not found exception
	 * @throws SQLException            the SQL exception
	 */
	public Course getCourse(String courseId) throws CourseNotFoundException, SQLException {

		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		Course res = new Course();

		stmt = conn.prepareStatement(SQLQuery.GET_COURSE_BY_ID);
		stmt.setString(1, courseId);
		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {
			res.setCourseID(rs.getString("courseid"));
			res.setCourseName(rs.getString("courseName"));
			res.setDepartment(rs.getString("department"));
		} else {
			CourseNotFoundException e = new CourseNotFoundException(courseId);
			logger.error(e.getMessage());
			throw e;
		}

		return res;

	}

	/**
	 * Gets the all courses.
	 *
	 * @return the all courses
	 * @throws SQLException             the SQL exception
	 * @throws EmptyCourseListExcpetion the empty course list excpetion
	 */
	public ArrayList<Course> getAllCourses() throws SQLException, EmptyCourseListExcpetion {

		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		ArrayList<Course> res = new ArrayList<Course>();

		stmt = conn.prepareStatement(SQLQuery.GET_ALL_COURSES);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			// Retrieve by column name
			Course temp = new Course();
			temp.setCourseID(rs.getString("courseid"));
			temp.setCourseName(rs.getString("courseName"));
			temp.setDepartment(rs.getString("department"));

			res.add(temp);

		}

		if (res.size() == 0) {
			EmptyCourseListExcpetion e = new EmptyCourseListExcpetion();
			logger.error(e.getMessage());
			throw e;
		}

		return res;
	}

	/**
	 * Gets the course catalog.
	 *
	 * @param courseId the course id
	 * @return the course catalog
	 * @throws CourseCatalogEntryNotFoundException the course catalog entry not
	 *                                             found exception
	 * @throws SQLException                        the SQL exception
	 */
	public CourseCatalog getCourseCatalog(String courseId) throws CourseCatalogEntryNotFoundException, SQLException {

		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		CourseCatalog res = new CourseCatalog();

		stmt = conn.prepareStatement(SQLQuery.GET_COURSE_CATALOG_BY_ID);
		stmt.setString(1, courseId);
		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {
			res.setCourseId(String.valueOf(rs.getInt("courseid")));
			res.setProfessorId(rs.getString("profid"));
			res.setSemester(rs.getInt("semester"));
			res.setSession(rs.getString("session"));
			res.setCredits(rs.getFloat("credits"));
		} else {
			CourseCatalogEntryNotFoundException e = new CourseCatalogEntryNotFoundException(courseId);
			logger.error(e.getMessage());
			throw e;
		}

		return res;
	}

	/**
	 * Gets the course catalog by session semester.
	 *
	 * @param session  the session
	 * @param semester the semester
	 * @return the course catalog by session semester
	 * @throws InvalidCCSessionSemesterException the invalid CC session semester
	 *                                           exception
	 * @throws SQLException                      the SQL exception
	 */
	public ArrayList<CourseCatalog> getCourseCatalogBySessionSemester(String session, int semester)
			throws InvalidCCSessionSemesterException, SQLException {

		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		ArrayList<CourseCatalog> res = new ArrayList<CourseCatalog>();

		stmt = conn.prepareStatement(SQLQuery.GET_COURSE_CATALOG_BY_SESSION_SEMESTER);
		stmt.setString(1, session);
		stmt.setInt(2, semester);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			// Retrieve by column name
			CourseCatalog temp = new CourseCatalog();

			temp.setCourseId(String.valueOf(rs.getInt("courseid")));
			temp.setProfessorId(rs.getString("profid"));
			temp.setSemester(rs.getInt("semester"));
			temp.setSession(rs.getString("session"));
			temp.setCredits(rs.getFloat("credits"));

			res.add(temp);

		}

		if (res.size() == 0) {
			InvalidCCSessionSemesterException e = new InvalidCCSessionSemesterException(session, semester);
			logger.error(e.getMessage());
			throw e;
		}

		return res;
	}

	/**
	 * Gets the department course catalog.
	 *
	 * @param department the department
	 * @return the department course catalog
	 * @throws InvalidDepartmentException the invalid department exception
	 * @throws SQLException               the SQL exception
	 */
	public ArrayList<CourseCatalog> getDepartmentCourseCatalog(String department)
			throws InvalidDepartmentException, SQLException {

		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		ArrayList<CourseCatalog> res = new ArrayList<CourseCatalog>();

		stmt = conn.prepareStatement(SQLQuery.GET_DEPARTMENT_COURSE_CATALOG);
		stmt.setString(1, department);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			// Retrieve by column name
			
			CourseCatalog temp = new CourseCatalog();

			temp.setCourseId(String.valueOf(rs.getInt("courseid")));
			temp.setProfessorId(rs.getString("profid"));
			temp.setSemester(rs.getInt("semester"));
			temp.setSession(rs.getString("session"));
			temp.setCredits(rs.getFloat("credits"));

			res.add(temp);
			

			if (res.size() == 0) {
				InvalidDepartmentException e = new InvalidDepartmentException(department);
				logger.error(e.getMessage());
				throw e;
			}
		}

		return res;
	}

	/**
	 * Gets the all course catalog.
	 *
	 * @return the all course catalog
	 * @throws SQLException                    the SQL exception
	 * @throws EmptyCourseCatalogListExcpetion the empty course catalog list
	 *                                         excpetion
	 */
	public ArrayList<CourseCatalog> getAllCourseCatalog() throws SQLException, EmptyCourseCatalogListExcpetion {

		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		ArrayList<CourseCatalog> res = new ArrayList<CourseCatalog>();

		stmt = conn.prepareStatement(SQLQuery.GET_COURSE_CATALOG);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			// Retrieve by column name
			CourseCatalog temp = new CourseCatalog();

			temp.setCourseId(rs.getString("courseid"));
			temp.setProfessorId(rs.getString("profid"));
			temp.setSemester(rs.getInt("semester"));
			temp.setSession(rs.getString("session"));
			temp.setCredits(rs.getFloat("credits"));

			res.add(temp);

		}

		if (res.size() == 0) {
			EmptyCourseCatalogListExcpetion e = new EmptyCourseCatalogListExcpetion();
			logger.error(e.getMessage());
			throw e;
		}

		return res;
	}

	/**
	 * Gets the course catalog by professor id.
	 *
	 * @param userId the user id
	 * @return the course catalog by professor id
	 * @throws SQLException                    the SQL exception
	 * @throws EmptyCourseCatalogListExcpetion the empty course catalog list
	 *                                         excpetion
	 */
	public ArrayList<CourseCatalog> getCourseCatalogByProfessorId(String userId)
			throws SQLException, EmptyCourseCatalogListExcpetion {

		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		ArrayList<CourseCatalog> res = new ArrayList<CourseCatalog>();

		stmt = conn.prepareStatement(SQLQuery.GET_CATALOG_BY_PROF_ID);
		stmt.setString(1, userId);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			// Retrieve by column name
			CourseCatalog temp = new CourseCatalog();

			temp.setCourseId(rs.getString("courseid"));
			temp.setProfessorId(rs.getString("profid"));
			temp.setSemester(rs.getInt("semester"));
			temp.setSession(rs.getString("session"));
			temp.setCredits(rs.getFloat("credits"));

			res.add(temp);

		}

		if (res.size() == 0) {
			EmptyCourseCatalogListExcpetion e = new EmptyCourseCatalogListExcpetion();
			logger.error(e.getMessage());
			throw e;
		}

		return res;
	}

	/**
	 * Update professor id.
	 *
	 * @param courseId    the course id
	 * @param professorId the professor id
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 */
	public boolean updateProfessorId(String courseId, String professorId) throws SQLException {

		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		stmt = conn.prepareStatement(SQLQuery.UPDATE_PROF_IN_COURSE);
		stmt.setString(1, professorId);
		stmt.setString(2, courseId);

		int rows = stmt.executeUpdate();

		if (rows == 1) {
			return true;
		} else {
			return false;
		}

	}

//	public static void main(String[] args) {
//		CourseDAOImp as = new CourseDAOImp();

//		testing getCourse
//		Course t = new Course();
//		t = as.getCourse("a");
//		System.out.println(t.getDepartment());
//		System.out.println(t.getCourseID());

//		testing getAllCourses
//		ArrayList<Course> t = as.getAllCourses();
//		for (int i = 0; i < t.size(); i++) {
//			System.out.print(t.get(i).getCourseID() + " " +t.get(i).getCourseName() + " " + t.get(i).getDepartment() + "\n");
//		}

//		testing getCourseCatalog
//		CourseCatalog t = as.getCourseCatalog("12");
//		System.out.println(t.getCredits());
//		System.out.println(t.getSession());
//		System.out.println(t.getProfessorId());
//		System.out.println(t.getSemester());

//		testing getCourseCatalogBySessionSemester
//		ArrayList<CourseCatalog> t = as.getCourseCatalogBySessionSemester("sd", 4);
//		for (int i = 0; i < t.size(); i++) {
//			System.out.print(t.get(i).getCourseId() + " " +t.get(i).getProfessorId() + " " + t.get(i).getSemester() + " " + t.get(i).getSession() + " " + t.get(i).getCredits() + "\n");
//		}

//		testing getDepartmentCourseCatalog
//		ArrayList<CourseCatalog> t = as.getDepartmentCourseCatalog("x");
//		for (int i = 0; i < t.size(); i++) {
//			System.out.print(t.get(i).getCourseId() + " " +t.get(i).getProfessorId() + " " + t.get(i).getSemester() + " " + t.get(i).getSession() + " " + t.get(i).getCredits() + "\n");
//		}

//		testing getAllCourseCatalog()
//		ArrayList<CourseCatalog> t = as.getAllCourseCatalog();
//		for (int i = 0; i < t.size(); i++) {
//			System.out.print(t.get(i).getCourseId() + " " +t.get(i).getProfessorId() + " " + t.get(i).getSemester() + " " + t.get(i).getSession() + " " + t.get(i).getCredits() + "\n");
//		}

//		testing getAllCourseCatalog()
//		ArrayList<CourseCatalog> t = as.getCourseCatalogByProfessorId("3");
//		for (int i = 0; i < t.size(); i++) {
//			System.out.print(t.get(i).getCourseId() + " " +t.get(i).getProfessorId() + " " + t.get(i).getSemester() + " " + t.get(i).getSession() + " " + t.get(i).getCredits() + "\n");
//		}

//		testing getAllCourseCatalog()
//		as.updateProfessorId("3", "5");

//	}

}
