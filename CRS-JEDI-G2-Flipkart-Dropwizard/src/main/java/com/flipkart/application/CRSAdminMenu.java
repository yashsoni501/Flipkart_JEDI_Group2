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
import com.flipkart.exception.CourseCatalogEntryNotFoundException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.EmptyCourseCatalogListExcpetion;
import com.flipkart.exception.EmptyCourseListExcpetion;
import com.flipkart.exception.InvalidCredentialsException;
import com.flipkart.exception.NoProfessorsFoundException;
import com.flipkart.exception.NoRegisteredCoursesException;
import com.flipkart.exception.UserEmailAlreadyInUseException;
import com.flipkart.exception.UserEmailNotFoundException;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.exception.SemesterReportCardNotFound;
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
import com.flipkart.utils.MenuOptionScanner;

//  Auto-generated Javadoc
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
			try {
				admin = adminInterface.getAdminById(CRSApplication.userId);
			} catch (UserNotFoundException e) {
				System.out.println(e.getMessage());
				return;
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				return;
			}
		}
		while (CRSApplication.userId != null) {
			System.out.println("**********Admin Menu*********");
			System.out.println("1. View All Course Catalogue");
			System.out.println("2. View All Students");
			System.out.println("3. View All Courses");
			System.out.println("4. View All Professors");

			System.out.println("5. Add Course Catalogue");
			System.out.println("6. Add Course");
			System.out.println("7. Add Professor");

			System.out.println("8. Enable Registration");
			System.out.println("9. Disable Registration");

			System.out.println("10. Enable Payment");
			System.out.println("11. Disable Payment");

			System.out.println("12. Enable Course Registration for Professor");
			System.out.println("13. Disable Course Registration for Professor");

			System.out.println("14. Generate Report Card");
			System.out.println("15. Logout");

			int choice = MenuOptionScanner.nextInt();

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
				addCourse();
				break;
			case 7:
				addProfessor();
				break;
			case 8:
				try {
					adminInterface.setCourseRegistrationFlag(true);
					System.out.println("Course Registration Enabled");
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 9:
				try {
					adminInterface.setCourseRegistrationFlag(false);
					System.out.println("Course Registration Disabled");
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 10:
				try {
					adminInterface.setPaymentFlag(true);
					System.out.println("Fee Payment Enabled");
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 11:
				try {
					adminInterface.setPaymentFlag(false);
					System.out.println("Fee Payment Disabled");
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 12:
				try {
					adminInterface.setProfessorFlag(true);
					System.out.println("Professor Course Selection Window Enabled");
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 13:
				try {
					adminInterface.setProfessorFlag(false);
					System.out.println("Professor Course Selection Window Disabled");
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 14:
				generateReportCard();
				break;
			case 15:
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
		int semester = MenuOptionScanner.nextInt();

		ArrayList<Student> registeredStudents = new ArrayList<Student>();
		try {
			registeredStudents = studentInterface.getAllStudents(session);
			if (registeredStudents.size() == 0) {
				System.out.println("No student found in the database");
				return;
			}
			boolean alreadyCreated = false;
			boolean checked = false;
			for (Student student : registeredStudents) {

				if (!checked) {

					ArrayList<SemesterReportCard> reportCards = semesterReportCardInterface
							.getSemesterReportCardByStudentId(student.getStudentID());
					for (SemesterReportCard semReportCard : reportCards) {
						if (semReportCard.getCurrentSem() == semester) {
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
			System.out.println(e.getMessage());
			return;
		} catch (NoRegisteredCoursesException e) {
			System.out.println(e.getMessage());
			return;
		} catch (SemesterReportCardNotFound e) {
			System.out.println(e.getMessage());
			return;
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
		try {
			for (RegisteredCourse temp : courses) {
				CourseCatalog currcourse;
				currcourse = courseCatalogInterface.getCourseCatalog(temp.getCourseId());
				totalCredit += currcourse.getCredits();
				totalScore += currcourse.getCredits() * weightage(temp.getGrade());
			}

			return totalScore / totalCredit;
		} catch (CourseCatalogEntryNotFoundException e) {
			System.out.println(e.getMessage());
			return 0;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return 0;
		}
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

		try {
			if (adminInterface.addCourse(courseName, department)) {
				System.out.println("Course Added Successfully");
			} else {
				System.out.println("Something went wrong");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Removes the course.
	 */
	private void removeCourse() {
		// Auto-generated method stub

		System.out.println("Enter Course Id:");
		String courseId = CRSApplication.scan.next();

		try {
			if (adminInterface.removeCourse(courseId)) {
				System.out.println("Course Removed Successfully");
			} else {
				System.out.println("Something went wrong");
			}
		} catch (CourseNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
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

		try {
			if (adminInterface.modifyCourse(courseId, courseName, department)) {
				System.out.println("Course Updated Successfully");
			} else {
				System.out.println("Something went wrong");
			}
		} catch (CourseNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
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
		int semester = MenuOptionScanner.nextInt();

		System.out.println("Enter Session:");
		String session = CRSApplication.scan.next();

		System.out.println("Enter Credits:");
		float credits = CRSApplication.scan.nextFloat();

		try {
			if (adminInterface.addCourseCatalog(courseId, semester, session, credits, null)) {
				System.out.println("Course Added to Catalog");
			} else {
				System.out.println("Something went wrong");
			}
		} catch (CourseNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Removes the course catalog.
	 */
	private void removeCourseCatalog() {
		// Auto-generated method stub

		System.out.println("Enter Course Id:");
		String courseId = CRSApplication.scan.next();

		try {
			if (adminInterface.removeCourseCatalog(courseId)) {
				System.out.println("Course Catalog Removed Successfully");
			} else {
				System.out.println("Something went wrong");
			}
		} catch (CourseNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
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
		int semester = MenuOptionScanner.nextInt();

		System.out.println("Enter Session:");
		String session = CRSApplication.scan.next();

		System.out.println("Enter Credits:");
		float credits = CRSApplication.scan.nextFloat();

		try {
			if (adminInterface.modifyCourseCatalog(courseId, semester, session, credits, null)) {
				System.out.println("Course Catalog Updated Successfully");
			} else {
				System.out.println("Something went wrong");
			}
		} catch (CourseNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * Adds the student.
	 */
	private void approveStudent() {
		// Auto-generated method stub
		System.out.println("Enter Student Id:");
		String studentId = CRSApplication.scan.next();

		try {
			if (adminInterface.editStudentPermission(studentId, true)) {
				System.out.println("Student Approved Successfully");
			} else {
				System.out.println("Something went wrong");
			}
		} catch (UserNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private void disApproveStudent() {
		// Auto-generated method stub

		System.out.println("Enter Student Id:");
		String studentId = CRSApplication.scan.next();

		try {
			if (adminInterface.editStudentPermission(studentId, false)) {
				System.out.println("Student Permission Revoked Successfully");
			} else {
				System.out.println("Something went wrong");
			}
		} catch (UserNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
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

		try {
			if (adminInterface.addProfessor(professorName, userEmail, password, department)) {
				System.out.println("Professor Added Successfully");
			} else {
				System.out.println("Something went wrong");
			}
		} catch (UserEmailAlreadyInUseException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (InvalidCredentialsException e) {
			System.out.println(e.getMessage());
		} catch (UserEmailNotFoundException e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * Removes the professor.
	 */
	private void removeProfessor() {
		// Auto-generated method stub

		System.out.println("Enter Professor Id:");
		String profId = CRSApplication.scan.next();

		try {
			if (adminInterface.removeProfessor(profId)) {
				System.out.println("Professor Removed Successfully");
			} else {
				System.out.println("Something went wrong");
			}
		} catch (UserNotFoundException e) {
			System.out.println(e.getMessage());
			return;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return;
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

		try {
			if (adminInterface.modifyProfessor(profId, professorName, department)) {
				System.out.println("Professor Updated Successfully");
			} else {
				System.out.println("Something went wrong");
			}
		} catch (UserNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * Gets the all course catalog.
	 *
	 * @return the all course catalog
	 */
	private void getAllCourseCatalog() {
		// Auto-generated method stub
		try {

			ArrayList<CourseCatalog> arr = new ArrayList<CourseCatalog>();
			arr = courseCatalogInterface.getAllCourseCatalog();
			System.out.println(
					"---------------------------------------------------------------------------------------------------------------");
			System.out.printf("%10s %15s %15s %10s %10s %10s\n", "Course ID", "Course Name", "Department", "Semester",
					"Session", "Credits");
			System.out.println(
					"---------------------------------------------------------------------------------------------------------------");
			for (CourseCatalog catalog : arr) {
				Course course = courseInterface.getCourse(catalog.getCourseId());
				System.out.printf("%10s %15s %15s", course.getCourseID(), course.getCourseName(),
						course.getDepartment());
				System.out.printf("%10s %10s %10s\n", catalog.getSemester(), catalog.getSession(),
						catalog.getCredits());
			}
			System.out.println(
					"---------------------------------------------------------------------------------------------------------------");
			System.out.println();

			while (true) {

				System.out.println("1. Remove Course Catalog");
				System.out.println("2. Modify Course Catalog Details");
				System.out.println("3. Return");
				int choice = MenuOptionScanner.nextInt();
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
		} catch (CourseNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (EmptyCourseCatalogListExcpetion e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * Gets the all courses.
	 *
	 * @return the all courses
	 */
	private void getAllCourses() {
		// Auto-generated method stub
		try {
			ArrayList<Course> arr;
			arr = courseInterface.getAllCourses();
			System.out.println("-------------------------------------------");
			System.out.printf("%10s %15s %15s\n", "Course ID", "Course Name", "Department");
			System.out.println("-------------------------------------------");
			for (Course course : arr) {
				System.out.printf("%10s %15s %15s\n", course.getCourseID(), course.getCourseName(),
						course.getDepartment());
			}
			System.out.println("-------------------------------------------");
			System.out.println();

			while (true) {
				System.out.println("1. Remove Course");
				System.out.println("2. Modify Course Details");
				System.out.println("3. Return");
				int choice = MenuOptionScanner.nextInt();
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
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (EmptyCourseListExcpetion e) {
			System.out.println(e.getMessage());
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
		} catch (SQLException e) {
			// Auto-generated catch block
			System.out.println(e.getMessage());
			return;
		}
		System.out.println("--------------------------------------------------------------------------------");
		System.out.printf("%15s %15s %15s %20s %10s\n", "Student ID", "Name", "Department", "Email ID", "Approval");
		System.out.println("--------------------------------------------------------------------------------");

		for (Student s : arr) {
			System.out.printf("%15s %15s %15s %20s %10s\n", s.getStudentID(), s.getStudentName(), s.getDepartment(),
					s.getEmailID(), s.getApprovalStatus());
		}
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println();

		while (true) {
			System.out.println("1. Approve Student");
			System.out.println("2. Revoke Student Permission");
			System.out.println("3. Remove Student");
			System.out.println("4. Return");
			int choice = MenuOptionScanner.nextInt();
			switch (choice) {
			case 1:
				approveStudent();
				break;
			case 2:
				disApproveStudent();
				break;
			case 3:
				removeStudent();
				break;
			case 4:
				return;
			default:
				System.out.println("***** Wrong Choice *****");
			}
		}
	}

	private void removeStudent() {
		// Auto-generated method stub
		System.out.println("Enter Student Id:");
		String studentId = CRSApplication.scan.next();

		try {
			if (adminInterface.removeStudent(studentId)) {
				System.out.println("Student Removed Successfully");
			} else {
				System.out.println("Something went wrong");
			}
		} catch (UserNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
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
		} catch (NoProfessorsFoundException e) {
			System.out.println(e.getMessage());
			return;
		} catch (SQLException e) {
			// Auto-generated catch block
			System.out.println(e.getMessage());
			return;
		}

		System.out.println("---------------------------------------------------------------------------");
		System.out.printf("%15s %15s %15s %25s\n", "Professor ID", "Name", "Department", "Email ID");
		System.out.println("---------------------------------------------------------------------------");
		for (Professor prof : arr) {
			System.out.printf("%15s %15s %15s %25s\n", prof.getProfessorId(), prof.getProfessorName(),
					prof.getDepartment(), prof.getEmailID());
		}
		System.out.println("---------------------------------------------------------------------------");
		System.out.println();

		while (true) {
			System.out.println("1. Remove Professor");
			System.out.println("2. Modify Professor Details");
			System.out.println("3. Return");
			int choice = MenuOptionScanner.nextInt();
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
