package com.flipkart.DAO;

import java.sql.SQLException;

import com.flipkart.bean.Admin;
import com.flipkart.exception.ConstantFlagNotSetException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.InvalidCredentialsException;
import com.flipkart.exception.UserEmailAlreadyInUseException;
import com.flipkart.exception.UserEmailNotFoundException;
import com.flipkart.exception.UserNotFoundException;

public interface AdminDAOInterface {
	public boolean addCourse(String courseName, String Department) throws SQLException;

	public boolean removeCourse(String courseId) throws CourseNotFoundException, SQLException;

	public boolean addProfessor(String name, String emailId, String password, String department)
			throws UserEmailAlreadyInUseException, SQLException, InvalidCredentialsException,
			UserEmailNotFoundException, SQLException;

	public boolean addStudent(String name, String emailId, String password, String department, String session)
			throws UserEmailAlreadyInUseException, SQLException, InvalidCredentialsException,
			UserEmailNotFoundException;

	public boolean setCourseRegistrationFlag(boolean flag) throws SQLException;

	public boolean setPaymentFlag(boolean flag) throws SQLException;

	public boolean setProfessorFlag(boolean flag) throws SQLException;

	public boolean getCourseRegistrationFlag() throws ConstantFlagNotSetException, SQLException;

	public boolean getPaymentFlag() throws ConstantFlagNotSetException, SQLException;

	public boolean getProfessorFlag() throws ConstantFlagNotSetException, SQLException;

	public boolean removeProfessor(String profId) throws UserNotFoundException, SQLException;

	public boolean modifyProfessor(String profId, String professorName, String department)
			throws UserNotFoundException, SQLException;

	public boolean modifyStudent(String email, String studentName, String department, String session)
			throws SQLException, UserEmailNotFoundException;

	public boolean removeCourseCatalog(String courseId) throws CourseNotFoundException, SQLException;

	public boolean modifyCourse(String courseId, String courseName, String department)
			throws CourseNotFoundException, SQLException;

	public boolean addCourseCatalog(String courseId, int semester, String session, float credits, String profId)
			throws CourseNotFoundException, SQLException;

	public boolean modifyCourseCatalog(String courseId, int semester, String session, float credits, String profId)
			throws CourseNotFoundException, SQLException;

	public Admin getAdminById(String userId) throws UserNotFoundException, SQLException;

	public boolean editStudentPermission(String studentId, boolean flag) throws UserNotFoundException, SQLException;

	public boolean removeStudent(String studentId) throws UserNotFoundException, SQLException;
}
