package com.flipkart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;

import com.flipkart.bean.Admin;
import com.flipkart.constant.Constants;
import com.flipkart.utils.DBUtils;
import com.flipkart.constant.SQLQuery;
import com.flipkart.exception.ConstantFlagNotSetException;
import com.flipkart.exception.CourseNotDeletedException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.InvalidCredentialsException;
import com.flipkart.exception.UserEmailAlreadyInUseException;
import com.flipkart.exception.UserEmailNotFoundException;
import com.flipkart.exception.UserNotFoundException;

/**
 * The Class AdminDAOImpl.
 */
public class AdminDAOImpl implements AdminDAOInterface {

	/** The instance. */
	private static volatile AdminDAOImpl instance = null;

	/**
	 * Instantiates a new admin DAO impl.
	 */
	private AdminDAOImpl() {
	}

	/**
	 * Gets the single instance of AdminDAOImpl.
	 *
	 * @return single instance of AdminDAOImpl
	 */
	public static AdminDAOImpl getInstance() {
		if (instance == null) {
			synchronized (AdminDAOImpl.class) {
				instance = new AdminDAOImpl();
			}
		}
		return instance;
	}

	/**
	 * Adds the course.
	 *
	 * @param courseName the course name
	 * @param department the department
	 * @return true, if successful
	 */
	@Override
	public boolean addCourse(String courseName, String department) throws SQLException {
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = conn.prepareStatement(SQLQuery.ADD_COURSE);

		stmt.setString(1, courseName);
		stmt.setString(2, department);

		int rows = stmt.executeUpdate();
		if (rows == 0) {

			return false;
		} else {
			return true;
		}
	}

	/**
	 * Removes the course.
	 *
	 * @param courseId the course id
	 * @return true, if successful
	 */
	@Override
	public boolean removeCourse(String courseId) throws CourseNotFoundException, SQLException {

		final String REMOVE_COURSE = "delete from course where courseid=?";

		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = conn.prepareStatement(REMOVE_COURSE);

		stmt.setString(1, courseId);

		int rows = stmt.executeUpdate();

		if (rows == 0) {
			throw new CourseNotFoundException(courseId);

		} else {
			return true;
		}
	}

	/**
	 * Adds the professor.
	 *
	 * @param name       the name
	 * @param emailId    the email id
	 * @param password   the password
	 * @param department the department
	 * @return true, if successful
	 */
	@Override
	public boolean addProfessor(String name, String emailId, String password, String department)
			throws UserEmailAlreadyInUseException, InvalidCredentialsException, UserEmailNotFoundException,
			SQLException {

		Connection conn = DBUtils.getConnection();

		Savepoint safepoint = conn.setSavepoint();
		conn.setAutoCommit(true);

		PreparedStatement stmt = conn.prepareStatement(SQLQuery.ADD_USER);
		stmt.setString(1, emailId);
		stmt.setString(2, password);
		stmt.setString(3, Constants.USER_ROLE_PROFESSOR);

		int row = stmt.executeUpdate();
		if (row == 0) {
			conn.rollback(safepoint);
			conn.setAutoCommit(true);
			throw new UserEmailAlreadyInUseException(emailId);

		} else {
			AuthDAOInterface authDAO = AuthDAOImpl.getInstance();
			String uid = authDAO.verifyUserWithEmailPassword(emailId, password);

			stmt = conn.prepareStatement(SQLQuery.ADD_PROFESSOR);

			stmt.setString(1, uid);
			stmt.setString(2, emailId);
			stmt.setString(3, name);
			stmt.setString(4, department);

			int rows = stmt.executeUpdate();

			if (rows == 0) {
				conn.rollback(safepoint);
				conn.setAutoCommit(true);

				throw new UserEmailAlreadyInUseException(emailId);

			} else {
				conn.commit();
				conn.setAutoCommit(true);
				return true;
			}
		}
	}

	/**
	 * Adds the student.
	 *
	 * @param name       the name
	 * @param emailId    the email id
	 * @param password   the password
	 * @param department the department
	 * @param session    the session
	 * @return true, if successful
	 */
	@Override
	public boolean addStudent(String name, String emailId, String password, String department, String session)
			throws UserEmailAlreadyInUseException, InvalidCredentialsException, UserEmailNotFoundException,
			SQLException {

		Connection conn = DBUtils.getConnection();

		conn.setAutoCommit(false);
		Savepoint safepoint = conn.setSavepoint();

		PreparedStatement stmt = conn.prepareStatement(SQLQuery.ADD_USER);
		stmt.setString(1, emailId);
		stmt.setString(2, password);
		stmt.setString(3, Constants.USER_ROLE_STUDENT);

		int row = stmt.executeUpdate();
		if (row == 0) {
			conn.rollback(safepoint);
			conn.setAutoCommit(true);
			throw new UserEmailAlreadyInUseException(emailId);

		} else {
			AuthDAOInterface authDAO = AuthDAOImpl.getInstance();
			String uid = authDAO.verifyUserWithEmailPassword(emailId, password);

			System.out.println("UserID: " + uid + " from verify method");

			stmt = conn.prepareStatement(SQLQuery.ADD_STUDENT);

			stmt.setString(1, uid);
			stmt.setString(2, emailId);
			stmt.setString(3, name);
			stmt.setString(4, department);
			stmt.setString(5, session);

			int rows = stmt.executeUpdate();

			if (rows == 0) {
				conn.rollback(safepoint);
				conn.setAutoCommit(true);
				throw new UserEmailAlreadyInUseException(emailId);

			} else {
				conn.commit();
				conn.setAutoCommit(true);
				return true;
			}
		}
	}

	/**
	 * Sets the course registration flag.
	 *
	 * @param flag the flag
	 * @return true, if successful
	 */
	@Override
	public boolean setCourseRegistrationFlag(boolean flag) throws SQLException {

		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = conn.prepareStatement(SQLQuery.EDIT_COURSE_REGISTRATION);

		if (flag) {
			stmt.setString(1, Constants.TRUE);
		} else {
			stmt.setString(1, Constants.FALSE);
		}
		stmt.setString(2, Constants.COURSE_WINDOW);

		int rows = stmt.executeUpdate();
		if (rows == 0) {

			return false;

		} else {
			return true;
		}
	}

	/**
	 * Sets the payment flag.
	 *
	 * @param flag the flag
	 * @return true, if successful
	 */
	@Override
	public boolean setPaymentFlag(boolean flag) throws SQLException {
		// Auto-generated method stub
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = conn.prepareStatement(SQLQuery.EDIT_PAYMENT_FLAG);

		if (flag) {
			stmt.setString(1, Constants.TRUE);
		} else {
			stmt.setString(1, Constants.FALSE);
		}
		stmt.setString(2, Constants.PAYMENT_WINDOW);

		int rows = stmt.executeUpdate();
		if (rows == 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Sets the professor flag.
	 *
	 * @param flag the flag
	 * @return true, if successful
	 */
	@Override
	public boolean setProfessorFlag(boolean flag) throws SQLException {

		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = conn.prepareStatement(SQLQuery.EDIT_PROFESSOR_FLAG);

		if (flag) {
			stmt.setString(1, Constants.TRUE);
		} else {
			stmt.setString(1, Constants.FALSE);
		}
		stmt.setString(2, Constants.PROFESSOR_WINDOW);

		int rows = stmt.executeUpdate();
		if (rows == 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Removes the professor.
	 *
	 * @param profId the prof id
	 * @return true, if successful
	 */
	@Override
	public boolean removeProfessor(String profId) throws UserNotFoundException, SQLException {
		// Auto-generated method stub
		Connection conn = DBUtils.getConnection();

		Savepoint savePoint = conn.setSavepoint();
		conn.setAutoCommit(false);

		PreparedStatement stmt = conn.prepareStatement(SQLQuery.REMOVE_PROFESSOR_PROFILE);
		stmt.setString(1, profId);

		int rows = stmt.executeUpdate();
		if (rows == 0) {
			conn.rollback(savePoint);
			conn.setAutoCommit(true);
			throw new UserNotFoundException(profId);

		} else {
			stmt = conn.prepareStatement(SQLQuery.REMOVE_PROFESSOR_AUTH);
			stmt.setString(1, profId);
			rows = stmt.executeUpdate();

			if (rows == 0) {
				conn.rollback(savePoint);
				conn.setAutoCommit(true);
				throw new UserNotFoundException(profId);

			} else {
				conn.commit();
				conn.setAutoCommit(true);
				return true;
			}
		}
	}

	/**
	 * Modify professor.
	 *
	 * @param profId        the prof id
	 * @param professorName the professor name
	 * @param department    the department
	 * @return true, if successful
	 */
	@Override
	public boolean modifyProfessor(String profId, String professorName, String department)
			throws UserNotFoundException, SQLException {
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = conn.prepareStatement(SQLQuery.MODIFY_PROFESSOR);

		stmt.setString(1, professorName);
		stmt.setString(2, department);
		stmt.setString(3, profId);

		int rows = stmt.executeUpdate();

		if (rows == 0) {
			throw new UserNotFoundException(profId);
		} else {
			return true;
		}
	}

	/**
	 * Modify studnet.
	 *
	 * @param studentId   the student id
	 * @param studentName the student name
	 * @param department  the department
	 * @param session     the session
	 * @return true, if successful
	 */
	@Override
	public boolean modifyStudent(String email, String studentName, String department, String session)
			throws UserNotFoundException, SQLException {
		// Auto-generated method stub
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = conn.prepareStatement(SQLQuery.MODIFY_STUDENT);

			stmt.setString(1, studentName);
			stmt.setString(2, department);
			stmt.setString(3, session);
			stmt.setString(4, Constants.FALSE);
			stmt.setString(5, email);

		int rows = stmt.executeUpdate();
		if (rows == 0) {
			throw new UserEmailNotFoundException(email);

		} else {
			return true;
		}
	}

	/**
	 * Removes the course catalog.
	 *
	 * @param courseId the course id
	 * @return true, if successful
	 */
	@Override
	public boolean removeCourseCatalog(String courseId) throws CourseNotFoundException, SQLException {
		// Auto-generated method stub
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = conn.prepareStatement(SQLQuery.REMOVE_COURSE_CATALOG);

		stmt.setString(1, courseId);

		int rows = stmt.executeUpdate();
		if (rows == 0) {
			throw new CourseNotFoundException(courseId);

		} else {
			return true;
		}
	}

	/**
	 * Modify course.
	 *
	 * @param courseId   the course id
	 * @param courseName the course name
	 * @param department the department
	 * @return true, if successful
	 */
	@Override
	public boolean modifyCourse(String courseId, String courseName, String department)
			throws CourseNotFoundException, SQLException {
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = conn.prepareStatement(SQLQuery.MODIFY_COURSE);

		stmt.setString(1, courseName);
		stmt.setString(2, department);
		stmt.setString(3, courseId);

		int rows = stmt.executeUpdate();
		if (rows == 0) {
			throw new CourseNotFoundException(courseId);

		} else {
			return true;
		}
	}

	/**
	 * Adds the course catalog.
	 *
	 * @param courseId the course id
	 * @param semester the semester
	 * @param session  the session
	 * @param credits  the credits
	 * @param profId   the prof id
	 * @return true, if successful
	 */
	@Override
	public boolean addCourseCatalog(String courseId, int semester, String session, float credits, String profId)
			throws CourseNotFoundException, SQLException {
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = conn.prepareStatement(SQLQuery.ADD_COURSE_CATALOG);

		stmt.setString(1, courseId);
		stmt.setString(2, profId);
		stmt.setInt(3, semester);
		stmt.setString(4, session);
		stmt.setFloat(5, credits);

		int rows = stmt.executeUpdate();
		if (rows == 0) {
			throw new CourseNotFoundException(courseId);

		} else {
			return true;
		}
	}

	/**
	 * Modify course catalog.
	 *
	 * @param courseId the course id
	 * @param semester the semester
	 * @param session  the session
	 * @param credits  the credits
	 * @param profId   the prof id
	 * @return true, if successful
	 */
	@Override
	public boolean modifyCourseCatalog(String courseId, int semester, String session, float credits, String profId)
			throws CourseNotFoundException, SQLException {
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = conn.prepareStatement(SQLQuery.MODIFY_COURSE_CATALOG);

		stmt.setString(1, profId);
		stmt.setInt(2, semester);
		stmt.setString(3, session);
		stmt.setFloat(4, credits);
		stmt.setString(5, courseId);

		int rows = stmt.executeUpdate();
		if (rows == 0) {
			throw new CourseNotFoundException(courseId);

		} else {
			return true;
		}
	}

	/**
	 * Gets the admin by id.
	 *
	 * @param userId the user id
	 * @return the admin by id
	 */
	@Override
	public Admin getAdminById(String userId) throws UserNotFoundException, SQLException {
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = conn.prepareStatement(SQLQuery.GET_ADMIN_BY_ID);

		stmt.setString(1, userId);

		ResultSet resultSet = stmt.executeQuery();

		if (!resultSet.next()) {
			throw new UserNotFoundException(userId);

		} else {
			Admin admin = new Admin();
			admin.setAdminID(resultSet.getInt("adminid"));
			admin.setAdminName(resultSet.getString("name"));
			admin.setEmailID(resultSet.getString("email"));
			admin.setDesignation(resultSet.getString("designation"));
			admin.setStatus(resultSet.getString("status"));
			return admin;
		}
	}

	/**
	 * Gets the boolean constants.
	 *
	 * @param key the key
	 * @return the boolean constants
	 */
	private boolean getBooleanConstants(String key) throws ConstantFlagNotSetException, SQLException {
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = conn.prepareStatement(SQLQuery.GET_FLAG);
		stmt.setString(1, key);
		ResultSet resultSet = stmt.executeQuery();

		if (resultSet.next()) {
			String flag = resultSet.getString("value");
			if (flag.equals(Constants.TRUE)) {
				return true;
			} else {
				return false;
			}
		} else {
			throw new ConstantFlagNotSetException(key);
		}
	}

	/**
	 * Edit the student permission.
	 *
	 * @param studentId the student id
	 * @param flag      boolean
	 * @return true, if successful
	 */
	@Override
	public boolean editStudentPermission(String studentId, boolean flag) throws UserNotFoundException, SQLException {
			Connection conn = DBUtils.getConnection();
			PreparedStatement stmt = conn.prepareStatement(SQLQuery.EDIT_STUDENT_PERMISSION);

			if (flag) {
				stmt.setString(1, Constants.TRUE);
			} else {
				stmt.setString(1, Constants.FALSE);
			}
			stmt.setString(1, studentId);
			int rows = stmt.executeUpdate();

			if (rows == 0) {
				throw new UserEmailNotFoundException(studentId));
			} else {
				return true;
			}
	}

	public boolean removeStudent(String studentId) throws UserNotFoundException, SQLException {
		Connection conn = DBUtils.getConnection();

		conn.setAutoCommit(false);
		Savepoint savePoint = conn.setSavepoint();

		PreparedStatement stmt = conn.prepareStatement(SQLQuery.REMOVE_STUDENT_PROFILE);
		stmt.setString(1, studentId);

		int rows = stmt.executeUpdate();
		if (rows == 0) {
			conn.rollback(savePoint);
			conn.setAutoCommit(true);

			throw new UserNotFoundException(studentId);

		} else {
			stmt = conn.prepareStatement(SQLQuery.REMOVE_STUDENT_AUTH);
			stmt.setString(1, studentId);
			rows = stmt.executeUpdate();

			if (rows == 0) {
				conn.rollback(savePoint);
				conn.setAutoCommit(true);

				throw new UserNotFoundException(studentId);
			} else {
				conn.commit();
				conn.setAutoCommit(true);
				return true;
			}
		}
	}

	/**
	 * Gets the course registration flag.
	 *
	 * @return the course registration flag
	 */
	@Override
	public boolean getCourseRegistrationFlag() throws ConstantFlagNotSetException, SQLException {
		return getBooleanConstants(Constants.COURSE_WINDOW);
	}

	/**
	 * Gets the payment flag.
	 *
	 * @return the payment flag
	 */
	@Override
	public boolean getPaymentFlag() throws ConstantFlagNotSetException, SQLException {
		return getBooleanConstants(Constants.PAYMENT_WINDOW);
	}

	/**
	 * Gets the professor flag.
	 *
	 * @return the professor flag
	 */
	@Override
	public boolean getProfessorFlag() throws ConstantFlagNotSetException, SQLException {
		return getBooleanConstants(Constants.PROFESSOR_WINDOW);
	}

}
