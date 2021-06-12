/**
 * 
 */
package com.flipkart.DAO;

import java.util.ArrayList;

import com.flipkart.bean.Student;
import com.flipkart.bean.Course;

/**
 * @author Aeron
 *
 */
public class StudentDAOInterface {
	
	public ArrayList<Course> fetchRegisteredCourses(String studentId, int semester);

	public boolean payFee(String studentId, int modeOfPayment);

	public boolean isFeePaid(String studentId, int semester);

	public ArrayList<Student> getAllStudents(String session);

	public Student getStudentById(String userId);
}
