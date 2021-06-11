/**
 * 
 */
package com.flipkart.application;
import java.util.Scanner;

import com.flipkart.service.AuthInterface;
import com.flipkart.service.AuthServiceImpl;

/**
 * @author yashsoni501
 *
 */
public class CRSApplication {
	
	public static String userId = null;
	public static AuthInterface authInterface = AuthServiceImpl.getInstance();
	public static Scanner scan = new Scanner(System.in);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CRSApplication crsApplication=new CRSApplication();
		createMainMenu();
		int userInput = scan.nextInt();
		while(userInput!=2)
		{
			switch(userInput)
			{	
				case 1:
					//login
					crsApplication.loginUser();
					break;
				case 2:
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
		
	public void updatePassword() {
		// TODO Auto-generated method stub
		String userEmail, newPassword,oldPassword;
		
		System.out.println("------------------Update Password--------------------");
		System.out.println("Email");
		userEmail = scan.next();
		System.out.println("Old Password");
		oldPassword = scan.next();
		System.out.println("New Password:");
		newPassword=scan.next();
		boolean isUpdated = authInterface.updatePassword(userEmail,oldPassword,newPassword);
		if(isUpdated)
			System.out.println("Password updated successfully!");
		else
			System.out.println("Something went wrong, please try again!");
	}

	public static void createMainMenu()
	{
		System.out.println("----------Welcome to Course Management System---------");
		System.out.println("1. Login");
		System.out.println("2. Update Password");
		System.out.println("3. Exit");
		System.out.println("Enter user input");
	}
	
	public void loginUser()
	{
		String userEmail,password;
		System.out.println("-----------------Login------------------");
		System.out.println("Email:");
		userEmail = scan.next();
		System.out.println("Password:");
		password = scan.next();
		userId = authInterface.verifyUserWithEmailPassword(userEmail, password);
		if(userId != null)
		{
			String userRole = authInterface.getRole(userId);
			switch(userRole)
			{
			case "ADMIN":
				System.out.println(" Login Successful");
				CRSAdminMenu adminMenu=new CRSAdminMenu();
				adminMenu.createMenu();
				break;
			case "PROFESSOR":
				System.out.println(" Login Successful");
				CRSProfessorMenu professorMenu=new CRSProfessorMenu();
				professorMenu.createMenu();
				
				break;
			case "STUDENT":
				System.out.println(" Login Successful");
				CRSStudentMenu studentMenu=new CRSStudentMenu();
				studentMenu.createMenu();
				break;
			}
		}
	}
	

	public static void logout() {
		// TODO Auto-generated method stub
		userId=null;
		authInterface.logout();
		System.out.println(" Logout Successful");
	}
	
}
