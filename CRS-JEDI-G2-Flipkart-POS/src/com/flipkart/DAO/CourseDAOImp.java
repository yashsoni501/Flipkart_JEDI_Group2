/**
 * 
 */
package com.flipkart.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.flipkart.bean.Course;
import com.flipkart.bean.CourseCatalog;
import com.flipkart.utils.DBUtils;

/**
 * @author Tanmay
 *
 */
public class CourseDAOImp {
	public Course getCourse(String courseId) {

		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		Course res = new Course();

		try {

			// System.out.println("Connecting to database...");

			String sql = "select * from course where courseName = \"" + courseId + "\" limit 1";
//		    System.out.println(sql);
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				res.setCourseID(rs.getString("courseid"));
				res.setCourseName(rs.getString("courseName"));
				res.setDepartment(rs.getString("department"));

			} else {
				// or throw exception? no entry found
			}

			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return res;

	}

	public ArrayList<Course> getAllCourses() {

		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		ArrayList<Course> res = new ArrayList<Course>();

		try {
			String sql = "select * from course";
//		    System.out.println(sql);
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				// Retrieve by column name
				Course temp = new Course();
				temp.setCourseID(rs.getString("courseid"));
				temp.setCourseName(rs.getString("courseName"));
				temp.setDepartment(rs.getString("department"));

				res.add(temp);

			}

			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return res;
	}

	public CourseCatalog getCourseCatalog(String courseId) {

		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		CourseCatalog res = new CourseCatalog();

		try {
			String sql = "select * from coursecatalog where courseid = \"" + courseId + "\" limit 1";
//		    System.out.println(sql);
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				res.setCourseId(String.valueOf(rs.getInt("courseid")));
				res.setProfessorId(rs.getString("profid"));
				res.setSemester(rs.getInt("semester"));
				res.setSession(rs.getString("session"));
				res.setCredits((int) rs.getFloat("credits"));
			} else {
				// or throw exception? no entry found
			}

			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return res;
	}

	public ArrayList<CourseCatalog> getCourseCatalogBySessionSemester(String session, int semester) {

		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		ArrayList<CourseCatalog> res = new ArrayList<CourseCatalog>();

		try {
			String sql = "select * from coursecatalog where session = \"" + session + "\" and semester = " + semester;
//		    System.out.println(sql);
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				// Retrieve by column name
				CourseCatalog temp = new CourseCatalog();

				temp.setCourseId(String.valueOf(rs.getInt("courseid")));
				temp.setProfessorId(rs.getString("profid"));
				temp.setSemester(rs.getInt("semester"));
				temp.setSession(rs.getString("session"));
				temp.setCredits((int) rs.getFloat("credits"));

				res.add(temp);

			}

			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return res;
	}

	public ArrayList<CourseCatalog> getDepartmentCourseCatalog(String department) {

		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		ArrayList<CourseCatalog> res = new ArrayList<CourseCatalog>();

		try {
			String sql = "select cc.courseid, cc.profid, cc.semester, cc.session, cc.credits"
					+ " from coursecatalog as cc, course as c" + " where cc.courseid = c.courseid and c.department = \""
					+ department + "\"";
//		    System.out.println(sql);
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				// Retrieve by column name
				CourseCatalog temp = new CourseCatalog();

				temp.setCourseId(String.valueOf(rs.getInt("courseid")));
				temp.setProfessorId(rs.getString("profid"));
				temp.setSemester(rs.getInt("semester"));
				temp.setSession(rs.getString("session"));
				temp.setCredits((int) rs.getFloat("credits"));

				res.add(temp);

			}

			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return res;
	}

	public ArrayList<CourseCatalog> getAllCourseCatalog() {

		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		ArrayList<CourseCatalog> res = new ArrayList<CourseCatalog>();

		try {
			String sql = "select * from coursecatalog";
//		    System.out.println(sql);
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				// Retrieve by column name
				CourseCatalog temp = new CourseCatalog();

				temp.setCourseId(String.valueOf(rs.getInt("courseid")));
				temp.setProfessorId(rs.getString("profid"));
				temp.setSemester(rs.getInt("semester"));
				temp.setSession(rs.getString("session"));
				temp.setCredits((int) rs.getFloat("credits"));

				res.add(temp);

			}

			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return res;
	}

	public ArrayList<CourseCatalog> getCourseCatalogByProfessorId(String userId) {

		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		ArrayList<CourseCatalog> res = new ArrayList<CourseCatalog>();

		try {
			String sql = "select * from coursecatalog where profid = " + userId;
//		    System.out.println(sql);
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				// Retrieve by column name
				CourseCatalog temp = new CourseCatalog();

				temp.setCourseId(String.valueOf(rs.getInt("courseid")));
				temp.setProfessorId(rs.getString("profid"));
				temp.setSemester(rs.getInt("semester"));
				temp.setSession(rs.getString("session"));
				temp.setCredits((int) rs.getFloat("credits"));

				res.add(temp);

			}

			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return res;
	}

	public boolean updateProfessorId(String courseId, String professorId) {

		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		try {
			String sql = "update coursecatalog " + "set profid = " + professorId + " " + "where courseid = " + courseId;
//		    System.out.println(sql);
			stmt = conn.prepareStatement(sql);
			int rs = stmt.executeUpdate(sql);

			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return true;
	}

	public boolean updateIsOffered(String courseId) {

		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		boolean res = false;

		try {
			String sql = "select * from coursecatalog where courseid = " + courseId + " limit 1";
//		    System.out.println(sql);
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				if (rs.getString("profid").equals("-1")) {
					res = true;
				}

			}

			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return res;
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
