/**
 * 
 */
package com.flipkart.application;

import java.sql.SQLException;
import java.util.ArrayList;

import com.flipkart.bean.CourseCatalog;
import com.flipkart.bean.Payment;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.SemesterReportCard;
import com.flipkart.bean.Student;
import com.flipkart.service.CourseCatalogInterface;
import com.flipkart.service.CourseCatalogServiceImpl;
import com.flipkart.service.PaymentInterface;
import com.flipkart.service.PaymentServiceImpl;
import com.flipkart.service.RegisteredCourseInterface;
import com.flipkart.service.RegisteredCourseServiceImpl;
import com.flipkart.service.SemesterReportCardInterface;
import com.flipkart.service.SemesterReportCardServiceImpl;
import com.flipkart.service.StudentInterface;
import com.flipkart.service.StudentServiceImpl;

/**
 * @author Aeron
 *
 */
public class CRSStudentMenu {

	StudentInterface studentInterface = StudentServiceImpl.getInstance();
	CourseCatalogInterface courseCatalogInterface = CourseCatalogServiceImpl.getInstance();
	PaymentInterface paymentInterface = PaymentServiceImpl.getInstance();
	RegisteredCourseInterface registeredCourseInterface = RegisteredCourseServiceImpl.getInstance();
	SemesterReportCardInterface semesterReportCardInterface = SemesterReportCardServiceImpl.getInstance();
	Student student = null;

	/**
	 * Creates the menu for Students
	 */
	public void createMenu() {
		if (CRSApplication.userId != null) {
			try {
				student = studentInterface.getStudentById(CRSApplication.userId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		while (CRSApplication.userId != null) {
			System.out.println("\n----------Student Menu-----------");
			System.out.println("1. View registered courses");
			System.out.println("2. View Fee receipt");
			System.out.println("3. View report card");
			System.out.println("4. Pay fee");
			System.out.println("5. Register for courses");
			System.out.println("6. Logout");

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
				student = null;
				CRSApplication.logout();
				break;
			default:
				System.out.println("Please enter a valid option");
			}
		}

	}

	private void registerInCourse() {
		// TODO Auto-generated method stub
		ArrayList<String> selectedCourses = new ArrayList<String>();

		while (true) {
			System.out.println("\n----------Course Registration-----------");
			System.out.println("1. View Selected Courses");
			System.out.println("2. Drop Course");
			System.out.println("3. Add Course");
			System.out.println("5. Submit Registration");
			System.out.println("4. View Courses");
			System.out.println("6. Return");
			int optionChoosed = CRSApplication.scan.nextInt();
			switch (optionChoosed) {
			case 1:
				viewSeletedCourses(selectedCourses);
				break;
			case 2:
				selectedCourses = addCourse(selectedCourses);
				break;
			case 3:
				selectedCourses = dropCourse(selectedCourses);
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

	private void submitRegistration(ArrayList<String> selectedCourses) {
		// TODO Auto-generated method stub
		System.out.println("Enter Semester");
		int semester = CRSApplication.scan.nextInt();
		for (String courseId : selectedCourses) {
			registeredCourseInterface.addRegisteredCourse(courseId, semester, null, student.getSession(),
					student.getStudentID());
		}
		System.out.println("success");
	}

	private void viewSeletedCourses(ArrayList<String> selectedCourses) {
		// TODO Auto-generated method stub
		for (String courseId : selectedCourses) {
			CourseCatalog catalog = courseCatalogInterface.getCourseCatalog(courseId);
		}
	}

	private ArrayList<String> addCourse(ArrayList<String> selectedCourses) {
		// TODO Auto-generated method stub
		System.out.println("Enter Course Id:");
		String courseId = CRSApplication.scan.next();
		selectedCourses.add(courseId);
		return selectedCourses;
	}

	private ArrayList<String> dropCourse(ArrayList<String> selectedCourses) {
		// TODO Auto-generated method stub
		System.out.println("Enter Course Id:");
		String courseId = CRSApplication.scan.next();
		selectedCourses.add(courseId);
		return selectedCourses;
	}

	private void viewCourses() {
		// TODO Auto-generated method stub
		System.out.println("Enter Semester");
		int semester = CRSApplication.scan.nextInt();
		courseCatalogInterface.getCourseCatalogBySessionSemester(student.getSession(), semester);
	}

	private void registeredCourses() {
		System.out.println("Enter Semester");
		int semester = CRSApplication.scan.nextInt();
		registeredCourseInterface.getRegisteredCourses(student.getStudentID(), student.getSession(), semester);
	}

	private void feeReciept() {
		System.out.println("Enter Semester");
		int semester = CRSApplication.scan.nextInt();
		Payment reciept = null;
		try {
			reciept = paymentInterface.getFeeReciept(CRSApplication.userId, semester);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(reciept == null)
		{
			System.out.println("Fee not payed");
		}
		else
		{
			System.out.println("amount " + reciept.getAmount() + " paid on " + reciept.getDateOfPayment());
		}
	}

	private void reportCard() {
		System.out.println("Enter Semester");
		int semester = CRSApplication.scan.nextInt();
		ArrayList<SemesterReportCard> semesterReportCards = semesterReportCardInterface
				.getSemesterReportCardByStudentId(CRSApplication.userId);
		ArrayList<RegisteredCourse> courses = registeredCourseInterface.getRegisteredCourses(student.getStudentID(),
				student.getSession(), semester);
	}

	private void payFees() {
		System.out.println("Enter Semester");
		int semester = CRSApplication.scan.nextInt();
		registeredCourseInterface.getRegisteredCourses(student.getStudentID(), student.getSession(), semester);
		float amount = 10000;
		System.out.println("Fee Payable");
		System.out.println("Enter Mode Of Payment (offline/online)");
		String modeOfPayment = CRSApplication.scan.next();
		Payment reciept = null;
		try {
			if (modeOfPayment == "offline") {

				reciept = paymentInterface.offlinePayment(student.getStudentID(), amount, semester);

			} else {
				reciept = paymentInterface.onlinePayment(student.getStudentID(), amount, semester);
			}
			System.out.println(reciept.getStatus());
			if (reciept.getStatus() == "success") {

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}