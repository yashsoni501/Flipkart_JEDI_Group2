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
import com.flipkart.bean.SemesterReportCard;
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

// TODO: Auto-generated Javadoc
/**
 * The Class CRSAdminMenu.
 *
 * @author jagru
 */
public class CRSAdminMenu {

	/** The admin interface. */
	AdminInterface adminInterface = AdminServiceImpl.getInstance();
	
	/** The professor interface. */
	ProfessorInterface professorInterface = ProfessorServiceImpl.getInstance();
	
	/** The student interface. */
	StudentInterface studentInterface = StudentServiceImpl.getInstance();
	
	/** The course catalog interface. */
	CourseCatalogInterface courseCatalogInterface = CourseCatalogServiceImpl.getInstance();
	
	/** The course interface. */
	CourseInterface courseInterface = CourseServiceImpl.getInstance();
	
	/** The semester report card interface. */
	SemesterReportCardInterface semesterReportCardInterface = SemesterReportCardServiceImpl.getInstance();
	
	/** The registered course interface. */
	RegisteredCourseInterface registeredCourseInterface = RegisteredCourseServiceImpl.getInstance();
	
	/** The admin. */
	Admin admin = null;

	/**
	 * Creates the menu.
	 */
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

			System.out.println("13. Enable Course Registration for Professor");
			System.out.println("14. Disable Course Registration for Professor");

			System.out.println("15. Generate Report Card");
			System.out.println("16. Logout");

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
				break;
			case 10:
				adminInterface.setCourseRegistrationFlag(false);
				break;
			case 11:
				adminInterface.setPaymentFlag(true);
				break;
			case 12:
				adminInterface.setPaymentFlag(false);
				break;
			case 13:
				adminInterface.setProfessorFlag(true);
				break;
			case 14:
				adminInterface.setProfessorFlag(false);
				break;
			case 15:
				generateReportCard();
				break;
			case 16:
				admin = null;
				CRSApplication.logout();
				break;

			default:
				System.out.println("***** Wrong Choice *****");
			}
		}
	}

	/**
	 * Generate report card.
	 */
	private void generateReportCard() {

		System.out.println("Enter Session:");
		String session = CRSApplication.scan.next();
		System.out.println("Enter Semester:");
		int semester = CRSApplication.scan.nextInt();

		ArrayList<Student> registeredStudents = new ArrayList<Student>();
		try {
			registeredStudents = studentInterface.getAllStudents(session);
			boolean alreadyCreated = false;
			boolean checked = false;
			for (Student student : registeredStudents) {

				if (!checked) {
					for (SemesterReportCard semRepotCard : semesterReportCardInterface
							.getSemesterReportCardByStudentId(student.getStudentID())) {
						if (semRepotCard.getCurrentSem() == semester) {
							alreadyCreated = true;
						}
					}
					checked = true;
				}

				if (alreadyCreated) {
					System.out.println("The Report cards are already generated");
					return;
				}

				ArrayList<RegisteredCourse> courses = registeredCourseInterface
						.getRegisteredCourses(student.getStudentID(), semester);
				float sgpa = calculateSgpa(courses);
				semesterReportCardInterface.addSemesterReportCard(student.getStudentID(), semester, sgpa);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("Report Card generated Successfully");
	}

	/**
	 * Weightage.
	 *
	 * @param str the str
	 * @return the int
	 */
	private int weightage(String str) {
		if (str.equals("A"))
			return 10;
		if (str.equals("B"))
			return 9;
		if (str.equals("C"))
			return 8;
		if (str.equals("D"))
			return 7;
		if (str.equals("E"))
			return 6;
		if (str.equals("F"))
			return 5;
		if (str.equals("G"))
			return 4;
		if (str.equals("H"))
			return 3;
		if (str.equals("I"))
			return 2;
		if (str.equals("J"))
			return 1;
		return 0;
	}

	/**
	 * Calculate sgpa.
	 *
	 * @param courses the courses
	 * @return the float
	 */
	private float calculateSgpa(ArrayList<RegisteredCourse> courses) {
		// Auto-generated method stub
		float totalCredit = 0, totalScore = 0;
		for (RegisteredCourse temp : courses) {
			CourseCatalog currcourse = courseCatalogInterface.getCourseCatalog(temp.getCourseId());
			totalCredit += currcourse.getCredits();
			totalScore += currcourse.getCredits() * weightage(temp.getGrade());
		}

		return totalScore / totalCredit;
	}

	/**
	 * Adds the course.
	 */
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

	/**
	 * Removes the course.
	 */
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

	/**
	 * Modify course.
	 */
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

	/**
	 * Adds the course catalog.
	 */
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

	/**
	 * Removes the course catalog.
	 */
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

	/**
	 * Modify course catalog.
	 */
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

	/**
	 * Adds the student.
	 */
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

		if (adminInterface.addStudent(studentName, userEmail, password, department, session)) {
			System.out.println("Student Added Successfully");
		} else {
			System.out.println("Something went wrong");
		}
	}

	/**
	 * Removes the student.
	 */
	private void removeStudent() {
		// Auto-generated method stub

		System.out.println("Enter Student Id:");
		String studentId = CRSApplication.scan.next();

		if (adminInterface.removeStudent(studentId)) {
			System.out.println("Student Removed Successfully");
		} else {
			System.out.println("Something went wrong");
		}

	}

	/**
	 * Modify student.
	 */
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

	/**
	 * Adds the professor.
	 */
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

		if (adminInterface.addProfessor(professorName, userEmail, password, department)) {
			System.out.println("Professor Added Successfully");
		} else {
			System.out.println("Something went wrong");
		}

	}

	/**
	 * Removes the professor.
	 */
	private void removeProfessor() {
		// Auto-generated method stub

		System.out.println("Enter Professor Id:");
		String profId = CRSApplication.scan.next();

		if (adminInterface.removeProfessor(profId)) {
			System.out.println("Professor Removed Successfully");
		} else {
			System.out.println("Something went wrong");
		}

	}

	/**
	 * Modify professor.
	 */
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

	/**
	 * Gets the all course catalog.
	 *
	 * @return the all course catalog
	 */
	private void getAllCourseCatalog() {
		// Auto-generated method stub
		ArrayList<CourseCatalog> arr = new ArrayList<CourseCatalog>();
		arr = courseCatalogInterface.getAllCourseCatalog();
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println("Course ID \t Course Name \t Department \t Semester \t Session \t Credits");
		System.out.println("----------------------------------------------------------------------------------------");
		for (int i = 0; i < arr.size(); i++) {
			Course course = courseInterface.getCourse(arr.get(i).getCourseId());
			System.out.println(course.getCourseID() + "\t" + course.getCourseName() + "\t" + course.getDepartment() + "\t"
					+ arr.get(i).getSemester() + "\t" + arr.get(i).getSession() + "\t" + arr.get(i).getCredits());
		}
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println();
		
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

	/**
	 * Gets the all courses.
	 *
	 * @return the all courses
	 */
	private void getAllCourses() {
		// Auto-generated method stub
		ArrayList<Course> arr = courseInterface.getAllCourses();
		System.out.println("---------------------------------------------------");
		System.out.println("Course ID \t Course Name \t Department");
		System.out.println("---------------------------------------------------");
		for (int i = 0; i < arr.size(); i++) {
			System.out.println(arr.get(i).getCourseID() + "\t" + arr.get(i).getCourseName() + "\t"
					+ arr.get(i).getDepartment());
		}
		System.out.println("---------------------------------------------------");
		System.out.println();
		
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

	/**
	 * Gets the all students.
	 *
	 * @return the all students
	 */
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
		System.out.println("---------------------------------------------------------------");
		System.out.println("Student ID \t Name \t Department \t Email ID \t Session");
		System.out.println("---------------------------------------------------------------");
		for (int i = 0; i < arr.size(); i++) {
			System.out.println(arr.get(i).getStudentID() + "\t\t" + arr.get(i).getStudentName() + "\t"
					+ arr.get(i).getDepartment() + "\t" + arr.get(i).getEmailID() + "\t" + arr.get(i).getSession());
		}
		System.out.println("---------------------------------------------------------------");
		System.out.println();
		
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

	/**
	 * Gets the all professors.
	 *
	 * @return the all professors
	 */
	private void getAllProfessors() {
		// Auto-generated method stub
		ArrayList<Professor> arr = new ArrayList<Professor>();

		try {
			arr = professorInterface.getAllProfessor();
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("---------------------------------------------------");
		System.out.println("Professor ID \t Name \t Department \t Email ID");
		System.out.println("---------------------------------------------------");
		for (int i = 0; i < arr.size(); i++) {
			System.out.println(arr.get(i).getProfessorId() + "\t\t" + arr.get(i).getProfessorName() + "\t"
					+ arr.get(i).getDepartment() + "\t" + arr.get(i).getEmailID());
		}
		System.out.println("---------------------------------------------------");
		System.out.println();
		
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
