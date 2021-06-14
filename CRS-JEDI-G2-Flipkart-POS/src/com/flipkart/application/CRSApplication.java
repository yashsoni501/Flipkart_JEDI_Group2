/**
 * 
 */
package com.flipkart.application;

import java.util.Scanner;

import com.flipkart.service.AdminInterface;
import com.flipkart.service.AdminServiceImpl;
import com.flipkart.service.AuthInterface;
import com.flipkart.service.AuthServiceImpl;
import com.flipkart.constant.Constants;

// Auto-generated Javadoc
/**
 * The Class CRSApplication.
 *
 * @author yashsoni501
 */
public class CRSApplication {

	/** The user id. */
	public static String userId = null;

	/** The auth interface. */
	public static AuthInterface authInterface = AuthServiceImpl.getInstance();

	/** The admin interface. */
	AdminInterface adminInterface = AdminServiceImpl.getInstance();

	/** The scan. */
	public static Scanner scan = new Scanner(System.in);

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		CRSApplication crsApplication = new CRSApplication();
		createMainMenu();
		int userInput = scan.nextInt();
		while (userInput != 4) {
			switch (userInput) {
			case 1:
				// login
				crsApplication.loginUser();
				break;
			case 2:
				// signUp
				crsApplication.studentSignUp();
				break;
			case 3:
				// update password
				crsApplication.updatePassword();
				break;
			default:
				System.out.println("Invalid Input");
			}
			createMainMenu();
			userInput = scan.nextInt();
		}
		scan.close();
	}

	private void studentSignUp() {
		// Auto-generated method stub
		String userEmail, password, userName, department, session;
		System.out.println("-----------------Student SignUp------------------");
		System.out.println("Email:");
		userEmail = scan.next();
		System.out.println("Password:");
		password = scan.next();
		System.out.println("Name:");
		userName = scan.next();
		System.out.println("Department:");
		department = scan.next();
		System.out.println("Session:");
		session = scan.next();
		if (adminInterface.addStudent(userName, userEmail, password, department, session)) {
			System.out.println("Student Added Successfully");
		} else {
			System.out.println("Something went wrong");
		}
	}

	/**
	 * Update password.
	 */
	public void updatePassword() {
		// Auto-generated method stub
		String userEmail, newPassword, oldPassword;

		System.out.println("------------------Update Password--------------------");
		System.out.println("Email");
		userEmail = scan.next();
		System.out.println("Old Password");
		oldPassword = scan.next();
		System.out.println("New Password:");
		newPassword = scan.next();
		boolean isUpdated = authInterface.updatePassword(userEmail, oldPassword, newPassword);
		if (isUpdated)
			System.out.println("Password updated successfully!");
		else
			System.out.println("Something went wrong, please try again!");
	}

	/**
	 * Creates the main menu.
	 */
	public static void createMainMenu() {
		System.out.println("----------Welcome to Course Management System---------");
		System.out.println("1. Login");
		System.out.println("2. Student SignUp");
		System.out.println("3. Update Password");
		System.out.println("4. Exit");
		System.out.println("Enter user input");
	}

	/**
	 * Login user.
	 */
	public void loginUser() {
		String userEmail, password;
		System.out.println("-----------------Login------------------");
		System.out.println("Email:");
		userEmail = scan.next();
		System.out.println("Password:");
		password = scan.next();
		userId = authInterface.verifyUserWithEmailPassword(userEmail, password);
		if (userId != null) {
			String userRole = authInterface.getRole(userId);
			switch (userRole) {
			case Constants.USER_ROLE_ADMIN:
				System.out.println("Admin Login Successful");
				CRSAdminMenu adminMenu = new CRSAdminMenu();
				adminMenu.createMenu();
				break;
			case Constants.USER_ROLE_PROFESSOR:
				System.out.println("Professor Login Successful");
				CRSProfessorMenu professorMenu = new CRSProfessorMenu();
				professorMenu.createMenu();
				break;
			case Constants.USER_ROLE_STUDENT:
				System.out.println("Student Login Successful");
				CRSStudentMenu studentMenu = new CRSStudentMenu();
				studentMenu.createMenu();
				break;
			}
		} else {
			System.out.println("Invalid Credentials");
		}
	}

	/**
	 * Logout.
	 */
	public static void logout() {
		// Auto-generated method stub
		userId = null;
		authInterface.logout();
		System.out.println(" Logout Successful");
	}

}
