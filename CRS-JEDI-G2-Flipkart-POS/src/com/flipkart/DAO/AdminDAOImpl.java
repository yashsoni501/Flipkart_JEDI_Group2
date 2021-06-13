package com.flipkart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;

import com.flipkart.bean.Admin;
import com.flipkart.utils.DBUtils;

public class AdminDAOImpl implements AdminDAOInterface {

	final public static String PAYMENT_WINDOW = "PAYMENT_WINDOW";
	final public static String COURSE_WINDOW = "COURSE_WINDOW";
	final public static String PROFESSOR_WINDOW = "PROFESSOR_WINDOW";

	final public static String TRUE = "TRUE";
	final public static String FALSE = "FALSE";

	private static volatile AdminDAOImpl instance = null;

	private AdminDAOImpl() {
	}

	public static AdminDAOImpl getInstance() {
		if (instance == null) {
			synchronized (AdminDAOImpl.class) {
				instance = new AdminDAOImpl();
			}
		}
		return instance;
	}

	@Override
	public boolean addCourse(String courseName, String department) {
		final String ADD_COURSE = "insert into course (`courseName`, `department`) values (?, ?)";

		try {
			Connection conn = DBUtils.getConnection();
			PreparedStatement stmt = conn.prepareStatement(ADD_COURSE);

			stmt.setString(1, courseName);
			stmt.setString(2, department);

			int rows = stmt.executeUpdate();
			if (rows == 0) {
				System.out.println("Add course Failed");
				return false;
			} else {
				return true;
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean removeCourse(String courseId) {

		final String REMOVE_COURSE = "delete from course where courseid=?";
		try {
			Connection conn = DBUtils.getConnection();
			PreparedStatement stmt = conn.prepareStatement(REMOVE_COURSE);

			stmt.setString(1, courseId);

			int rows = stmt.executeUpdate();

			if (rows == 0) {
				System.out.println("Course Removal failed");
				return false;
			} else {
				return true;
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean addProfessor(String name, String emailId, String password, String department) {

		final String ADD_USER = "insert into auth (`email`, `password`, `userRole`) values (?, ?, ?)";
		final String ADD_PROFESSOR = "insert into professor (`profid`, `email`, `name`, `department`) values (?, ?, ?, ?)";

		try {
			Connection conn = DBUtils.getConnection();

			Savepoint safepoint = conn.setSavepoint();
			conn.setAutoCommit(true);

			PreparedStatement stmt = conn.prepareStatement(ADD_USER);
			stmt.setString(1, emailId);
			stmt.setString(2, password);
			stmt.setString(3, "PROFESSOR");

			int row = stmt.executeUpdate();
			if (row == 0) {
				conn.rollback(safepoint);
				conn.setAutoCommit(true);
				return false;
			} else {
				AuthDAOInterface authDAO = AuthDAOImpl.getInstance();
				String uid = authDAO.verifyUserWithEmailPassword(emailId, password);

				stmt = conn.prepareStatement(ADD_PROFESSOR);

				stmt.setString(1, uid);
				stmt.setString(2, emailId);
				stmt.setString(3, name);
				stmt.setString(4, department);

				int rows = stmt.executeUpdate();

				if (rows == 0) {
					conn.rollback(safepoint);
					conn.setAutoCommit(true);
					return false;
				} else {
					conn.commit();
					conn.setAutoCommit(true);
					return true;
				}
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean addStudent(String name, String emailId, String password, String department, String session) {

		final String ADD_USER = "insert into auth (`email`, `password`, `userRole`) values (?, ?, ?)";
		final String ADD_STUDENT = "insert into student (`stuid`, `email`, `name`, `department`, `session`) values (?, ?, ?, ?, ?)";

		try {
			Connection conn = DBUtils.getConnection();

			conn.setAutoCommit(false);
			Savepoint safepoint = conn.setSavepoint();

			PreparedStatement stmt = conn.prepareStatement(ADD_USER);
			stmt.setString(1, emailId);
			stmt.setString(2, password);
			stmt.setString(3, "STUDENT");

			int row = stmt.executeUpdate();
			if (row == 0) {
				conn.rollback(safepoint);
				conn.setAutoCommit(true);
				return false;
			} else {
				AuthDAOInterface authDAO = AuthDAOImpl.getInstance();
				String uid = authDAO.verifyUserWithEmailPassword(emailId, password);

				System.out.println("UserID: " + uid + " from verify method");

				stmt = conn.prepareStatement(ADD_STUDENT);

				stmt.setString(1, uid);
				stmt.setString(2, emailId);
				stmt.setString(3, name);
				stmt.setString(4, department);
				stmt.setString(5, session);

				int rows = stmt.executeUpdate();

				if (rows == 0) {
					conn.rollback(safepoint);
					conn.setAutoCommit(true);
					return false;
				} else {
					conn.commit();
					conn.setAutoCommit(true);
					return true;
				}
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean setCourseRegistrationFlag(boolean flag) {
		final String EDIT_COURSE_REGISTRATION = "update constants set value=? where `key`=?";

		// Auto-generated method stub
		try {
			Connection conn = DBUtils.getConnection();
			PreparedStatement stmt = conn.prepareStatement(EDIT_COURSE_REGISTRATION);

			if (flag) {
				stmt.setString(1, TRUE);
			} else {
				stmt.setString(1, FALSE);
			}
			stmt.setString(2, COURSE_WINDOW);

			int rows = stmt.executeUpdate();
			if (rows == 0) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean setPaymentFlag(boolean flag) {
		final String EDIT_PAYMENT_FLAG = "update constants set value=? where `key`=?";

		// Auto-generated method stub
		try {
			Connection conn = DBUtils.getConnection();
			PreparedStatement stmt = conn.prepareStatement(EDIT_PAYMENT_FLAG);

			if (flag) {
				stmt.setString(1, TRUE);
			} else {
				stmt.setString(1, FALSE);
			}
			stmt.setString(2, PAYMENT_WINDOW);

			int rows = stmt.executeUpdate();
			if (rows == 0) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean setProfessorFlag(boolean flag) {
		final String EDIT_PROFESSOR_FLAG = "update constants set value=? where `key`=?";

		// TODO Auto-generated method stub
		try {
			Connection conn = DBUtils.getConnection();
			PreparedStatement stmt = conn.prepareStatement(EDIT_PROFESSOR_FLAG);

			if (flag) {
				stmt.setString(1, TRUE);
			} else {
				stmt.setString(1, FALSE);
			}
			stmt.setString(2, PROFESSOR_WINDOW);

			int rows = stmt.executeUpdate();
			if (rows == 0) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean removeProfessor(String profId) {
		final String REMOVE_PROFESSOR_PROFILE = "delete from professor where profid=?";
		final String REMOVE_PROFESSOR_AUTH = "delete from auth where uid=? ";

		// Auto-generated method stub
		try {
			Connection conn = DBUtils.getConnection();

			Savepoint savePoint = conn.setSavepoint();
			conn.setAutoCommit(false);

			PreparedStatement stmt = conn.prepareStatement(REMOVE_PROFESSOR_PROFILE);
			stmt.setString(1, profId);

			int rows = stmt.executeUpdate();
			if (rows == 0) {
				conn.rollback(savePoint);
				conn.setAutoCommit(true);
				return false;
			} else {
				stmt = conn.prepareStatement(REMOVE_PROFESSOR_AUTH);
				stmt.setString(1, profId);
				rows = stmt.executeUpdate();

				if (rows == 0) {
					conn.rollback(savePoint);
					conn.setAutoCommit(true);
					return false;
				} else {
					conn.commit();
					conn.setAutoCommit(true);
					return true;
				}
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean modifyProfessor(String profId, String professorName, String department) {
		final String MODIFY_PROFESSOR = "update professor set name=?, department=? where profid=?";

		// Auto-generated method stub
		try {
			Connection conn = DBUtils.getConnection();
			PreparedStatement stmt = conn.prepareStatement(MODIFY_PROFESSOR);

			stmt.setString(1, professorName);
			stmt.setString(2, department);
			stmt.setString(3, profId);

			int rows = stmt.executeUpdate();
			if (rows == 0) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean modifyStudnet(String studentId, String studentName, String department, String session) {
		final String MODIFY_STUDENT = "update student set name=?, department=?, session=? where stuid=?";

		// Auto-generated method stub
		try {
			Connection conn = DBUtils.getConnection();
			PreparedStatement stmt = conn.prepareStatement(MODIFY_STUDENT);

			stmt.setString(1, studentName);
			stmt.setString(2, department);
			stmt.setString(3, session);
			stmt.setString(4, studentId);

			int rows = stmt.executeUpdate();
			if (rows == 0) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean removeCourseCatalog(String courseId) {
		final String REMOVE_COURSE_CATALOG = "delete from courseCatalog where courseid=?";

		// Auto-generated method stub
		try {
			Connection conn = DBUtils.getConnection();
			PreparedStatement stmt = conn.prepareStatement(REMOVE_COURSE_CATALOG);

			stmt.setString(1, courseId);

			int rows = stmt.executeUpdate();
			if (rows == 0) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean modifyCourse(String courseId, String courseName, String department) {
		final String MODIFY_COURSE = "update course set courseName=?, department=? where courseid=?";

		try {
			Connection conn = DBUtils.getConnection();
			PreparedStatement stmt = conn.prepareStatement(MODIFY_COURSE);

			stmt.setString(1, courseName);
			stmt.setString(2, department);
			stmt.setString(3, courseId);

			int rows = stmt.executeUpdate();
			if (rows == 0) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean addCourseCatalog(String courseId, int semester, String session, float credits, String profId) {
		final String ADD_COURSE_CATALOG = "insert into courseCatalog (`courseid`, `profid`, `semester`, `session`, `credits`) values (?, ?, ?, ?, ?)";

		try {
			Connection conn = DBUtils.getConnection();
			PreparedStatement stmt = conn.prepareStatement(ADD_COURSE_CATALOG);

			stmt.setString(1, courseId);
			stmt.setString(2, profId);
			stmt.setInt(3, semester);
			stmt.setString(4, session);
			stmt.setFloat(5, credits);

			int rows = stmt.executeUpdate();
			if (rows == 0) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean modifyCourseCatalog(String courseId, int semester, String session, float credits, String profId) {
		final String MODIFY_COURSE_CATALOG = "update courseCatalog set profid=?, semester=?, session=?, credits=? where courseid=?";

		try {
			Connection conn = DBUtils.getConnection();
			PreparedStatement stmt = conn.prepareStatement(MODIFY_COURSE_CATALOG);

			stmt.setString(1, profId);
			stmt.setInt(2, semester);
			stmt.setString(3, session);
			stmt.setFloat(4, credits);
			stmt.setString(5, courseId);

			int rows = stmt.executeUpdate();
			if (rows == 0) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Admin getAdminById(String userId) {
		final String GET_ADMIN_BY_ID = "select * from admin where adminid=?";

		try {
			Connection conn = DBUtils.getConnection();
			PreparedStatement stmt = conn.prepareStatement(GET_ADMIN_BY_ID);

			stmt.setString(1, userId);

			ResultSet resultSet = stmt.executeQuery();

			if (!resultSet.next()) {
				return null;
			} else {
				Admin admin = new Admin();
				admin.setAdminID(resultSet.getInt("adminid"));
				admin.setAdminName(resultSet.getString("name"));
				admin.setEmailID(resultSet.getString("email"));
				admin.setDesignation(resultSet.getString("designation"));
				admin.setStatus(resultSet.getString("status"));
				return admin;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private boolean getBooleanConstants(String key) {
		final String GET_FLAG = "select value from constants where `key`=?";

		try {
			Connection conn = DBUtils.getConnection();
			PreparedStatement stmt = conn.prepareStatement(GET_FLAG);
			stmt.setString(1, key);
			ResultSet resultSet = stmt.executeQuery();

			if (resultSet.next()) {
				String flag = resultSet.getString("value");
				if (flag.equals(TRUE)) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean removeStudent(String studentId) {
		final String REMOVE_STUDENT_PROFILE = "delete from student where stuid=?";
		final String REMOVE_STUDENT_AUTH = "delete from auth where uid=? ";

		// Auto-generated method stub
		try {
			Connection conn = DBUtils.getConnection();

			conn.setAutoCommit(false);
			Savepoint savePoint = conn.setSavepoint();

			PreparedStatement stmt = conn.prepareStatement(REMOVE_STUDENT_PROFILE);
			stmt.setString(1, studentId);

			int rows = stmt.executeUpdate();
			if (rows == 0) {
				conn.rollback(savePoint);
				conn.setAutoCommit(true);
				return false;
			} else {
				stmt = conn.prepareStatement(REMOVE_STUDENT_AUTH);
				stmt.setString(1, studentId);
				rows = stmt.executeUpdate();

				if (rows == 0) {
					conn.rollback(savePoint);
					conn.setAutoCommit(true);
					return false;
				} else {
					conn.commit();
					conn.setAutoCommit(true);
					return true;
				}
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean getCourseRegistrationFlag() {
		return getBooleanConstants(COURSE_WINDOW);
	}

	@Override
	public boolean getPaymentFlag() {
		return getBooleanConstants(PAYMENT_WINDOW);
	}

	@Override
	public boolean getProfessorFlag() {
		return getBooleanConstants(PROFESSOR_WINDOW);
	}
}
