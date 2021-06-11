/**
 * 
 */
package com.flipkart.application;

import com.flipkart.bean.Professor;
import com.flipkart.service.CourseCatalogInterface;
import com.flipkart.service.CourseCatalogServiceImpl;
import com.flipkart.service.ProfessorInterface;
import com.flipkart.service.ProfessorServiceImpl;
import com.flipkart.service.RegisteredCourseInterface;
import com.flipkart.service.RegisteredCourseServiceImpl;

/**
 * @author yashsoni501
 *
 */
public class CRSProfessorMenu {

	ProfessorInterface professorInterface = ProfessorServiceImpl.getInstance();
	CourseCatalogInterface courseCatalogInterface = CourseCatalogServiceImpl.getInstance();
	RegisteredCourseInterface registerdCourseInterface = RegisteredCourseServiceImpl.getInstance();
	Professor professor = null;

	public void createMenu() {
		if (CRSApplication.userId != null) {
			professor = professorInterface.getProfessorById(CRSApplication.userId);
		}
		while (CRSApplication.userId != null) {
			System.out.println("Professor Menu");
			System.out.println("1. Opt in a course");
			System.out.println("2. View opted courses");
			System.out.println("3. Enrolled students in a course");
			System.out.println("4. Remove an opted course");
			System.out.println("5. Submit grade for a course");
			System.out.println("6. View Courses");
			System.out.println("7. Logout");
			int choice = 0;
			choice = CRSApplication.scan.nextInt();

			switch (choice) {
			case 1:
				optInCourse();
				break;
			case 2:
				viewOptedCourses();
				break;
			case 3:
				viewEnrolledStudentsInCourse();
				break;
			case 4:
				removeOptedCourse();
				break;
			case 5:
				submitGrades();
				break;
			case 6:
				viewCourses();
				break;
			case 7:
				CRSApplication.logout();
				break;
			default:
				System.out.println("Invalid option. Please try again...");
			}
		}
	}

	private void submitGrades() {
		// TODO Auto-generated method stub

		System.out.println("Submit Grade Menu");

		System.out.println("Enter Student Id");
		String studentId = CRSApplication.scan.next();

		System.out.println("Enter Course Id");
		String courseId = CRSApplication.scan.next();

		System.out.println("Enter Grade (A/B/C/D)");
		String grade = CRSApplication.scan.next();

		if (registerdCourseInterface.modifyGrade(studentId, courseId, grade)) {
			System.out.println("Succes");
		} else {
			System.out.println("Failure");
		}
	}

	private void viewEnrolledStudentsInCourse() {
		// TODO Auto-generated method stub
		System.out.println("Enter Course Id");
		String courseId = CRSApplication.scan.next();
		System.out.println("Enter Session");
		String session = CRSApplication.scan.next();
		System.out.println("Enter Semester");
		int semester = CRSApplication.scan.nextInt();
		registerdCourseInterface.getEnrolledStudents(courseId, semester, session);
	}

	private void viewOptedCourses() {
		// TODO Auto-generated method stub

		courseCatalogInterface.getCourseCatalogByProfessorId(CRSApplication.userId);

	}

	private void removeOptedCourse() {

		System.out.println("Enter the CourseId");

		String courseId = CRSApplication.scan.next();
		if (courseCatalogInterface.updateProfessorId(courseId, null)) {
			System.out.println("Succes");
		} else {
			System.out.println("Failure");
		}
	}

	private void viewCourses() {
		courseCatalogInterface.getDepartmentCourseCatalog(professor.getDepartment());
	}

	private void optInCourse() {

		// TODO Auto-generated method stub
		System.out.println("Course Opt Menu");
		System.out.println("Enter the CourseId");
		String courseId = CRSApplication.scan.next();
		if (courseCatalogInterface.updateProfessorId(courseId, CRSApplication.userId)) {
			System.out.println("Succes");
		} else {
			System.out.println("Failure");
		}

	}
}
