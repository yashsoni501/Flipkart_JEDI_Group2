/**
 * 
 */
package com.flipkart.application;

import java.sql.SQLException;
import java.util.ArrayList;

import com.flipkart.bean.Course;
import com.flipkart.bean.CourseCatalog;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.service.AdminInterface;
import com.flipkart.service.AdminServiceImpl;
import com.flipkart.service.CourseCatalogInterface;
import com.flipkart.service.CourseCatalogServiceImpl;
import com.flipkart.service.CourseInterface;
import com.flipkart.service.CourseServiceImpl;
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
	CourseInterface courseInterface = CourseServiceImpl.getInstance();
	RegisteredCourseInterface registerdCourseInterface = RegisteredCourseServiceImpl.getInstance();
	AdminInterface adminInterface = AdminServiceImpl.getInstance();

	Professor professor = null;

	public void createMenu() {
		if (CRSApplication.userId != null) {
			try {
				professor = professorInterface.getProfessorDetails(CRSApplication.userId);
			} catch (SQLException e) {
				// Auto-generated catch block
				e.printStackTrace();
			}
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

	private void optInCourse() {
		boolean profWindow = adminInterface.getProfessorFlag();

		if (!profWindow) {
			System.out.println("Course Registration window for professors is closed.");
			return;
		}

		// Auto-generated method stub
		System.out.println("Course Opt Menu");
		System.out.println("Enter the CourseId");
		String courseId = CRSApplication.scan.next();
		try {
			if (professorInterface.optInCourse(CRSApplication.userId, courseId)) {
				System.out.println("Succes");
			} else {
				System.out.println("Failure");
			}
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void viewOptedCourses() {
		// Auto-generated method stub
		ArrayList<CourseCatalog> arr = new ArrayList<CourseCatalog>();
		try {
			arr = professorInterface.viewOptedCourses(CRSApplication.userId);
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < arr.size(); i++) {
			Course course = courseInterface.getCourse(arr.get(i).getCourseId());
			System.out.println(course.getCourseID() + " " + course.getCourseName() + " " + course.getDepartment() + " "
					+ arr.get(i).getSemester() + " " + arr.get(i).getSession() + " " + arr.get(i).getCredits());

		}
	}

	private void viewEnrolledStudentsInCourse() {
		// Auto-generated method stub
		System.out.println("Enter Course Id");
		String courseId = CRSApplication.scan.next();
		System.out.println("Enter Session");
		String session = CRSApplication.scan.next();
		ArrayList<Student> arr = new ArrayList<Student>();
		try {
			arr = professorInterface.viewEnrolledStudents(courseId, session);
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < arr.size(); i++) {
			System.out.println(arr.get(i).getStudentID() + " " + arr.get(i).getStudentName() + " "
					+ arr.get(i).getDepartment() + " " + arr.get(i).getEmailID() + " " + arr.get(i).getSession());

		}
	}

	// Note :Needs to be implemented in course catalog
	private void removeOptedCourse() {
		boolean profWindow = adminInterface.getProfessorFlag();

		if (!profWindow) {
			System.out.println("Course Registration window for professors is closed.");
			return;
		}

		System.out.println("Enter the CourseId");

		String courseId = CRSApplication.scan.next();
		if (courseCatalogInterface.updateProfessorId(courseId, null)) {
			System.out.println("Success");
		} else {
			System.out.println("Failure");
		}
	}

	private void submitGrades() {
		// Auto-generated method stub

		System.out.println("Submit Grade Menu");

		System.out.println("Enter Student Id");
		String studentId = CRSApplication.scan.next();

		System.out.println("Enter Course Id");
		String courseId = CRSApplication.scan.next();

		System.out.println("Enter Grade (A/B/C/D)");
		String grade = CRSApplication.scan.next();

		try {
			if (professorInterface.submitGrade(studentId, courseId, grade)) {
				System.out.println("Succes");
			} else {
				System.out.println("Failure");
			}
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void viewCourses() {
		ArrayList<CourseCatalog> arr = new ArrayList<CourseCatalog>();
		arr = courseCatalogInterface.getDepartmentCourseCatalog(professor.getDepartment());
		for (int i = 0; i < arr.size(); i++) {
			Course course = courseInterface.getCourse(arr.get(i).getCourseId());
			System.out.println(course.getCourseID() + " " + course.getCourseName() + " " + course.getDepartment() + " "
					+ arr.get(i).getSemester() + " " + arr.get(i).getSession() + " " + arr.get(i).getCredits());

		}

	}

}
