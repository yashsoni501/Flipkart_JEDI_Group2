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

public class AdminServiceImpl implements AdminInterface {

	public static volatile AdminServiceImpl instance = null;
	AdminDAOInterface adminDAO = AdminDAOImpl.getInstance();

	public static AdminServiceImpl getInstance() {
		if (instance == null) {
			// This is a synchronized block, when multiple threads will access this instance
			synchronized (AdminServiceImpl.class) {
				instance = new AdminServiceImpl();
			}
		}
		return instance;
	}

	@Override
	public boolean addCourse(String courseName, String department) throws SQLException {
		// Auto-generated method stub
		return adminDAO.addCourse(courseName, department);
	}

	@Override
	public boolean removeCourse(String courseId) throws CourseNotFoundException, SQLException {
		// Auto-generated method stub
		return adminDAO.removeCourse(courseId);
	}

	@Override
	public boolean addProfessor(String name, String emailId, String password, String department)
			throws UserEmailAlreadyInUseException, SQLException, InvalidCredentialsException,
			UserEmailNotFoundException {
		// Auto-generated method stub
		return adminDAO.addProfessor(name, emailId, password, department);
	}

	@Override
	public boolean addStudent(String name, String emailId, String password, String department, String session)
			throws UserEmailAlreadyInUseException, SQLException, InvalidCredentialsException,
			UserEmailNotFoundException {
		//  Auto-generated method stub
		return adminDAO.addStudent(name, emailId, password, department, session);
	}

	@Override
	public boolean setCourseRegistrationFlag(boolean flag) throws SQLException {
		// Auto-generated method stub
		return adminDAO.setCourseRegistrationFlag(flag);
	}

	@Override
	public boolean setPaymentFlag(boolean flag) throws SQLException {
		// Auto-generated method stub
		return adminDAO.setPaymentFlag(flag);
	}

	@Override
	public boolean removeProfessor(String profId) throws UserNotFoundException, SQLException {
		// Auto-generated method stub
		return adminDAO.removeProfessor(profId);
	}

	@Override
	public boolean modifyProfessor(String profId, String professorName, String department)
			throws UserNotFoundException, SQLException {
		// Auto-generated method stub
		return adminDAO.modifyProfessor(profId, professorName, department);
	}

	@Override
	public boolean removeCourseCatalog(String courseId) throws CourseNotFoundException, SQLException {
		// Auto-generated method stub
		return adminDAO.removeCourseCatalog(courseId);
	}

	@Override
	public boolean modifyCourse(String courseId, String courseName, String department)
			throws CourseNotFoundException, SQLException {
		// Auto-generated method stub
		return adminDAO.modifyCourse(courseId, courseName, department);
	}

	@Override
	public boolean addCourseCatalog(String courseId, int semester, String session, float credits, String profId)
			throws CourseNotFoundException, SQLException {
		// Auto-generated method stub
		return adminDAO.addCourseCatalog(courseId, semester, session, credits, profId);
	}

	@Override
	public boolean modifyCourseCatalog(String courseId, int semester, String session, float credits, String profId)
			throws CourseNotFoundException, SQLException {
		// Auto-generated method stub
		return adminDAO.modifyCourseCatalog(courseId, semester, session, credits, profId);
	}

	@Override
	public Admin getAdminById(String userId) throws UserNotFoundException, SQLException {
		// Auto-generated method stub
		return adminDAO.getAdminById(userId);
	}

	@Override
	public boolean setProfessorFlag(boolean flag) throws SQLException {
		return adminDAO.setProfessorFlag(flag);
	}

	@Override
	public boolean getCourseRegistrationFlag() throws ConstantFlagNotSetException, SQLException {
		return adminDAO.getCourseRegistrationFlag();
	}

	@Override
	public boolean getPaymentFlag() throws ConstantFlagNotSetException, SQLException {
		return adminDAO.getPaymentFlag();
	}

	@Override
	public boolean getProfessorFlag() throws ConstantFlagNotSetException, SQLException {
		return adminDAO.getProfessorFlag();
	}

	@Override
	public boolean editStudentPermission(String studentId, boolean flag) {
		// Auto-generated method stub
		return adminDAO.editStudentPermission(studentId, flag);
	}

	@Override
	public boolean modifyStudent(String email, String studentName, String department, String session) throws UserNotFoundException, SQLException {
		// Auto-generated method stub
		return adminDAO.modifyStudent(email, studentName, department, session);
	}

	@Override
	public boolean removeStudent(String studentId) throws UserNotFoundException, SQLException {
		// Auto-generated method stub
		return adminDAO.removeStudent(studentId);
	}

}
