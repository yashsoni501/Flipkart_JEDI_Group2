/**
 * 
 */
package com.flipkart.service;

import java.sql.SQLException;

import com.flipkart.bean.Admin;
import com.flipkart.exception.ConstantFlagNotSetException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.InvalidCredentialsException;
import com.flipkart.exception.UserEmailAlreadyInUseException;
import com.flipkart.exception.UserEmailNotFoundException;
import com.flipkart.exception.UserNotFoundException;

// Auto-generated Javadoc
/**
 * The Interface AdminInterface.
 *
 * @author aysh
 */
public interface AdminInterface {

	/**
	 * Adds the course.
	 *
	 * @param courseName the course name
	 * @param department the department
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 */
	public boolean addCourse(String courseName, String department) throws SQLException;

	/**
	 * Removes the course.
	 *
	 * @param courseId the course id
	 * @return true, if successful
	 * @throws CourseNotFoundException the course not found exception
	 * @throws SQLException            the SQL exception
	 */
	public boolean removeCourse(String courseId) throws CourseNotFoundException, SQLException;

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
	public boolean addProfessor(String name, String emailId, String password, String department)
			throws UserEmailAlreadyInUseException, SQLException, InvalidCredentialsException,
			UserEmailNotFoundException;

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
	public boolean addStudent(String name, String emailId, String password, String department, String session)
			throws UserEmailAlreadyInUseException, SQLException, InvalidCredentialsException,
			UserEmailNotFoundException;

	/**
	 * Sets the course registration flag.
	 *
	 * @param flag the flag
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 */
	public boolean setCourseRegistrationFlag(boolean flag) throws SQLException;

	/**
	 * Sets the payment flag.
	 *
	 * @param flag the flag
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 */
	public boolean setPaymentFlag(boolean flag) throws SQLException;

	/**
	 * Sets the professor flag.
	 *
	 * @param flag the flag
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 */
	public boolean setProfessorFlag(boolean flag) throws SQLException;

	/**
	 * Gets the course registration flag.
	 *
	 * @return the course registration flag
	 * @throws ConstantFlagNotSetException the constant flag not set exception
	 * @throws SQLException                the SQL exception
	 */
	public boolean getCourseRegistrationFlag() throws ConstantFlagNotSetException, SQLException;

	/**
	 * Gets the payment flag.
	 *
	 * @return the payment flag
	 * @throws ConstantFlagNotSetException the constant flag not set exception
	 * @throws SQLException                the SQL exception
	 */
	public boolean getPaymentFlag() throws ConstantFlagNotSetException, SQLException;

	/**
	 * Gets the professor flag.
	 *
	 * @return the professor flag
	 * @throws ConstantFlagNotSetException the constant flag not set exception
	 * @throws SQLException                the SQL exception
	 */
	public boolean getProfessorFlag() throws ConstantFlagNotSetException, SQLException;

	/**
	 * Removes the professor.
	 *
	 * @param profId the prof id
	 * @return true, if successful
	 * @throws UserNotFoundException the user not found exception
	 * @throws SQLException          the SQL exception
	 */
	public boolean removeProfessor(String profId) throws UserNotFoundException, SQLException;

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
	public boolean modifyProfessor(String profId, String professorName, String department)
			throws UserNotFoundException, SQLException;

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
	public boolean modifyStudent(String email, String studentName, String department, String session)
			throws SQLException, UserEmailNotFoundException;

	/**
	 * Removes the course catalog.
	 *
	 * @param courseId the course id
	 * @return true, if successful
	 * @throws CourseNotFoundException the course not found exception
	 * @throws SQLException            the SQL exception
	 */
	public boolean removeCourseCatalog(String courseId) throws CourseNotFoundException, SQLException;

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
	public boolean modifyCourse(String courseId, String courseName, String department)
			throws CourseNotFoundException, SQLException;

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
	public boolean addCourseCatalog(String courseId, int semester, String session, float credits, String profId)
			throws CourseNotFoundException, SQLException;

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
	public boolean modifyCourseCatalog(String courseId, int semester, String session, float credits, String profId)
			throws CourseNotFoundException, SQLException;

	/**
	 * Gets the admin by id.
	 *
	 * @param userId the user id
	 * @return the admin by id
	 * @throws UserNotFoundException the user not found exception
	 * @throws SQLException          the SQL exception
	 */
	public Admin getAdminById(String userId) throws UserNotFoundException, SQLException;

	/**
	 * Revokes the student permission.
	 *
	 * @param studentId the student id
	 * @param flag      boolean
	 * @return true, if successful
	 * @throws UserNotFoundException the user not found exception
	 * @throws SQLException          the SQL exception
	 */
	public boolean editStudentPermission(String studentId, boolean flag) throws UserNotFoundException, SQLException;

	/**
	 * Remove Student.
	 *
	 * @param studentId the studentId
	 * @return true, if successful
	 * @throws UserNotFoundException the user not found exception
	 * @throws SQLException          the SQL exception
	 */
	public boolean removeStudent(String studentId) throws UserNotFoundException, SQLException;
}
