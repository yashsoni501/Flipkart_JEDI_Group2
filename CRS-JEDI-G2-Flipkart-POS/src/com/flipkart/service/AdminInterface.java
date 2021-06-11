/**
 * 
 */
package com.flipkart.service;

import java.util.List;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;

/**
 * @author aysh
 *
 */
public interface AdminInterface {

	public boolean addCourse(String courseName, String Department);

	public boolean removeCourse(String courseId);

	public boolean addProfessor(String professorId, String name, String department, String emailId);

	public boolean addStudent(String studentId, String name, String department, String emailId, String session);

	public boolean enableOrDisableCourseRegistration(boolean flag);

	public boolean enableOrDisablePayment(boolean flag);

	public boolean generateReportCard(String session, int semester);

	public boolean removeProfessor(String profId);

	public boolean modifyProfessor(String profId, String professorName, String department);

	public boolean modifyStudent(String studentId, String studentName, String department, String session);

	public boolean removeCourseCatalog(String courseId);

	public boolean modifyCourse(String courseId, String courseName, String department);

	public boolean addCourseCatalog(String courseId, int semester, String session, int credits, String profId);

	public boolean modifyCourseCatalog(String courseId, int semester, String session, int credits, String profID);

	public void enableCourseRegistration();

	public void disableCourseRegistration();

	public void enablePayment();

	public void disablePayment();

	public Admin getAdminById(String userId);
}
