/**
 * 
 */
package com.flipkart.service;

/**
 * @author Lenovo
 *
 */
public interface CourseRegistrationInterface {
	public boolean submitCourseRegistrationForm(int[] courselist);
	public void addCourse(int courseId);
	public void dropCourse(int courseId);

}
