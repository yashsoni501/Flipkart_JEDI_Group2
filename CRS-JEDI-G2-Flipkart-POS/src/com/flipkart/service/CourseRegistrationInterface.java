/**
 * 
 */
package com.flipkart.service;

import java.util.ArrayList;
import com.flipkart.bean.CourseCatalog;

/**
 * @author Lenovo
 *
 */
public interface CourseRegistrationInterface {
	public boolean submitCourseRegistrationForm(ArrayList<CourseCatalog> courselist);
	public boolean addCourse(String courseId);
	public boolean dropCourse(String courseId);

}
