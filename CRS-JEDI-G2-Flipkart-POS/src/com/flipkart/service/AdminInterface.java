/**
 * 
 */
package com.flipkart.service;

import com.flipkart.bean.Admin;

/**
 * @author aysh
 *
 */
public interface AdminInterface {

	public boolean addCourse(String courseName, String department);

	public boolean removeCourse(String courseId);

	public boolean addProfessor(String professorId, String name, String department, String emailId);

	public boolean addStudent(String studentId, String name, String department, String emailId, String session);

	public boolean setCourseRegistrationFlag(boolean flag);

	public boolean setPaymentFlag(boolean flag);

	public boolean removeProfessor(String profId);

	public boolean modifyProfessor(String profId, String professorName, String department);

	public boolean modifyStudent(String studentId, String studentName, String department, String session);

	public boolean removeCourseCatalog(String courseId);

	public boolean modifyCourse(String courseId, String courseName, String department);

	public boolean addCourseCatalog(String courseId, int semester, String session, float credits, String profId);

	public boolean modifyCourseCatalog(String courseId, int semester, String session, float credits, String profId);

	public Admin getAdminById(String userId);

	public boolean removeStudent(String studentId);
}
