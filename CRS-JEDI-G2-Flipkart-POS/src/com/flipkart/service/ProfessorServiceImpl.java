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
public class ProfessorServiceImpl implements ProfessorInterface {

	@Override
	public void getDepartmentCourses() {
		// TODO Auto-generated method stub

	}

	@Override
	public void optInCourse() {
		// TODO Auto-generated method stub
		return professorDAO.optInCourse(professorId, courseId);
	}

	@Override
	public void viewOptedCourses() {
		// TODO Auto-generated method stub
		return professorDAO.viewOptedCourses(professorId);
	}

	@Override
	public void submitGrades() {
		// TODO Auto-generated method stub

	}

	@Override
	public void viewEnrolledStudentsInCourse() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Professor getProfessorDetails(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getAllProfessor() {
		// TODO Auto-generated method stub
		
	}

}
