/**
 * 
 */
package com.flipkart.application;

import com.flipkart.service.ProfessorInterface;
import com.flipkart.service.ProfessorServiceImpl;

/**
 * @author yashsoni501
 *
 */
public class CRSProfessorMenu {

	ProfessorInterface professor = ProfessorServiceImpl.getInstance();

	public void createMenu()
	{
		while(CRSApplication.userId != null)
		{
			System.out.println("Professor Menu");
			System.out.println("1. View Courses");
			System.out.println("2. Opt in a course");
			System.out.println("3. View opted courses");
			System.out.println("4. View students in the course");
			System.out.println("5. Upload student grades");
			System.out.println("6. Logout");
			int choice=0;
			choice = CRSApplication.scan.nextInt();

			switch(choice)
			{
				case 1:
					viewCourses();
					break;
				case 2:
					optInCourse();
					break;
				case 3:
					viewOptedCourses();
					break;
				case 4:
					viewEnrolledStudentsInCourse();
					break;
				case 5:
					submitGrades();
					break;
				case 6:
					CRSApplication.logout();
					break;
				default:
					System.out.println("Invalid option. Please try again...");
			}
		}
	}

	private void submitGrades() {
		// TODO Auto-generated method stub
		
	}

	private void viewEnrolledStudentsInCourse() {
		// TODO Auto-generated method stub
		
	}

	private void viewOptedCourses() {
		// TODO Auto-generated method stub
		
	}

	private void optInCourse() {
		// TODO Auto-generated method stub
		
	}

	private void viewCourses() {
		// TODO Auto-generated method stub
		
	}
}
