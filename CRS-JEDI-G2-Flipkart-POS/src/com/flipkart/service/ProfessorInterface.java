/**
 * 
 */
package com.flipkart.service;

import java.util.List;

import com.flipkart.bean.Course;

/**
 * @author aysh
 *
 */
public interface ProfessorInterface {

	public List<Course> getDepartmentCourses(int professorId);
	public void optInCourse(int professorId, int courseId);
	public void viewOptedCourses(int professorId);
	public void submitGrades(int studentId, String courseId, String grade);
	public void viewEnrolledStudentsInCourse(int professorId);
}
