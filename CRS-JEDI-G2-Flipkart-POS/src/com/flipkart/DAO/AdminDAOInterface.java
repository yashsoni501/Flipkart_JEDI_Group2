package com.flipkart.DAO;

import com.flipkart.bean.Admin;

public interface AdminDAOInterface {
	public boolean addCourse(String courseName, String Department);

	public boolean removeCourse(String courseId);

	public boolean addProfessor(String name, String emailId, String password, String department);

	public boolean addStudent(String name, String emailId, String password, String department, String session);

	public boolean setCourseRegistrationFlag(boolean flag);

	public boolean setPaymentFlag(boolean flag);

	public boolean setProfessorFlag(boolean flag);

	public boolean getCourseRegistrationFlag();

	public boolean getPaymentFlag();

	public boolean getProfessorFlag();

	public boolean removeProfessor(String profId);

	public boolean modifyProfessor(String profId, String professorName, String department);

	public boolean modifyStudnet(String studentId, String studentName, String department, String session);

	public boolean removeCourseCatalog(String courseId);

	public boolean modifyCourse(String courseId, String courseName, String department);

	public boolean addCourseCatalog(String courseId, int semester, String session, float credits, String profId);

	public boolean modifyCourseCatalog(String courseId, int semester, String session, float credits, String profId);

	public Admin getAdminById(String userId);
}
