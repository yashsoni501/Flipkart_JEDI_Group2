/**
 * 
 */
package com.flipkart.service;

import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.CourseRegistration;

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

	public boolean generateReportCard(String session);

	public boolean removeProfessor(String profId);

	public boolean modifyProfessor(String profId, String professorName, String department);

	public boolean modifyStudnet(String studentId, String studentName, String department, String session);

	public boolean removeCourseCatalog(String courseId);

	public boolean modifyCourse(String courseId, String courseName, String department);

	public boolean addCourseCatalog(String courseId, int semester, String session, int credits);

	public boolean modifyCourseCatalog(String courseId, int semester, String session, int credits);

	public void enableCourseRegistration();

	public void disableCourseRegistration();

	public void enablePayment();

	public void disablePayment();
}
