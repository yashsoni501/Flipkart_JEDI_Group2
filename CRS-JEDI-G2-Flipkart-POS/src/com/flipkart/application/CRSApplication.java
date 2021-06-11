/**
 * 
 */
package com.flipkart.application;
import java.util.Scanner;

/**
 * @author yashsoni501
 *
 */
public class CRSApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
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
		System.out.println("Login");
	}
	
}
