/**
 * 
 */
package com.flipkart.application;

import java.sql.SQLException;
import java.util.ArrayList;

import com.flipkart.bean.Course;
import com.flipkart.bean.CourseCatalog;
import com.flipkart.bean.Payment;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.SemesterReportCard;
import com.flipkart.bean.Student;
import com.flipkart.service.AdminInterface;
import com.flipkart.service.AdminServiceImpl;
import com.flipkart.service.CourseCatalogInterface;
import com.flipkart.service.CourseCatalogServiceImpl;
import com.flipkart.service.CourseInterface;
import com.flipkart.service.CourseServiceImpl;
import com.flipkart.service.PaymentInterface;
import com.flipkart.service.PaymentServiceImpl;
import com.flipkart.service.RegisteredCourseInterface;
import com.flipkart.service.RegisteredCourseServiceImpl;
import com.flipkart.service.SemesterReportCardInterface;
import com.flipkart.service.SemesterReportCardServiceImpl;
import com.flipkart.service.StudentInterface;
import com.flipkart.service.StudentServiceImpl;
import com.flipkart.constant.Constants;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.exception.SemesterReportCardNotFound;

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
				e.getMessage();
			} catch (UserNotFoundException e) {
					//  Auto-generated catch block
					e.getMessage();
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

			int optionChoosed = CRSApplication.scan.nextInt();
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
		System.out.println("---------------------------------------------------");
		System.out.println("Student ID \t Name \t Department \t Email ID \t Approval");
		System.out.println("---------------------------------------------------");

		System.out.println(student.getStudentID() + "\t" + student.getStudentName() + "\t" + student.getDepartment()
				+ "\t" + student.getEmailID() + "\t" + student.getApprovalStatus());
		System.out.println("---------------------------------------------------");
		System.out.println();
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

		if (adminInterface.modifyStudent(email, studentName, department, session)) {
			System.out.println("Student Updated Successfully");
		} else {
			System.out.println("Something went wrong");
		}
	}

	/**
	 * Register in course.
	 */
	private void registerInCourse() {
		boolean courseWindow = adminInterface.getCourseRegistrationFlag();

		if (!courseWindow) {
			System.out.println("The Coure Registration window is closed.");
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
			int optionChoosed = CRSApplication.scan.nextInt();
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
		int semester = CRSApplication.scan.nextInt();
		try {
			for (CourseCatalog course : selectedCourses) {

				registeredCourseInterface.addRegisteredCourse(course.getCourseId(), semester, null,
						student.getSession(), student.getStudentID());

			}
		} catch (SQLException e) {
			// Auto-generated catch block
			e.getMessage();
		}
		System.out.println("success");
	}

	/**
	 * View seleted courses.
	 *
	 * @param selectedCourses the selected courses
	 */
	private void viewSeletedCourses(ArrayList<CourseCatalog> selectedCourses) {
		// Auto-generated method stub
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println("Course ID \t Course Name \t Department \t Semester \t Session \t Credits");
		System.out.println("----------------------------------------------------------------------------------------");
		for (CourseCatalog arr : selectedCourses) {
			Course course = courseInterface.getCourse(arr.getCourseId());
			System.out.println(course.getCourseID() + "\t" + course.getCourseName() + "\t" + course.getDepartment()
					+ "\t" + arr.getSemester() + "\t" + arr.getSession() + "\t" + arr.getCredits());
		}
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println();
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

		CourseCatalog course = courseCatalogInterface.getCourseCatalog(courseId);

		if (course.getCourseId() == null) {
			System.out.println("Course with given ID does not exist");
		} else {
			if (!selectedCourses.contains(course)) {
				selectedCourses.add(course);
			} else {
				System.out.println("You have already selected this course");
			}
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
		int semester = CRSApplication.scan.nextInt();
		ArrayList<CourseCatalog> arr = new ArrayList<CourseCatalog>();
		arr = courseCatalogInterface.getCourseCatalogBySessionSemester(student.getSession(), semester);

		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println("Course ID \t Course Name \t Department \t Semester \t Session \t Credits");
		System.out.println("----------------------------------------------------------------------------------------");
		for (int i = 0; i < arr.size(); i++) {
			Course course = courseInterface.getCourse(arr.get(i).getCourseId());
			System.out.println(course.getCourseID() + "\t" + course.getCourseName() + "\t" + course.getDepartment()
					+ "\t" + arr.get(i).getSemester() + "\t" + arr.get(i).getSession() + "\t"
					+ arr.get(i).getCredits());
		}
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println();
	}

	/**
	 * Registered courses.
	 */
	private void registeredCourses() {
		System.out.println("Enter Semester");
		int semester = CRSApplication.scan.nextInt();
		try {
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

		} catch (SQLException e) {
			// Auto-generated catch block
			e.getMessage();
		}
	}

	/**
	 * Fee reciept.
	 */
	private void feeReciept() {
		System.out.println("Enter Semester");
		int semester = CRSApplication.scan.nextInt();
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
			e.getMessage();
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
			e.getMessage();
		} catch (SemesterReportCardNotFound e) {
			
			e.getMessage();
		}

	}

	/**
	 * Pay fees.
	 */
	private void payFees() {
		boolean feeWindow = adminInterface.getPaymentFlag();

		if (!feeWindow) {
			System.out.println("The Fee Payment window is closed.");
			return;
		}

		System.out.println("Enter Semester");
		int semester = CRSApplication.scan.nextInt();
		Payment reciept = new Payment();
		try {

			reciept = paymentInterface.getFeeReciept(CRSApplication.userId, semester);

			if (reciept.getStatus().equalsIgnoreCase(Constants.PAYMENT_SUCCESS)) {
				System.out.println("Fee is already paid for " + semester + " semester");
				return;
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
			e.getMessage();
		}
	}
}