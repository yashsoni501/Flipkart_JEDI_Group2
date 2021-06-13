/**
 * 
 */
package com.flipkart.service;

import java.util.ArrayList;

import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;

/**
 * @author Lenovo
 *
 */
public interface RegisteredCourseInterface {

	public ArrayList<Student> getEnrolledStudents(String courseId, int semester, String session);

	public ArrayList<RegisteredCourse> getRegisteredCourses(String studentId, int semester);

	public boolean addRegisteredCourse(String courseId, int semester, String grade, String session, String studentID);
}