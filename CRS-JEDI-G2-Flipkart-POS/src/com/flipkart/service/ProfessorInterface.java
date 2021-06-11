/**
 * 
 */
package com.flipkart.service;

import java.util.ArrayList;

import com.flipkart.bean.CourseCatalog;
import com.flipkart.bean.Professor;

/**
 * @author aysh
 *
 */
public interface ProfessorInterface {

	public boolean optInCourse(String professorId, String courseId);

	public ArrayList<CourseCatalog> viewOptedCourses(String professorId);

	public void getAllProfessor();

	public Professor getProfessorById(String userId);

}
