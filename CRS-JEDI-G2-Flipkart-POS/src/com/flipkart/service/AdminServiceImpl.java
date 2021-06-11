package com.flipkart.service;

public class AdminServiceImpl implements AdminInterface {

	public static volatile AdminServiceImpl instance = null;

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
	public boolean addCourse(String courseName, String Department) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeCourse(String courseId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addProfessor(String professorId, String name, String department, String emailId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addStudent(String studentId, String name, String department, String emailId, String session) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean enableOrDisableCourseRegistration(boolean flag) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean enableOrDisablePayment(boolean flag) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean generateReportCard(String session) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeProfessor(String profId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modifyProfessor(String profId, String professorName, String department) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modifyStudnet(String studentId, String studentName, String department, String session) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeCourseCatalog(String courseId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modifyCourse(String courseId, String courseName, String department) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addCourseCatalog(String courseId, int semester, String session, int credits) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modifyCourseCatalog(String courseId, int semester, String session, int credits) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void enableCourseRegistration() {
		// TODO Auto-generated method stub

	}

	@Override
	public void disableCourseRegistration() {
		// TODO Auto-generated method stub

	}

	@Override
	public void enablePayment() {
		// TODO Auto-generated method stub

	}

	@Override
	public void disablePayment() {
		// TODO Auto-generated method stub

	}

}
