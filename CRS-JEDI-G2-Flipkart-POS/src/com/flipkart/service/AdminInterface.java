/**
 * 
 */
package com.flipkart.service;

import com.flipkart.bean.Admin;

// TODO: Auto-generated Javadoc
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
	 */
	public boolean addCourse(String courseName, String department);

	/**
	 * Removes the course.
	 *
	 * @param courseId the course id
	 * @return true, if successful
	 */
	public boolean removeCourse(String courseId);

	/**
	 * Adds the professor.
	 *
	 * @param name the name
	 * @param emailId the email id
	 * @param password the password
	 * @param department the department
	 * @return true, if successful
	 */
	public boolean addProfessor(String name, String emailId, String password, String department);

	/**
	 * Adds the student.
	 *
	 * @param name the name
	 * @param emailId the email id
	 * @param password the password
	 * @param department the department
	 * @param session the session
	 * @return true, if successful
	 */
	public boolean addStudent(String name, String emailId, String password, String department, String session);

	/**
	 * Sets the course registration flag.
	 *
	 * @param flag the flag
	 * @return true, if successful
	 */
	public boolean setCourseRegistrationFlag(boolean flag);

	/**
	 * Sets the payment flag.
	 *
	 * @param flag the flag
	 * @return true, if successful
	 */
	public boolean setPaymentFlag(boolean flag);

	/**
	 * Sets the professor flag.
	 *
	 * @param flag the flag
	 * @return true, if successful
	 */
	public boolean setProfessorFlag(boolean flag);

	/**
	 * Gets the course registration flag.
	 *
	 * @return the course registration flag
	 */
	public boolean getCourseRegistrationFlag();

	/**
	 * Gets the payment flag.
	 *
	 * @return the payment flag
	 */
	public boolean getPaymentFlag();

	/**
	 * Gets the professor flag.
	 *
	 * @return the professor flag
	 */
	public boolean getProfessorFlag();

	/**
	 * Removes the professor.
	 *
	 * @param profId the prof id
	 * @return true, if successful
	 */
	public boolean removeProfessor(String profId);

	/**
	 * Modify professor.
	 *
	 * @param profId the prof id
	 * @param professorName the professor name
	 * @param department the department
	 * @return true, if successful
	 */
	public boolean modifyProfessor(String profId, String professorName, String department);

	/**
	 * Modify student.
	 *
	 * @param studentId the student id
	 * @param studentName the student name
	 * @param department the department
	 * @param session the session
	 * @return true, if successful
	 */
	public boolean modifyStudent(String studentId, String studentName, String department, String session);

	/**
	 * Removes the course catalog.
	 *
	 * @param courseId the course id
	 * @return true, if successful
	 */
	public boolean removeCourseCatalog(String courseId);

	/**
	 * Modify course.
	 *
	 * @param courseId the course id
	 * @param courseName the course name
	 * @param department the department
	 * @return true, if successful
	 */
	public boolean modifyCourse(String courseId, String courseName, String department);

	/**
	 * Adds the course catalog.
	 *
	 * @param courseId the course id
	 * @param semester the semester
	 * @param session the session
	 * @param credits the credits
	 * @param profId the prof id
	 * @return true, if successful
	 */
	public boolean addCourseCatalog(String courseId, int semester, String session, float credits, String profId);

	/**
	 * Modify course catalog.
	 *
	 * @param courseId the course id
	 * @param semester the semester
	 * @param session the session
	 * @param credits the credits
	 * @param profId the prof id
	 * @return true, if successful
	 */
	public boolean modifyCourseCatalog(String courseId, int semester, String session, float credits, String profId);

	/**
	 * Gets the admin by id.
	 *
	 * @param userId the user id
	 * @return the admin by id
	 */
	public Admin getAdminById(String userId);

	/**
	 * Removes the student.
	 *
	 * @param studentId the student id
	 * @return true, if successful
	 */
	public boolean removeStudent(String studentId);
}
