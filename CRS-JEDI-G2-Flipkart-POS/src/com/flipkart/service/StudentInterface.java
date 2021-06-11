/**
 * 
 */
package com.flipkart.service;

import java.util.ArrayList;

import com.flipkart.bean.Student;
import com.flipkart.bean.Course;

/**
 * @author aysh
 *
 */
public interface StudentInterface {

	public ArrayList<Course> fetchRegisteredCourses(String studentId, int semester);

	public boolean payFee(String studentId, int modeOfPayment);

	public boolean isFeePaid(String studentId, int semester);

	public ArrayList<Student> getAllStudents(String session);

	public Student getStudentById(String userId);
}