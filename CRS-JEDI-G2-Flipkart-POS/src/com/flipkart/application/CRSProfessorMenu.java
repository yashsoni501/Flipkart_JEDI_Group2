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
	Professor professor = null;

	public void createMenu() throws SQLException {
		if (CRSApplication.userId != null) {
			try {
				professor = professorInterface.getProfessorDetails(CRSApplication.userId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
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

	private void optInCourse() throws SQLException {

		// TODO Auto-generated method stub
		System.out.println("Course Opt Menu");
		System.out.println("Enter the CourseId");
		String courseId = CRSApplication.scan.next();
		if (professorInterface.optInCourse(CRSApplication.userId, courseId)) {
			System.out.println("Succes");
		} else {
			System.out.println("Failure");
		}

	}

	private void viewOptedCourses() throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<CourseCatalog> arr = new ArrayList<CourseCatalog>();
		arr = professorInterface.viewOptedCourses(CRSApplication.userId);
		for (int i = 0; i < arr.size(); i++) {
			Course course = courseInterface.getCourse(arr.get(i).getCourseId());
			System.out.print(course.getCourseID() + " " + course.getCourseName() + " " + course.getDepartment() + " "
					+ arr.get(i).getSemester() + " " + arr.get(i).getSession() + " " + arr.get(i).getCredits());

		}
	}

	private void viewEnrolledStudentsInCourse() throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("Enter Course Id");
		String courseId = CRSApplication.scan.next();
		System.out.println("Enter Session");
		String session = CRSApplication.scan.next();
		System.out.println("Enter Semester");
		int semester = CRSApplication.scan.nextInt();
		ArrayList<Student> arr = new ArrayList<Student>();
		arr = professorInterface.viewEnrolledStudents(courseId);
		System.out.println(arr);

	}

	// Note :Needs to be implemented in course catalog
	private void removeOptedCourse() {

		System.out.println("Enter the CourseId");

		String courseId = CRSApplication.scan.next();
		if (courseCatalogInterface.updateProfessorId(courseId, null)) {
			System.out.println("Success");
		} else {
			System.out.println("Failure");
		}
	}

	private void submitGrades() throws SQLException {
		// TODO Auto-generated method stub

		System.out.println("Submit Grade Menu");

		System.out.println("Enter Student Id");
		String studentId = CRSApplication.scan.next();

		System.out.println("Enter Course Id");
		String courseId = CRSApplication.scan.next();

		System.out.println("Enter Grade (A/B/C/D)");
		String grade = CRSApplication.scan.next();

		if (professorInterface.submitGrade(studentId, courseId, grade)) {
			System.out.println("Succes");
		} else {
			System.out.println("Failure");
		}
	}

	private void viewCourses() throws SQLException {
		ArrayList<CourseCatalog> arr = new ArrayList<CourseCatalog>();
		arr = courseCatalogInterface.getDepartmentCourseCatalog(professor.getDepartment());
		System.out.println(arr);

	}

}
