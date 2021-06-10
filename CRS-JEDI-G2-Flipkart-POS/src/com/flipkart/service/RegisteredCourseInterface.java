/**
 * 
 */
package com.flipkart.service;

/**
 * @author Lenovo
 *
 */
public interface RegisteredCourseInterface {
	public boolean  modifyGrade(int studentId, int courseId, String grade);
	public String viewGrade(int studentId, int courseId);
	public String[] getEnrolledStudents(int courseId);

}