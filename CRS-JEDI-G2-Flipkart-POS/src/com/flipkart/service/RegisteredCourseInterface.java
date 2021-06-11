/**
 * 
 */
package com.flipkart.service;
import java.util.ArrayList;
import com.flipkart.bean.Student;

/**
 * @author Lenovo
 *
 */
public interface RegisteredCourseInterface {
	
	public boolean modifyGrade(String studentId, String courseId, String grade);
	public String viewGrade(String studentId, String courseId);
	public ArrayList<Student> getEnrolledStudents(String courseId, int semester, String session);
	public void getRegisteredCourses(String studentId, String session, int semester);
	public void addRegisteredCourse(String courseId, int semester, String grade, String session, String studentID);
}