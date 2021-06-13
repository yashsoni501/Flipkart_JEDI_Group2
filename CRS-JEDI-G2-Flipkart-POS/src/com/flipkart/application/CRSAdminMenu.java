/**
 * 
 */
package com.flipkart.application;

import java.sql.SQLException;
import java.util.ArrayList;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.CourseCatalog;
import com.flipkart.bean.Professor;
import com.flipkart.bean.RegisteredCourse;
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
import com.flipkart.service.SemesterReportCardInterface;
import com.flipkart.service.SemesterReportCardServiceImpl;
import com.flipkart.service.StudentInterface;
import com.flipkart.service.StudentServiceImpl;

/**
 * @author jagru
 *
 */
public class CRSAdminMenu {

	AdminInterface adminInterface = AdminServiceImpl.getInstance();
	ProfessorInterface professorInterface = ProfessorServiceImpl.getInstance();
	StudentInterface studentInterface = StudentServiceImpl.getInstance();
	CourseCatalogInterface courseCatalogInterface = CourseCatalogServiceImpl.getInstance();
	CourseInterface courseInterface = CourseServiceImpl.getInstance();
	SemesterReportCardInterface semesterReportCardInterface = SemesterReportCardServiceImpl.getInstance();
	RegisteredCourseInterface registeredCourseInterface = RegisteredCourseServiceImpl.getInstance();
	Admin admin = null;

	public void createMenu() {
		if (CRSApplication.userId != null) {
			admin = adminInterface.getAdminById(CRSApplication.userId);
		}
		while (CRSApplication.userId != null) {
			System.out.println("**********Admin Menu*********");
			System.out.println("1. View All Course Catalogue");
			System.out.println("2. View All Students");
			System.out.println("3. View All Courses");
			System.out.println("4. View All Professors");
			System.out.println("5. Add Course Catalogue");
			System.out.println("6. Add Student");
			System.out.println("7. Add Course");
			System.out.println("8. Add Professor");
			System.out.println("9. Enable Registration");
			System.out.println("10. Disable Registration");
			System.out.println("11. Enable Payment");
			System.out.println("12. Disable Payment");
			System.out.println("13. Generate Report Card");
			System.out.println("14. Logout");

			int choice = CRSApplication.scan.nextInt();

			switch (choice) {
			case 1:
				getAllCourseCatalog();
				break;
			case 2:
				getAllStudents();
				break;
			case 3:
				getAllCourses();
				break;
			case 4:
				getAllProfessors();
				break;
			case 5:
				addCourseCatalog();
				break;
			case 6:
				addStudent();
				break;
			case 7:
				addCourse();
				break;
			case 8:
				addProfessor();
				break;
			case 9:
				adminInterface.setCourseRegistrationFlag(true);
				return;
			case 10:
				adminInterface.setCourseRegistrationFlag(false);
				return;
			case 11:
				adminInterface.setPaymentFlag(true);
				return;
			case 12:
				adminInterface.setPaymentFlag(false);
				return;
			case 13:
				generateReportCard();
				return;
			case 14:
				admin = null;
				CRSApplication.logout();
				return;

			default:
				System.out.println("***** Wrong Choice *****");
			}
		}
	}

	private void generateReportCard() {

		System.out.println("Enter Session:");
		String session = CRSApplication.scan.next();
		System.out.println("Enter Semester:");
		int semester = CRSApplication.scan.nextInt();

		ArrayList<Student> registeredStudents = new ArrayList<Student>();
		try {
			registeredStudents = studentInterface.getAllStudents(session);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (Student student : registeredStudents) {
			ArrayList<RegisteredCourse> courses = registeredCourseInterface.getRegisteredCourses(student.getStudentID(),
					semester);
			float sgpa = calculateSgpa(courses);
			semesterReportCardInterface.addSemesterReportCard(student.getStudentID(), semester, sgpa);
		}
		System.out.println("Report Card generated Successfully");
	}

	private float calculateSgpa(ArrayList<RegisteredCourse> courses) {
		// TODO Auto-generated method stub
		return 0;
	}

	private void addCourse() {
		// Auto-generated method stub
		System.out.println("Enter Course Name:");
		String courseName = CRSApplication.scan.next();

		System.out.println("Enter Department:");
		String department = CRSApplication.scan.next();

		if (adminInterface.addCourse(courseName, department)) {
			System.out.println("Course Added Successfully");
		} else {
			System.out.println("Something went wrong");
		}
	}

	private void removeCourse() {
		// Auto-generated method stub

		System.out.println("Enter Course Id:");
		String courseId = CRSApplication.scan.next();

		if (adminInterface.removeCourse(courseId)) {
			System.out.println("Course Removed Successfully");
		} else {
			System.out.println("Something went wrong");
		}

	}

	private void modifyCourse() {
		// Auto-generated method stub

		System.out.println("Enter Course Id:");
		String courseId = CRSApplication.scan.next();

		System.out.println("Enter Course Name:");
		String courseName = CRSApplication.scan.next();

		System.out.println("Enter Department:");
		String department = CRSApplication.scan.next();

		if (adminInterface.modifyCourse(courseId, courseName, department)) {
			System.out.println("Course Updated Successfully");
		} else {
			System.out.println("Something went wrong");
		}

	}

	private void addCourseCatalog() {
		// Auto-generated method stub
		System.out.println("Enter Course Id:");
		String courseId = CRSApplication.scan.next();

		System.out.println("Enter Semester:");
		int semester = CRSApplication.scan.nextInt();

		System.out.println("Enter Session:");
		String session = CRSApplication.scan.next();

		System.out.println("Enter Credits:");
		float credits = CRSApplication.scan.nextFloat();

		if (adminInterface.addCourseCatalog(courseId, semester, session, credits, null)) {
			System.out.println("Course Added to Catalog");
		} else {
			System.out.println("Something went wrong");
		}
	}

	private void removeCourseCatalog() {
		// Auto-generated method stub

		System.out.println("Enter Course Id:");
		String courseId = CRSApplication.scan.next();

		if (adminInterface.removeCourseCatalog(courseId)) {
			System.out.println("Course Catalog Removed Successfully");
		} else {
			System.out.println("Something went wrong");
		}

	}

	private void modifyCourseCatalog() {
		// Auto-generated method stub

		System.out.println("Enter Course Id:");
		String courseId = CRSApplication.scan.next();

		System.out.println("Enter Semester:");
		int semester = CRSApplication.scan.nextInt();

		System.out.println("Enter Session:");
		String session = CRSApplication.scan.next();

		System.out.println("Enter Credits:");
		float credits = CRSApplication.scan.nextFloat();

		if (adminInterface.modifyCourseCatalog(courseId, semester, session, credits, null)) {
			System.out.println("Course Catalog Updated Successfully");
		} else {
			System.out.println("Something went wrong");
		}

	}

	private void addStudent() {
		// Auto-generated method stub
		System.out.println("Enter Student Name:");
		String studentName = CRSApplication.scan.next();

		System.out.println("Enter Department:");
		String department = CRSApplication.scan.next();

		System.out.println("Enter Session:");
		String session = CRSApplication.scan.next();

		System.out.println("Enter Student Email:");
		String userEmail = CRSApplication.scan.next();

		System.out.println("Enter Password:");
		String password = CRSApplication.scan.next();

		String studentId = CRSApplication.authInterface.addUserWithEmailPassword(userEmail, password, "STUDENT");

		if (adminInterface.addStudent(studentId, studentName, department, userEmail, session)) {
			System.out.println("Student Added Successfully");
		} else {
			System.out.println("Something went wrong");
		}
	}

	private void removeStudent() {
		// Auto-generated method stub

		System.out.println("Enter Student Id:");
		String studentId = CRSApplication.scan.next();

		if (CRSApplication.authInterface.removeUser(studentId) && adminInterface.removeStudent(studentId)) {
			System.out.println("Student Removed Successfully");
		} else {
			System.out.println("Something went wrong");
		}

	}

	private void modifyStudent() {
		// Auto-generated method stub

		System.out.println("Enter Student Id:");
		String studentId = CRSApplication.scan.next();

		System.out.println("Enter Student Name:");
		String studentName = CRSApplication.scan.next();

		System.out.println("Enter Department:");
		String department = CRSApplication.scan.next();

		System.out.println("Enter Session:");
		String session = CRSApplication.scan.next();

		if (adminInterface.modifyStudent(studentId, studentName, department, session)) {
			System.out.println("Student Updated Successfully");
		} else {
			System.out.println("Something went wrong");
		}

	}

	private void addProfessor() {
		// Auto-generated method stub

		System.out.println("Enter Professor Name:");
		String professorName = CRSApplication.scan.next();

		System.out.println("Enter Department:");
		String department = CRSApplication.scan.next();

		System.out.println("Enter Professor Email:");
		String userEmail = CRSApplication.scan.next();

		System.out.println("Enter Password:");
		String password = CRSApplication.scan.next();

		String profId = CRSApplication.authInterface.addUserWithEmailPassword(userEmail, password, "PROFESSOR");

		if (adminInterface.addProfessor(profId, professorName, department, userEmail)) {
			System.out.println("Professor Added Successfully");
		} else {
			System.out.println("Something went wrong");
		}

	}

	private void removeProfessor() {
		// Auto-generated method stub

		System.out.println("Enter Professor Id:");
		String profId = CRSApplication.scan.next();

		if (CRSApplication.authInterface.removeUser(profId) && adminInterface.removeProfessor(profId)) {
			System.out.println("Professor Removed Successfully");
		} else {
			System.out.println("Something went wrong");
		}

	}

	private void modifyProfessor() {
		// Auto-generated method stub

		System.out.println("Enter Professor Id:");
		String profId = CRSApplication.scan.next();

		System.out.println("Enter Professor Name:");
		String professorName = CRSApplication.scan.next();

		System.out.println("Enter Department:");
		String department = CRSApplication.scan.next();

		if (adminInterface.modifyProfessor(profId, professorName, department)) {
			System.out.println("Professor Updated Successfully");
		} else {
			System.out.println("Something went wrong");
		}

	}

	private void getAllCourseCatalog() {
		// Auto-generated method stub
		ArrayList<CourseCatalog> arr = new ArrayList<CourseCatalog>();
		arr = courseCatalogInterface.getAllCourseCatalog();
		for (int i = 0; i < arr.size(); i++) {
			Course course = courseInterface.getCourse(arr.get(i).getCourseId());
			System.out.println(course.getCourseID() + " " + course.getCourseName() + " " + course.getDepartment() + " "
					+ arr.get(i).getSemester() + " " + arr.get(i).getSession() + " " + arr.get(i).getCredits());

		}

		while (true) {

			System.out.println("1. Remove Course Catalog");
			System.out.println("2. Modify Course Catalog Details");
			System.out.println("3. Return");
			int choice = CRSApplication.scan.nextInt();
			switch (choice) {

			case 1:
				removeCourseCatalog();
				break;
			case 2:
				modifyCourseCatalog();
				break;
			case 3:
				return;
			default:
				System.out.println("***** Wrong Choice *****");
			}
		}
	}

	private void getAllCourses() {
		// Auto-generated method stub
		ArrayList<Course> arr = courseInterface.getAllCourses();
		for (int i = 0; i < arr.size(); i++) {
			System.out.println(arr.get(i).getCourseID() + " " + arr.get(i).getCourseName() + " "
					+ arr.get(i).getDepartment() + " ");

		}
		while (true) {
			System.out.println("1. Remove Course");
			System.out.println("2. Modify Course Details");
			System.out.println("3. Return");
			int choice = CRSApplication.scan.nextInt();
			switch (choice) {
			case 1:
				removeCourse();
				break;
			case 2:
				modifyCourse();
				break;
			case 3:
				return;
			default:
				System.out.println("***** Wrong Choice *****");
			}
		}
	}

	private void getAllStudents() {
		// Auto-generated method stub
		System.out.println("Enter Session:");
		String session = CRSApplication.scan.next();
		ArrayList<Student> arr = new ArrayList<Student>();
		try {
			arr = studentInterface.getAllStudents(session);
		} catch (SQLException e1) {
			// Auto-generated catch block
			e1.printStackTrace();
		}
		for (int i = 0; i < arr.size(); i++) {
			System.out.println(arr.get(i).getStudentID() + " " + arr.get(i).getStudentName() + " "
					+ arr.get(i).getDepartment() + " " + arr.get(i).getEmailID() + " " + arr.get(i).getSession());

		}
		while (true) {
			System.out.println("1. Remove Student");
			System.out.println("2. Modify Student Details");
			System.out.println("3. Return");
			int choice = CRSApplication.scan.nextInt();
			switch (choice) {
			case 1:
				removeStudent();
				break;
			case 2:
				modifyStudent();
				break;
			case 3:
				return;
			default:
				System.out.println("***** Wrong Choice *****");
			}
		}
	}

	private void getAllProfessors() {
		// Auto-generated method stub
		ArrayList<Professor> arr = new ArrayList<Professor>();

		arr = professorInterface.getAllProfessor();

		for (int i = 0; i < arr.size(); i++) {
			System.out.println(arr.get(i).getProfessorId() + " " + arr.get(i).getProfessorName() + " "
					+ arr.get(i).getDepartment() + " " + arr.get(i).getEmailID());

		}
		while (true) {
			System.out.println("1. Remove Professor");
			System.out.println("2. Modify Professor Details");
			System.out.println("3. Return");
			int choice = CRSApplication.scan.nextInt();
			switch (choice) {
			case 1:
				removeProfessor();
				break;
			case 2:
				modifyProfessor();
				break;
			case 3:
				return;
			default:
				System.out.println("***** Wrong Choice *****");
			}
		}
	}

}
