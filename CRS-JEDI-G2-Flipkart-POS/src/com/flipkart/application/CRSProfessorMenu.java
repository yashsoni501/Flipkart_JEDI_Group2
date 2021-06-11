/**
 * 
 */
package com.flipkart.application;

import com.flipkart.service.ProfessorInterface;
import com.flipkart.service.ProfessorServiceImpl;
import com.flipkart.service.RegisteredCourseInterface;
import com.flipkart.service.RegisteredCourseServiceImpl;

/**
 * @author yashsoni501
 *
 */
public class CRSProfessorMenu {

	ProfessorInterface professor = ProfessorServiceImpl.getInstance();

	public void createMenu(String professorId)
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
					optInCourse(professorId);
					break;
				case 3:
					viewOptedCourses(professorId);
					break;
				case 4:
					viewEnrolledStudentsInCourse();
					break;
				case 5:
					submitGrades(professorId);
					break;
				case 6:
					CRSApplication.logout();
					break;
				default:
					System.out.println("Invalid option. Please try again...");
			}
		}
	}

	private void logout() {
		// TODO Auto-generated method stub
		
	}

	private void submitGrades(String professorId) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Submit Grade Menu");
		
		System.out.println("Enter Student Id");
		String studentId = scanner.next();
		
		System.out.println("Enter Course Id");
		String courseId = scanner.next();
		
		System.out.println("Enter Grade (A/B/C/D)");
		String grade = scanner.next();
			
		// Call the Add grade Method
	}

	private void viewEnrolledStudentsInCourse() {
		// TODO Auto-generated method stub
		
	}

	private void viewOptedCourses(String professorId) {
		// TODO Auto-generated method stub
		
	}

	private void optInCourse(String professorId) {
		Scanner scanner = new Scanner(System.in);
		
		// TODO Auto-generated method stub
		System.out.println("Course Opt Menu");
		System.out.println("Enter the CourseId");
		
		String courseId = scanner.next();
		
		// Call the optInCourse from prof Interface
	}

	private void viewCourses() {
		// TODO Auto-generated method stub
		
		
	}
}
