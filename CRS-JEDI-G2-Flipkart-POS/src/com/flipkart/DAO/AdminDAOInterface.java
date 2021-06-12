package com.flipkart.DAO;

import com.flipkart.bean.Admin;

public interface AdminDAOInterface {
	public boolean addCourse(String courseName, String Department);

	public boolean removeCourse(String courseId);

	public boolean addProfessor(String professorId, String name, String department, String emailId);

	public boolean addStudent(String studentId, String name, String department, String emailId, String session);

	public boolean setCourseRegistrationFlag(boolean flag);

	public boolean setPaymentFlag(boolean flag);

	public boolean generateReportCard(String session, int semester);

	public boolean removeProfessor(String profId);

	public boolean modifyProfessor(String profId, String professorName, String department);

	public boolean modifyStudnet(String studentId, String studentName, String department, String session);

	public boolean removeCourseCatalog(String courseId);

	public boolean modifyCourse(String courseId, String courseName, String department);

	public boolean addCourseCatalog(String courseId, int semester, String session, int credits, String profId);

	public boolean modifyCourseCatalog(String courseId, int semester, String session, int credits, String profId);

	public Admin getAdminById(String userId);
}
