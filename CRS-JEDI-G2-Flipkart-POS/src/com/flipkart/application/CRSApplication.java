/**
 * 
 */
package com.flipkart.application;

import java.sql.SQLException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.service.AdminInterface;
import com.flipkart.service.AdminServiceImpl;
import com.flipkart.service.AuthInterface;
import com.flipkart.service.AuthServiceImpl;
import com.flipkart.utils.MenuOptionScanner;
import com.flipkart.constant.Constants;
import com.flipkart.exception.InvalidCredentialsException;
import com.flipkart.exception.UserEmailAlreadyInUseException;
import com.flipkart.exception.UserEmailNotFoundException;
import com.flipkart.exception.UserNotFoundException;

// Auto-generated Javadoc
/**
 * The Class CRSApplication.
 *
 * @author yashsoni501
 */
public class CRSApplication {

	/** The user id. */
	public static String userId = null;

	Logger logger = Logger.getLogger(CRSApplication.class.getName());
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
		int userInput;
		do {
			createMainMenu();
			userInput = MenuOptionScanner.nextInt();

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
		} while (userInput != 4);

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
		try {
			if (adminInterface.addStudent(userName, userEmail, password, department, session)) {
				System.out.println("Student Added Successfully");
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
		try {
			boolean isUpdated = authInterface.updatePassword(userEmail, oldPassword, newPassword);
			if (isUpdated)
				System.out.println("Password updated successfully!");
			else
				System.out.println("Something went wrong, please try again!");
		} catch (InvalidCredentialsException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (UserEmailNotFoundException e) {
			System.out.println(e.getMessage());
		}
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
		try {
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
		} catch (InvalidCredentialsException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (UserEmailNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (UserNotFoundException e) {
			System.out.println(e.getMessage());
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
