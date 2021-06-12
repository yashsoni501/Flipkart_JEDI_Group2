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
		// TODO Auto-generated method stub
		return adminDAO.addCourse(courseName, department);
	}

	@Override
	public boolean removeCourse(String courseId) {
		// TODO Auto-generated method stub
		return adminDAO.removeCourse(courseId);
	}

	@Override
	public boolean addProfessor(String professorId, String name, String department, String emailId) {
		// TODO Auto-generated method stub
		return adminDAO.addProfessor(professorId, name, department, emailId);
	}

	@Override
	public boolean addStudent(String studentId, String name, String department, String emailId, String session) {
		// TODO Auto-generated method stub
		return adminDAO.addStudent(studentId, name, department, emailId, session);
	}

	@Override
	public boolean setCourseRegistrationFlag(boolean flag) {
		// TODO Auto-generated method stub
		return adminDAO.setCourseRegistrationFlag(flag);
	}

	@Override
	public boolean setPaymentFlag(boolean flag) {
		// TODO Auto-generated method stub
		return adminDAO.setPaymentFlag(flag);
	}

	@Override
	public boolean removeProfessor(String profId) {
		// TODO Auto-generated method stub
		return adminDAO.removeProfessor(profId);
	}

	@Override
	public boolean modifyProfessor(String profId, String professorName, String department) {
		// TODO Auto-generated method stub
		return adminDAO.modifyProfessor(profId, professorName, department);
	}

	@Override
	public boolean removeCourseCatalog(String courseId) {
		// TODO Auto-generated method stub
		return adminDAO.removeCourseCatalog(courseId);
	}

	@Override
	public boolean modifyCourse(String courseId, String courseName, String department) {
		// TODO Auto-generated method stub
		return adminDAO.modifyCourse(courseId, courseName, department);
	}

	@Override
	public boolean addCourseCatalog(String courseId, int semester, String session, int credits, String profId) {
		// TODO Auto-generated method stub
		return adminDAO.addCourseCatalog(courseId, semester, session, credits, profId);
	}

	@Override
	public boolean modifyCourseCatalog(String courseId, int semester, String session, int credits, String profId) {
		// TODO Auto-generated method stub
		return adminDAO.modifyCourseCatalog(courseId, semester, session, credits, profId);
	}

	@Override
	public boolean modifyStudent(String studentId, String studentName, String department, String session) {
		// TODO Auto-generated method stub
		return adminDAO.modifyStudnet(studentId, studentName, department, session);
	}

	@Override
	public boolean generateReportCard(String session, int semester) {
		// TODO Auto-generated method stub
		return adminDAO.generateReportCard(session, semester);
	}

	@Override
	public Admin getAdminById(String userId) {
		// TODO Auto-generated method stub
		return adminDAO.getAdminById(userId);
	}

}
