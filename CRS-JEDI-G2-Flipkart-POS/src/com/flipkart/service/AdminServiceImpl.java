package com.flipkart.service;

import java.sql.SQLException;

import com.flipkart.DAO.AdminDAOImpl;
import com.flipkart.DAO.AdminDAOInterface;
import com.flipkart.bean.Admin;
import com.flipkart.exception.ConstantFlagNotSetException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.InvalidCredentialsException;
import com.flipkart.exception.UserEmailAlreadyInUseException;
import com.flipkart.exception.UserEmailNotFoundException;
import com.flipkart.exception.UserNotFoundException;

/**
 * The Class AdminServiceImpl.
 */
public class AdminServiceImpl implements AdminInterface {

	/** The instance. */
	public static volatile AdminServiceImpl instance = null;

	/** The admin DAO. */
	AdminDAOInterface adminDAO = AdminDAOImpl.getInstance();

	/**
	 * Gets the single instance of AdminServiceImpl.
	 *
	 * @return single instance of AdminServiceImpl
	 */
	public static AdminServiceImpl getInstance() {
		if (instance == null) {
			// This is a synchronized block, when multiple threads will access this instance
			synchronized (AdminServiceImpl.class) {
				instance = new AdminServiceImpl();
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
	 * @throws SQLException the SQL exception
	 */
	@Override
	public boolean addCourse(String courseName, String department) throws SQLException {
		// Auto-generated method stub
		return adminDAO.addCourse(courseName, department);
	}

	/**
	 * Removes the course.
	 *
	 * @param courseId the course id
	 * @return true, if successful
	 * @throws CourseNotFoundException the course not found exception
	 * @throws SQLException            the SQL exception
	 */
	@Override
	public boolean removeCourse(String courseId) throws CourseNotFoundException, SQLException {
		// Auto-generated method stub
		return adminDAO.removeCourse(courseId);
	}

	/**
	 * Adds the professor.
	 *
	 * @param name       the name
	 * @param emailId    the email id
	 * @param password   the password
	 * @param department the department
	 * @return true, if successful
	 * @throws UserEmailAlreadyInUseException the user email already in use
	 *                                        exception
	 * @throws SQLException                   the SQL exception
	 * @throws InvalidCredentialsException    the invalid credentials exception
	 * @throws UserEmailNotFoundException     the user email not found exception
	 */
	@Override
	public boolean addProfessor(String name, String emailId, String password, String department)
			throws UserEmailAlreadyInUseException, SQLException, InvalidCredentialsException,
			UserEmailNotFoundException {
		// Auto-generated method stub
		return adminDAO.addProfessor(name, emailId, password, department);
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
	 * @throws UserEmailAlreadyInUseException the user email already in use
	 *                                        exception
	 * @throws SQLException                   the SQL exception
	 * @throws InvalidCredentialsException    the invalid credentials exception
	 * @throws UserEmailNotFoundException     the user email not found exception
	 */
	@Override
	public boolean addStudent(String name, String emailId, String password, String department, String session)
			throws UserEmailAlreadyInUseException, SQLException, InvalidCredentialsException,
			UserEmailNotFoundException {
		// Auto-generated method stub
		return adminDAO.addStudent(name, emailId, password, department, session);
	}

	/**
	 * Sets the course registration flag.
	 *
	 * @param flag the flag
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 */
	@Override
	public boolean setCourseRegistrationFlag(boolean flag) throws SQLException {
		// Auto-generated method stub
		return adminDAO.setCourseRegistrationFlag(flag);
	}

	/**
	 * Sets the payment flag.
	 *
	 * @param flag the flag
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 */
	@Override
	public boolean setPaymentFlag(boolean flag) throws SQLException {
		// Auto-generated method stub
		return adminDAO.setPaymentFlag(flag);
	}

	/**
	 * Removes the professor.
	 *
	 * @param profId the prof id
	 * @return true, if successful
	 * @throws UserNotFoundException the user not found exception
	 * @throws SQLException          the SQL exception
	 */
	@Override
	public boolean removeProfessor(String profId) throws UserNotFoundException, SQLException {
		// Auto-generated method stub
		return adminDAO.removeProfessor(profId);
	}

	/**
	 * Modify professor.
	 *
	 * @param profId        the prof id
	 * @param professorName the professor name
	 * @param department    the department
	 * @return true, if successful
	 * @throws UserNotFoundException the user not found exception
	 * @throws SQLException          the SQL exception
	 */
	@Override
	public boolean modifyProfessor(String profId, String professorName, String department)
			throws UserNotFoundException, SQLException {
		// Auto-generated method stub
		return adminDAO.modifyProfessor(profId, professorName, department);
	}

	/**
	 * Removes the course catalog.
	 *
	 * @param courseId the course id
	 * @return true, if successful
	 * @throws CourseNotFoundException the course not found exception
	 * @throws SQLException            the SQL exception
	 */
	@Override
	public boolean removeCourseCatalog(String courseId) throws CourseNotFoundException, SQLException {
		// Auto-generated method stub
		return adminDAO.removeCourseCatalog(courseId);
	}

	/**
	 * Modify course.
	 *
	 * @param courseId   the course id
	 * @param courseName the course name
	 * @param department the department
	 * @return true, if successful
	 * @throws CourseNotFoundException the course not found exception
	 * @throws SQLException            the SQL exception
	 */
	@Override
	public boolean modifyCourse(String courseId, String courseName, String department)
			throws CourseNotFoundException, SQLException {
		// Auto-generated method stub
		return adminDAO.modifyCourse(courseId, courseName, department);
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
	 * @throws CourseNotFoundException the course not found exception
	 * @throws SQLException            the SQL exception
	 */
	@Override
	public boolean addCourseCatalog(String courseId, int semester, String session, float credits, String profId)
			throws CourseNotFoundException, SQLException {
		// Auto-generated method stub
		return adminDAO.addCourseCatalog(courseId, semester, session, credits, profId);
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
	 * @throws CourseNotFoundException the course not found exception
	 * @throws SQLException            the SQL exception
	 */
	@Override
	public boolean modifyCourseCatalog(String courseId, int semester, String session, float credits, String profId)
			throws CourseNotFoundException, SQLException {
		// Auto-generated method stub
		return adminDAO.modifyCourseCatalog(courseId, semester, session, credits, profId);
	}

	/**
	 * Gets the admin by id.
	 *
	 * @param userId the user id
	 * @return the admin by id
	 * @throws UserNotFoundException the user not found exception
	 * @throws SQLException          the SQL exception
	 */
	@Override
	public Admin getAdminById(String userId) throws UserNotFoundException, SQLException {
		// Auto-generated method stub
		return adminDAO.getAdminById(userId);
	}

	/**
	 * Sets the professor flag.
	 *
	 * @param flag the flag
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 */
	@Override
	public boolean setProfessorFlag(boolean flag) throws SQLException {
		return adminDAO.setProfessorFlag(flag);
	}

	/**
	 * Gets the course registration flag.
	 *
	 * @return the course registration flag
	 * @throws ConstantFlagNotSetException the constant flag not set exception
	 * @throws SQLException                the SQL exception
	 */
	@Override
	public boolean getCourseRegistrationFlag() throws ConstantFlagNotSetException, SQLException {
		return adminDAO.getCourseRegistrationFlag();
	}

	/**
	 * Gets the payment flag.
	 *
	 * @return the payment flag
	 * @throws ConstantFlagNotSetException the constant flag not set exception
	 * @throws SQLException                the SQL exception
	 */
	@Override
	public boolean getPaymentFlag() throws ConstantFlagNotSetException, SQLException {
		return adminDAO.getPaymentFlag();
	}

	/**
	 * Gets the professor flag.
	 *
	 * @return the professor flag
	 * @throws ConstantFlagNotSetException the constant flag not set exception
	 * @throws SQLException                the SQL exception
	 */
	@Override
	public boolean getProfessorFlag() throws ConstantFlagNotSetException, SQLException {
		return adminDAO.getProfessorFlag();
	}

	/**
	 * Edits the student permission.
	 *
	 * @param studentId the student id
	 * @param flag      the flag
	 * @return true, if successful
	 * @throws UserNotFoundException the user not found exception
	 * @throws SQLException          the SQL exception
	 */
	@Override
	public boolean editStudentPermission(String studentId, boolean flag) throws UserNotFoundException, SQLException {
		// Auto-generated method stub
		return adminDAO.editStudentPermission(studentId, flag);
	}

	/**
	 * Modify student.
	 *
	 * @param email       the email
	 * @param studentName the student name
	 * @param department  the department
	 * @param session     the session
	 * @return true, if successful
	 * @throws SQLException               the SQL exception
	 * @throws UserEmailNotFoundException the user email not found exception
	 */
	@Override
	public boolean modifyStudent(String email, String studentName, String department, String session)
			throws SQLException, UserEmailNotFoundException {
		// Auto-generated method stub
		return adminDAO.modifyStudent(email, studentName, department, session);
	}

	/**
	 * Removes the student.
	 *
	 * @param studentId the student id
	 * @return true, if successful
	 * @throws UserNotFoundException the user not found exception
	 * @throws SQLException          the SQL exception
	 */
	@Override
	public boolean removeStudent(String studentId) throws UserNotFoundException, SQLException {
		// Auto-generated method stub
		return adminDAO.removeStudent(studentId);
	}

}
