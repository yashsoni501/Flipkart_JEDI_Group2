/**
 * 
 */
package com.flipkart.application;

import com.flipkart.service.AdminInterface;
import com.flipkart.service.AdminServiceImpl;

/**
 * @author jagru
 *
 */
public class CRSAdminMenu {

	AdminInterface adminInterface = AdminServiceImpl.getInstance();

	public void createMenu() {

		while (CRSApplication.userId != null) {
			System.out.println("**********Admin Menu*********");
			System.out.println("1. View All Course Catalogue");
			System.out.println("1. View All Students");
			System.out.println("1. View All Courses");
			System.out.println("1. View All Professors");
			System.out.println("8. Enable Registration");
			System.out.println("9. Disable Registration");
			System.out.println("10. Enable Payment");
			System.out.println("11. Disable Payment");
			System.out.println("12. Generate Report Card");
			System.out.println("13. Logout");

			int choice = CRSApplication.scan.nextInt();

			switch (choice) {
			case 1:
				getAllCourseCatalog();
				break;
			case 2:
				getAllStudents();
				break;
			case 3:
				getAllProfessors();
				break;
			case 4:
				getAllCourses();
				break;
			case 8:
				adminInterface.enableCourseRegistration();
				return;
			case 9:
				adminInterface.disableCourseRegistration();
				return;
			case 10:
				adminInterface.enablePayment();
				return;
			case 11:
				adminInterface.disablePayment();
				return;
			case 12:
				generateReportCard();
				return;
			case 13:
				CRSApplication.logout();
				return;

			default:
				System.out.println("***** Wrong Choice *****");
			}
		}
	}
	
	private void generateReportCard() {
		// TODO Auto-generated method stub
		
		System.out.println("Enter Session:");
		String session = CRSApplication.scan.next();
		
		if(adminInterface.generateReportCard(session))
		{
			System.out.println("Student Removed Successfully");
		}else {
			System.out.println("Something went wrong");
		}
		
	}


	private void addCourse() {
		// TODO Auto-generated method stub
		System.out.println("Enter Course Name:");
		String courseName = CRSApplication.scan.next();
		
		System.out.println("Enter Department:");
		String department = CRSApplication.scan.next();
		
		
		if(adminInterface.addCourse(courseName, department))
		{
			System.out.println("Student Added Successfully");
		}else {
			System.out.println("Something went wrong");
		}
	}
	
	private void removeCourse() {
		// TODO Auto-generated method stub
		
		System.out.println("Enter Student Id:");
		String courseId = CRSApplication.scan.next();
		
		if(adminInterface.removeCourse(courseId))
		{
			System.out.println("Student Removed Successfully");
		}else {
			System.out.println("Something went wrong");
		}
		
	}
	private void modifyCourse() {
		// TODO Auto-generated method stub
		
		System.out.println("Enter Student Id:");
		String courseId = CRSApplication.scan.next();
		
		System.out.println("Enter Course Name:");
		String courseName = CRSApplication.scan.next();
		
		System.out.println("Enter Department:");
		String department = CRSApplication.scan.next();
		
		if( adminInterface.modifyCourse(courseId,courseName,department))
		{
			System.out.println("Student Updated Successfully");
		}else {
			System.out.println("Something went wrong");
		}
		
	}

	private void addCourseCatalog() {
		// TODO Auto-generated method stub
		System.out.println("Enter Student Name:");
		String courseId = CRSApplication.scan.next();
		
		System.out.println("Enter Department:");
		int semester = CRSApplication.scan.nextInt();
		
		System.out.println("Enter Session:");
		String session = CRSApplication.scan.next();
		
		System.out.println("Enter Student Email:");
		int credits = CRSApplication.scan.nextInt();
		
		if(adminInterface.addCourseCatalog(courseId,semester,session,credits))
		{
			System.out.println("Student Added Successfully");
		}else {
			System.out.println("Something went wrong");
		}
	}
	
	private void removeCourseCatalog() {
		// TODO Auto-generated method stub
		
		System.out.println("Enter Student Id:");
		String courseId = CRSApplication.scan.next();
		
		if(adminInterface.removeCourseCatalog(courseId))
		{
			System.out.println("Student Removed Successfully");
		}else {
			System.out.println("Something went wrong");
		}
		
	}
	private void modifyCourseCatalog() {
		// TODO Auto-generated method stub
		
		System.out.println("Enter Student Name:");
		String courseId = CRSApplication.scan.next();
		
		System.out.println("Enter Department:");
		int semester = CRSApplication.scan.nextInt();
		
		System.out.println("Enter Session:");
		String session = CRSApplication.scan.next();
		
		System.out.println("Enter Student Email:");
		int credits = CRSApplication.scan.nextInt();
		
		if( adminInterface.modifyCourseCatalog(courseId,semester,session,credits))
		{
			System.out.println("Student Updated Successfully");
		}else {
			System.out.println("Something went wrong");
		}
		
	}

	private void addStudent() {
		// TODO Auto-generated method stub
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
		
		String studentId = CRSApplication.authInterface.addUserWithEmailPassword(userEmail, password,"PROFESSOR");
		
		if(adminInterface.addStudent(studentId, studentName, department, userEmail,session))
		{
			System.out.println("Student Added Successfully");
		}else {
			System.out.println("Something went wrong");
		}
	}
	
	private void removeStudent() {
		// TODO Auto-generated method stub
		
		System.out.println("Enter Student Id:");
		String studentId = CRSApplication.scan.next();
		
		if(CRSApplication.authInterface.removeUser(studentId) && adminInterface.removeProfessor(studentId))
		{
			System.out.println("Student Removed Successfully");
		}else {
			System.out.println("Something went wrong");
		}
		
	}
	private void modifyStudent() {
		// TODO Auto-generated method stub
		
		System.out.println("Enter Student Id:");
		String studentId = CRSApplication.scan.next();
		
		System.out.println("Enter Student Name:");
		String studentName = CRSApplication.scan.next();
		
		System.out.println("Enter Department:");
		String department = CRSApplication.scan.next();
		
		System.out.println("Enter Session:");
		String session = CRSApplication.scan.next();
		
		if( adminInterface.modifyStudnet(studentId,studentName,department,session))
		{
			System.out.println("Student Updated Successfully");
		}else {
			System.out.println("Something went wrong");
		}
		
	}

	private void addProfessor() {
		// TODO Auto-generated method stub
		
		System.out.println("Enter Professor Name:");
		String professorName = CRSApplication.scan.next();
		
		System.out.println("Enter Department:");
		String department = CRSApplication.scan.next();
		
		System.out.println("Enter Professor Email:");
		String userEmail = CRSApplication.scan.next();
		
		System.out.println("Enter Password:");
		String password = CRSApplication.scan.next();
		
		String profId = CRSApplication.authInterface.addUserWithEmailPassword(userEmail, password,"PROFESSOR");
		
		if(adminInterface.addProfessor(profId, professorName, department, userEmail))
		{
			System.out.println("Professor Added Successfully");
		}else {
			System.out.println("Something went wrong");
		}
		
		
	}
	
	private void removeProfessor() {
		// TODO Auto-generated method stub
		
		System.out.println("Enter Professor Id:");
		String profId = CRSApplication.scan.next();
		
		if(CRSApplication.authInterface.removeUser(profId) && adminInterface.removeProfessor(profId))
		{
			System.out.println("Professor Removed Successfully");
		}else {
			System.out.println("Something went wrong");
		}
		
	}
	private void modifyProfessor() {
		// TODO Auto-generated method stub
		
		System.out.println("Enter Professor Id:");
		String profId = CRSApplication.scan.next();
		
		System.out.println("Enter Professor Name:");
		String professorName = CRSApplication.scan.next();
		
		System.out.println("Enter Department:");
		String department = CRSApplication.scan.next();
		
		if( adminInterface.modifyProfessor(profId,professorName,department))
		{
			System.out.println("Professor Updated Successfully");
		}else {
			System.out.println("Something went wrong");
		}
		
	}
	

	private void getAllCourseCatalog() {
		// TODO Auto-generated method stub
		while(true) {
			System.out.println("2. Add Professor");
			System.out.println("3. Remove Professor");
			System.out.println("3. Modify Professor Details");
			System.out.println("4. Return");
			int choice = CRSApplication.scan.nextInt();
			switch (choice) {
			case 1:
				addCourseCatalog();
				break;
			case 2:
				removeCourseCatalog();
				break;
			case 3:
				modifyCourseCatalog();
				break;
			case 4:
				return;
			default:
				System.out.println("***** Wrong Choice *****");
			}
		}
	}
	private void getAllCourses() {
		// TODO Auto-generated method stub
		while(true) {
			System.out.println("2. Add Professor");
			System.out.println("3. Remove Professor");
			System.out.println("3. Modify Professor Details");
			System.out.println("4. Return");
			int choice = CRSApplication.scan.nextInt();
			switch (choice) {
			case 1:
				addCourse();
				break;
			case 2:
				removeCourse();
				break;
			case 3:
				modifyCourse();
				break;
			case 4:
				return;
			default:
				System.out.println("***** Wrong Choice *****");
			}
		}
	}
	
	private void getAllStudents() {
		// TODO Auto-generated method stub
		while(true) {
			System.out.println("2. Add Professor");
			System.out.println("3. Remove Professor");
			System.out.println("3. Modify Professor Details");
			System.out.println("4. Return");
			int choice = CRSApplication.scan.nextInt();
			switch (choice) {
			case 1:
				addStudent();
				break;
			case 2:
				removeStudent();
				break;
			case 3:
				modifyStudent();
				break;
			case 4:
				return;
			default:
				System.out.println("***** Wrong Choice *****");
			}
		}
	}
	
	private void getAllProfessors() {
		// TODO Auto-generated method stub
		while(true) {
			System.out.println("2. Add Professor");
			System.out.println("3. Remove Professor");
			System.out.println("3. Modify Professor Details");
			System.out.println("4. Return");
			int choice = CRSApplication.scan.nextInt();
			switch (choice) {
			case 1:
				addProfessor();
				break;
			case 2:
				removeProfessor();
				break;
			case 3:
				modifyProfessor();
				break;
			case 4:
				return;
			default:
				System.out.println("***** Wrong Choice *****");
			}
		}
	}

}
