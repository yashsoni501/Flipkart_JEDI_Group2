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
	 * @throws SQLException
	 */
	public boolean addCourse(String courseName, String department) throws SQLException;

	/**
	 * Removes the course.
	 *
	 * @param courseId the course id
	 * @return true, if successful
	 * @throws SQLException
	 * @throws CourseNotFoundException
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
	 * @throws SQLException
	 * @throws UserEmailAlreadyInUseException
	 * @throws UserEmailNotFoundException
	 * @throws InvalidCredentialsException
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
	 * @throws SQLException
	 * @throws UserEmailAlreadyInUseException
	 * @throws UserEmailNotFoundException
	 * @throws InvalidCredentialsException
	 */
	public boolean addStudent(String name, String emailId, String password, String department, String session)
			throws UserEmailAlreadyInUseException, SQLException, InvalidCredentialsException,
			UserEmailNotFoundException;

	/**
	 * Sets the course registration flag.
	 *
	 * @param flag the flag
	 * @return true, if successful
	 * @throws SQLException
	 */
	public boolean setCourseRegistrationFlag(boolean flag) throws SQLException;

	/**
	 * Sets the payment flag.
	 *
	 * @param flag the flag
	 * @return true, if successful
	 * @throws SQLException
	 */
	public boolean setPaymentFlag(boolean flag) throws SQLException;

	/**
	 * Sets the professor flag.
	 *
	 * @param flag the flag
	 * @return true, if successful
	 * @throws SQLException
	 */
	public boolean setProfessorFlag(boolean flag) throws SQLException;

	/**
	 * Gets the course registration flag.
	 *
	 * @return the course registration flag
	 * @throws SQLException
	 * @throws ConstantFlagNotSetException
	 */
	public boolean getCourseRegistrationFlag() throws ConstantFlagNotSetException, SQLException;

	/**
	 * Gets the payment flag.
	 *
	 * @return the payment flag
	 * @throws SQLException
	 * @throws ConstantFlagNotSetException
	 */
	public boolean getPaymentFlag() throws ConstantFlagNotSetException, SQLException;

	/**
	 * Gets the professor flag.
	 *
	 * @return the professor flag
	 * @throws SQLException
	 * @throws ConstantFlagNotSetException
	 */
	public boolean getProfessorFlag() throws ConstantFlagNotSetException, SQLException;

	/**
	 * Removes the professor.
	 *
	 * @param profId the prof id
	 * @return true, if successful
	 * @throws SQLException
	 * @throws UserNotFoundException
	 */
	public boolean removeProfessor(String profId) throws UserNotFoundException, SQLException;

	/**
	 * Modify professor.
	 *
	 * @param profId        the prof id
	 * @param professorName the professor name
	 * @param department    the department
	 * @return true, if successful
	 * @throws SQLException
	 * @throws UserNotFoundException
	 */
	public boolean modifyProfessor(String profId, String professorName, String department)
			throws UserNotFoundException, SQLException;

	/**
	 * Modify student.
	 *
	 * @param studentEmail   the student email
	 * @param studentName the student name
	 * @param department  the department
	 * @param session     the session
	 * @return true, if successful
	 * @throws SQLException
	 * @throws UserNotFoundException
	 */
	public boolean modifyStudent(String email, String studentName, String department, String session)
			throws UserNotFoundException, SQLException;

	/**
	 * Removes the course catalog.
	 *
	 * @param courseId the course id
	 * @return true, if successful
	 * @throws SQLException
	 * @throws CourseNotFoundException
	 */
	public boolean removeCourseCatalog(String courseId) throws CourseNotFoundException, SQLException;

	/**
	 * Modify course.
	 *
	 * @param courseId   the course id
	 * @param courseName the course name
	 * @param department the department
	 * @return true, if successful
	 * @throws SQLException
	 * @throws CourseNotFoundException
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
	 * @throws SQLException
	 * @throws CourseNotFoundException
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
	 * @throws SQLException
	 * @throws CourseNotFoundException
	 */
	public boolean modifyCourseCatalog(String courseId, int semester, String session, float credits, String profId)
			throws CourseNotFoundException, SQLException;

	/**
	 * Gets the admin by id.
	 *
	 * @param userId the user id
	 * @return the admin by id
	 * @throws SQLException
	 * @throws UserNotFoundException
	 */
	public Admin getAdminById(String userId) throws UserNotFoundException, SQLException;

	/**
	 * Revokes the student permission.
	 *
	 * @param studentId the student id
	 * @param flag      boolean
	 * @return true, if successful
	 */
	public boolean editStudentPermission(String studentId, boolean flag);

	/**
	 * Remove Student.
	 *
	 * @param studentId the studentId
	 * @return true, if successful
	 * @throws SQLException
	 * @throws UserNotFoundException
	 */
	public boolean removeStudent(String studentId) throws UserNotFoundException, SQLException;
}
