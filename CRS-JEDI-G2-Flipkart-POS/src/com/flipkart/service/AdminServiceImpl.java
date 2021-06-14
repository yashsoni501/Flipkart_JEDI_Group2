package com.flipkart.service;

import com.flipkart.DAO.AdminDAOImpl;
import com.flipkart.DAO.AdminDAOInterface;
import com.flipkart.bean.Admin;

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
	public boolean addCourse(String courseName, String department) {
		// Auto-generated method stub
		return adminDAO.addCourse(courseName, department);
	}

	@Override
	public boolean removeCourse(String courseId) {
		// Auto-generated method stub
		return adminDAO.removeCourse(courseId);
	}

	@Override
	public boolean addProfessor(String name, String emailId, String password, String department) {
		// Auto-generated method stub
		return adminDAO.addProfessor(name, emailId, password, department);
	}

	@Override
	public boolean addStudent(String name, String emailId, String password, String department, String session) {
		// Auto-generated method stub
		return adminDAO.addStudent(name, emailId, password, department, session);
	}

	@Override
	public boolean setCourseRegistrationFlag(boolean flag) {
		// Auto-generated method stub
		return adminDAO.setCourseRegistrationFlag(flag);
	}

	@Override
	public boolean setPaymentFlag(boolean flag) {
		// Auto-generated method stub
		return adminDAO.setPaymentFlag(flag);
	}

	@Override
	public boolean removeProfessor(String profId) {
		// Auto-generated method stub
		return adminDAO.removeProfessor(profId);
	}

	@Override
	public boolean modifyProfessor(String profId, String professorName, String department) {
		// Auto-generated method stub
		return adminDAO.modifyProfessor(profId, professorName, department);
	}

	@Override
	public boolean removeCourseCatalog(String courseId) {
		// Auto-generated method stub
		return adminDAO.removeCourseCatalog(courseId);
	}

	@Override
	public boolean modifyCourse(String courseId, String courseName, String department) {
		// Auto-generated method stub
		return adminDAO.modifyCourse(courseId, courseName, department);
	}

	@Override
	public boolean addCourseCatalog(String courseId, int semester, String session, float credits, String profId) {
		// Auto-generated method stub
		return adminDAO.addCourseCatalog(courseId, semester, session, credits, profId);
	}

	@Override
	public boolean modifyCourseCatalog(String courseId, int semester, String session, float credits, String profId) {
		// Auto-generated method stub
		return adminDAO.modifyCourseCatalog(courseId, semester, session, credits, profId);
	}

	@Override
	public Admin getAdminById(String userId) {
		// Auto-generated method stub
		return adminDAO.getAdminById(userId);
	}

	@Override
	public boolean setProfessorFlag(boolean flag) {
		return adminDAO.setProfessorFlag(flag);
	}

	@Override
	public boolean getCourseRegistrationFlag() {
		return adminDAO.getCourseRegistrationFlag();
	}

	@Override
	public boolean getPaymentFlag() {
		return adminDAO.getPaymentFlag();
	}

	@Override
	public boolean getProfessorFlag() {
		return adminDAO.getProfessorFlag();
	}

	@Override
	public boolean editStudentPermission(String studentId, boolean flag) {
		// Auto-generated method stub
		return adminDAO.editStudentPermission(studentId, flag);
	}

	@Override
	public boolean modifyStudent(String email, String studentName, String department, String session) {
		// Auto-generated method stub
		return adminDAO.modifyStudent(email, studentName, department, session);
	}

	@Override
	public boolean removeStudent(String studentId) {
		// Auto-generated method stub
		return adminDAO.removeStudent(studentId);
	}

}
