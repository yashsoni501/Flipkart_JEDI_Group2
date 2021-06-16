/**
 * 
 */
package com.flipkart.application;

import java.sql.SQLException;
import java.util.ArrayList;

import com.flipkart.bean.*;
import com.flipkart.service.*;
import com.flipkart.utils.MenuOptionScanner;
import com.flipkart.constant.Constants;
import com.flipkart.exception.*;

/**
 * The Class CRSStudentMenu.
 *
 * @author Aeron
 */
public class CRSStudentMenu {

	/** The student interface. */
	StudentInterface studentInterface = StudentServiceImpl.getInstance();

	/** The course catalog interface. */
	CourseCatalogInterface courseCatalogInterface = CourseCatalogServiceImpl.getInstance();

	/** The payment interface. */
	PaymentInterface paymentInterface = PaymentServiceImpl.getInstance();

	/** The registered course interface. */
	RegisteredCourseInterface registeredCourseInterface = RegisteredCourseServiceImpl.getInstance();

	/** The semester report card interface. */
	SemesterReportCardInterface semesterReportCardInterface = SemesterReportCardServiceImpl.getInstance();

	/** The course interface. */
	CourseInterface courseInterface = CourseServiceImpl.getInstance();

	/** The admin interface. */
	AdminInterface adminInterface = AdminServiceImpl.getInstance();

	/** The student. */
	Student student = null;

	/**
	 * Creates the menu for Students.
	 */
	public void createMenu() {
		if (CRSApplication.userId != null) {
			try {

				student = studentInterface.getStudentById(CRSApplication.userId);

			} catch (SQLException e) {
				// Auto-generated catch block
				System.out.println(e.getMessage());
			} catch (UserNotFoundException e) {
				// Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
		if (student.getApprovalStatus().equals(Constants.FALSE)) {
			System.out.println("You have not been aprroved by the Admin.");
			return;
		}
		while (CRSApplication.userId != null) {
			System.out.println("\n----------Student Menu-----------");
			System.out.println("1. View registered courses");
			System.out.println("2. View Fee receipt");
			System.out.println("3. View report card");
			System.out.println("4. Pay fee");
			System.out.println("5. Register for courses");
			System.out.println("6. View Details");
			System.out.println("7. Modify Details");
			System.out.println("8. Logout");

			int optionChoosed = MenuOptionScanner.nextInt();
			switch (optionChoosed) {
			case 1:
				registeredCourses();
				break;
			case 2:
				feeReciept();
				break;
			case 3:
				reportCard();
				break;
			case 4:
				payFees();
				break;
			case 5:
				registerInCourse();
				break;
			case 6:
				viewStudent();
				break;
			case 7:
				modifyStudent();
				break;
			case 8:
				student = null;
				CRSApplication.logout();
				break;
			default:
				System.out.println("Please enter a valid option");
			}
		}

	}

	private void viewStudent() {
		// Auto-generated method stub

		System.out.println("--------------------------------------------------------------------------------");
		System.out.printf("%15s %15s %15s %20s %10s\n", "Student ID", "Name", "Department", "Email ID", "Approval");
		System.out.println("--------------------------------------------------------------------------------");

		System.out.printf("%15s %15s %15s %20s %10s\n", student.getStudentID(), student.getStudentName(),
				student.getDepartment(), student.getEmailID(), student.getApprovalStatus());
	}

	private void modifyStudent() {
		// Auto-generated method stub

		System.out.println("Enter Email:");
		String email = CRSApplication.scan.next();

		System.out.println("Enter Name:");
		String studentName = CRSApplication.scan.next();

		System.out.println("Enter Department:");
		String department = CRSApplication.scan.next();

		System.out.println("Enter Session:");
		String session = CRSApplication.scan.next();

		try {
			if (adminInterface.modifyStudent(email, studentName, department, session)) {
				System.out.println("Student Updated Successfully");
			} else {
				System.out.println("Something went wrong");
			}
		} catch (SQLException e) {
			// Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (UserEmailNotFoundException e) {
			// Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Register in course.
	 */
	private void registerInCourse() {
		boolean courseWindow;
		try {
			courseWindow = adminInterface.getCourseRegistrationFlag();

			if (!courseWindow) {
				System.out.println("The Course Registration window is closed.");
				return;
			}

			ArrayList<CourseCatalog> selectedCourses = new ArrayList<CourseCatalog>();

			while (true) {
				System.out.println("\n----------Course Registration-----------");
				System.out.println("1. View Selected Courses");
				System.out.println("2. Drop Course");
				System.out.println("3. Add Course");
				System.out.println("4. View Courses");
				System.out.println("5. Submit Registration");
				System.out.println("6. Return");
				int optionChoosed = MenuOptionScanner.nextInt();
				switch (optionChoosed) {
				case 1:
					viewSeletedCourses(selectedCourses);
					break;
				case 2:
					selectedCourses = dropCourse(selectedCourses);
					break;
				case 3:
					selectedCourses = addCourse(selectedCourses);
					break;
				case 4:
					viewCourses();
					break;
				case 5:
					submitRegistration(selectedCourses);
					break;
				case 6:
					return;
				default:
					System.out.println("Please enter a valid option");
				}
			}
		} catch (ConstantFlagNotSetException e) {
			// Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			// Auto-generated catch block
			System.out.println(e.getMessage());
		}

	}

	/**
	 * Submit registration.
	 *
	 * @param selectedCourses the selected courses
	 */
	private void submitRegistration(ArrayList<CourseCatalog> selectedCourses) {
		if (selectedCourses.size() != 6) {
			System.out.println("Please select exactly 6 courses. You have chosen " + selectedCourses.size());
			return;
		}

		System.out.println("Enter Semester");
		int semester = MenuOptionScanner.nextInt();
		try {
			for (CourseCatalog course : selectedCourses) {

				registeredCourseInterface.addRegisteredCourse(course.getCourseId(), semester, null,
						student.getSession(), student.getStudentID());

			}
			System.out.println("success");
		} catch (SQLException e) {
			// Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	/**
	 * View seleted courses.
	 *
	 * @param selectedCourses the selected courses
	 */
	private void viewSeletedCourses(ArrayList<CourseCatalog> selectedCourses) {
		// Auto-generated method stub
		try {
			System.out.println(
					"---------------------------------------------------------------------------------------------------------------");
			System.out.printf("%10s %15s %15s %10s %10s %10s\n", "Course ID", "Course Name", "Department", "Semester",
					"Session", "Credits");
			System.out.println(
					"---------------------------------------------------------------------------------------------------------------");
			for (CourseCatalog catalog : selectedCourses) {
				Course course = courseInterface.getCourse(catalog.getCourseId());
				System.out.printf("%10s %15s %15s", course.getCourseID(), course.getCourseName(),
						course.getDepartment());
				System.out.printf("%10s %10s %10s\n", catalog.getSemester(), catalog.getSession(),
						catalog.getCredits());
			}
			System.out.println(
					"---------------------------------------------------------------------------------------------------------------");
			System.out.println();
		} catch (CourseNotFoundException e) {
			System.out.println(e.getMessage());
			return;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Adds the course.
	 *
	 * @param selectedCourses the selected courses
	 * @return the array list
	 */
	private ArrayList<CourseCatalog> addCourse(ArrayList<CourseCatalog> selectedCourses) {
		// Auto-generated method stub
		if (selectedCourses.size() == 6) {
			System.out.println("Maximum number of Courses are already added");
			return selectedCourses;
		}
		System.out.println("Enter Course Id:");
		String courseId = CRSApplication.scan.next();

		CourseCatalog course = null;
		try {
			course = courseCatalogInterface.getCourseCatalog(courseId);

			if (course == null || course.getCourseId() == null) {
				System.out.println("Course with given ID does not exist");
			} else {
				if (!selectedCourses.contains(course)) {
					selectedCourses.add(course);
				} else {
					System.out.println("You have already selected this course");
				}
			}
		} catch (CourseCatalogEntryNotFoundException e) {
			// Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return selectedCourses;
	}

	/**
	 * Drop course.
	 *
	 * @param selectedCourses the selected courses
	 * @return the array list
	 */
	private ArrayList<CourseCatalog> dropCourse(ArrayList<CourseCatalog> selectedCourses) {
		// Auto-generated method stub
		if (selectedCourses.size() == 0) {
			System.out.println("No course to drop");
			return selectedCourses;
		}
		System.out.println("Enter Course Id:");
		String courseId = CRSApplication.scan.next();
		selectedCourses.removeIf(course -> course.getCourseId() == courseId);
		return selectedCourses;
	}

	/**
	 * View courses.
	 */
	private void viewCourses() {
		// Auto-generated method stub
		System.out.println("Enter Semester");
		int semester = MenuOptionScanner.nextInt();
		try {
			ArrayList<CourseCatalog> arr = new ArrayList<CourseCatalog>();

			arr = courseCatalogInterface.getCourseCatalogBySessionSemester(student.getSession(), semester);

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

		} catch (InvalidCCSessionSemesterException e) {
			// Auto-generated catch block
			System.out.println(e.getMessage());
			return;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return;
		} catch (CourseNotFoundException e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * Registered courses.
	 */
	private void registeredCourses() {
		System.out.println("Enter Semester");
		int semester = MenuOptionScanner.nextInt();
		try {
			ArrayList<RegisteredCourse> arr = registeredCourseInterface.getRegisteredCourses(student.getStudentID(),
					semester);

			System.out.println(
					"---------------------------------------------------------------------------------------------------------------");
			System.out.printf("%10s %15s %15s %10s %20s\n", "Course ID", "Course Name", "Department", "Credits",
					"Professor ID");
			System.out.println(
					"---------------------------------------------------------------------------------------------------------------");

			for (RegisteredCourse regCourse : arr) {
				Course course = courseInterface.getCourse(regCourse.getCourseId());
				CourseCatalog catalog = courseCatalogInterface.getCourseCatalog(regCourse.getCourseId());

				System.out.printf("%10s %15s %15s %10s %20s\n", course.getCourseID(), course.getCourseName(),
						course.getDepartment(), catalog.getCredits(), catalog.getProfessorId());

			}
			System.out.println(
					"---------------------------------------------------------------------------------------------------------------");
			System.out.println();

		} catch (SQLException e) {
			// Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (CourseCatalogEntryNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (CourseNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (NoRegisteredCoursesException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Fee reciept.
	 */
	private void feeReciept() {
		System.out.println("Enter Semester");
		int semester = MenuOptionScanner.nextInt();
		Payment reciept = null;
		try {
			reciept = paymentInterface.getFeeReciept(CRSApplication.userId, semester);
			if (reciept.getStatus().equalsIgnoreCase(Constants.PAYMENT_FAILURE)) {
				System.out.println("Fee is not paid for " + semester + " semester");
				return;
			}
			System.out.println(
					"----------------------------------------------------------------------------------------");
			System.out.println("Reference ID \t Payment Mode \t Amount \t Date");
			System.out.println(reciept.getReferenceId() + "\t" + reciept.getModeOfPayment() + "\t" + reciept.getAmount()
					+ "\t" + reciept.getDateOfPayment());
			System.out.println(
					"----------------------------------------------------------------------------------------");
			System.out.println();
		} catch (SQLException e) {
			// Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (FeeRecieptNotFoundException e) {
			// Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Report card.
	 */
	private void reportCard() {
		try {
			ArrayList<SemesterReportCard> semesterReportCards = semesterReportCardInterface
					.getSemesterReportCardByStudentId(CRSApplication.userId);

			if (semesterReportCards.size() == 0) {
				System.out.println("The report cards aren't yet generated");
				return;
			}

			for (int i = 0; i < semesterReportCards.size(); i++) {
				ArrayList<RegisteredCourse> arr1 = registeredCourseInterface
						.getRegisteredCourses(student.getStudentID(), semesterReportCards.get(i).getCurrentSem());

				System.out.println("Semester : " + semesterReportCards.get(i).getCurrentSem() + "\n"
						+ " Semeseter sgpa : " + semesterReportCards.get(i).getSgpa());
				System.out.println("---------------------------------------------------------------");
				System.out.println("Course ID \t Course Name \t Department \t Professor ID \t Credits \t Grade");
				for (int j = 0; j < arr1.size(); j++) {
					CourseCatalog arr = courseCatalogInterface.getCourseCatalog(arr1.get(j).getCourseId());
					Course course = courseInterface.getCourse(arr1.get(j).getCourseId());
					System.out.println(course.getCourseID() + "\t\t" + course.getCourseName() + "\t"
							+ course.getDepartment() + "\t" + arr.getProfessorId() + "\t\t" + arr.getCredits() + "\t"
							+ arr1.get(j).getGrade());
				}
				System.out.println("");
			}
		} catch (SQLException e) {
			// Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (SemesterReportCardNotFound e) {

			System.out.println(e.getMessage());
		} catch (CourseCatalogEntryNotFoundException e) {
			// Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (CourseNotFoundException e) {
			// Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (NoRegisteredCoursesException e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * Pay fees.
	 */
	private void payFees() {
		boolean feeWindow;
		try {
			feeWindow = adminInterface.getPaymentFlag();
			if (!feeWindow) {
				System.out.println("The Fee Payment window is closed.");
				return;
			}

			System.out.println("Enter Semester");
			int semester = MenuOptionScanner.nextInt();
			Payment reciept = new Payment();
			try {
				reciept = paymentInterface.getFeeReciept(CRSApplication.userId, semester);
				System.out.println("Fee is already paid for " + semester + " semester");
				return;
				
			} catch (FeeRecieptNotFoundException e) {
				// Auto-generated catch block
				System.out.println("Entering Payment Gateway... Please be patient");
			}

			ArrayList<RegisteredCourse> arr1 = registeredCourseInterface.getRegisteredCourses(student.getStudentID(),
					semester);

			System.out.println(
					"----------------------------------------------------------------------------------------");
			System.out.println("Course ID \t Course Name \t Department \t Credits \t Professor ID");
			System.out.println(
					"----------------------------------------------------------------------------------------");
			for (int i = 0; i < arr1.size(); i++) {
				CourseCatalog arr = courseCatalogInterface.getCourseCatalog(arr1.get(i).getCourseId());
				Course course = courseInterface.getCourse(arr1.get(i).getCourseId());
				System.out.println(course.getCourseID() + "\t" + course.getCourseName() + "\t" + course.getDepartment()
						+ "\t" + arr.getCredits() + "\t" + arr.getProfessorId());
			}
			System.out.println(
					"----------------------------------------------------------------------------------------");
			System.out.println();

			float amount = 1000;
			System.out.println("Fee Payable: Rs " + amount);
			System.out.println("Enter Mode Of Payment (offline/online)");
			String modeOfPayment = CRSApplication.scan.next();
			reciept = null;

			if (modeOfPayment.equalsIgnoreCase(Constants.PAYMENT_OFFLINE)) {

				reciept = paymentInterface.offlinePayment(student.getStudentID(), amount, semester);

			} else {
				reciept = paymentInterface.onlinePayment(student.getStudentID(), amount, semester);
			}
			System.out.println(reciept.getStatus());

			if (reciept.getStatus().equalsIgnoreCase(Constants.PAYMENT_SUCCESS)) {
				System.out.println(
						"----------------------------------------------------------------------------------------");
				System.out.println("Reference ID \t Payment Mode \t Amount \t Date");
				System.out.println(reciept.getReferenceId() + "\t" + reciept.getModeOfPayment() + "\t"
						+ reciept.getAmount() + "\t" + reciept.getDateOfPayment());
				System.out.println(
						"----------------------------------------------------------------------------------------");
				System.out.println();
			} else {
				System.out.println("Payment Failed");
			}
		} catch (SQLException e) {
			// Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (ConstantFlagNotSetException e) {
			// Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (CourseCatalogEntryNotFoundException e) {
			// Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (CourseNotFoundException e) {
			// Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (NoRegisteredCoursesException e) {
			System.out.println(e.getMessage());
		}
	}
}