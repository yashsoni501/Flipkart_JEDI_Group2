/**
 * 
 */
package com.flipkart.service;

import java.util.ArrayList;

import com.flipkart.bean.CourseCatalog;

/**
 * @author aysh
 *
 */
public interface ProfessorInterface {

	public boolean optInCourse(String professorId, String courseId);
	public ArrayList<CourseCatalog> viewOptedCourses(String professorId);
}
