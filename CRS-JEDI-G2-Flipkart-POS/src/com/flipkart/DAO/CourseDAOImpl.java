/**
 * 
 */
package com.flipkart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.flipkart.bean.Course;
import com.flipkart.bean.CourseCatalog;
import com.flipkart.utils.DBUtils;
import com.flipkart.constant.SQLQuery;

/**
 * @author Tanmay
 *
 */
public class CourseDAOImpl implements CourseDAOInterface {

	public static volatile CourseDAOImpl instance = null;

	public static CourseDAOImpl getInstance() {
		if (instance == null) {
			// This is a synchronized block, when multiple threads will access this instance
			synchronized (CourseDAOImpl.class) {
				instance = new CourseDAOImpl();
			}
		}
		return instance;
	}

	public Course getCourse(String courseId) {

		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		Course res = new Course();

		try {

			stmt = conn.prepareStatement(SQLQuery.GET_COURSE_BY_ID);
			stmt.setString(1, courseId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				res.setCourseID(rs.getString("courseid"));
				res.setCourseName(rs.getString("courseName"));
				res.setDepartment(rs.getString("department"));

			}

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
		return res;

	}

	public ArrayList<Course> getAllCourses() {

		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		ArrayList<Course> res = new ArrayList<Course>();

		try {

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
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
		return res;
	}

	public CourseCatalog getCourseCatalog(String courseId) {

		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		CourseCatalog res = new CourseCatalog();

		try {

			stmt = conn.prepareStatement(SQLQuery.GET_COURSE_CATALOG_BY_ID);
			stmt.setString(1, courseId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				res.setCourseId(String.valueOf(rs.getInt("courseid")));
				res.setProfessorId(rs.getString("profid"));
				res.setSemester(rs.getInt("semester"));
				res.setSession(rs.getString("session"));
				res.setCredits(rs.getFloat("credits"));
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<CourseCatalog> getCourseCatalogBySessionSemester(String session, int semester) {

		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		ArrayList<CourseCatalog> res = new ArrayList<CourseCatalog>();

		try {
//			System.out.println("Sem: " + semester + ", session: " + session + "\n");

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

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<CourseCatalog> getDepartmentCourseCatalog(String department) {

		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		ArrayList<CourseCatalog> res = new ArrayList<CourseCatalog>();

		try {

			stmt = conn.prepareStatement(SQLQuery.GET_DEPARTMENT_COURSE_CATALOG);
			stmt.setString(1, department);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// Retrieve by column name
				if (rs.getString("profid") == null) {
					CourseCatalog temp = new CourseCatalog();

					temp.setCourseId(String.valueOf(rs.getInt("courseid")));
					temp.setProfessorId(rs.getString("profid"));
					temp.setSemester(rs.getInt("semester"));
					temp.setSession(rs.getString("session"));
					temp.setCredits(rs.getFloat("credits"));

					res.add(temp);
				}
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<CourseCatalog> getAllCourseCatalog() {

		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		ArrayList<CourseCatalog> res = new ArrayList<CourseCatalog>();

		try {

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
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<CourseCatalog> getCourseCatalogByProfessorId(String userId) {

		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		ArrayList<CourseCatalog> res = new ArrayList<CourseCatalog>();

		try {

//		    System.out.println(sql);
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

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
		return res;
	}

	public boolean updateProfessorId(String courseId, String professorId) {

		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		try {

			stmt = conn.prepareStatement(SQLQuery.UPDATE_PROF_IN_COURSE);
			stmt.setString(1, professorId);
			stmt.setString(2, courseId);

			int rows = stmt.executeUpdate();

			if (rows == 1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
		return true;
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