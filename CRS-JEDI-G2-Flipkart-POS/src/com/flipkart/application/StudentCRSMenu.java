/**
 * 
 */
package com.flipkart.application;

import java.util.Scanner;

import com.flipkart.service.StudentInterface;
import com.flipkart.service.StudentServiceImpl;

/**
 * @author Aeron
 *
 */
public class StudentCRSMenu {

	
	Scanner scanner = new Scanner(System.in);
	StudentInterface studentInterface = StudentServiceImpl.getInstance();
	
	/** Creates the menu for Students
	 */
	public void create_menu( ) {
	
			if(CRSApplication.loggedIn) {
				while(true) {
					System.out.println("\n----------Student Menu-----------");
					System.out.println("1. View registered courses");
					System.out.println("2. Check if Fee is Paid");
					System.out.println("3. View Fee receipt");
					System.out.println("4. View report card");
					System.out.println("5. Pay fee");
					System.out.println("6. Register for courses");
					System.out.println("7. Logout");
					
					int optionChoosed = scanner.nextInt();
					switch(optionChoosed) {
						case 1:
							fetchRegisteredCourses();
							break;
						case 2:
							isFeePaid();
							break;
						case 3:
							viewFeeReciept();
							break;
						case 4:
							viewReportCard();
							break;
						case 5: 
							payfee();
							break;
						case 6:
							registerForCourses();
							break;
						case 7:
							CRSApplication.logout();
							break;
						default:
                            System.out.println("Please enter a valid option");
					}
				}
			}
			else {
				System.out.println("Profile not verified");
			}
	}
	
	private void registerForCourses() {
		// TODO Auto-generated method stub
		
	}

	private void fetchRegisteredCourses() {

    }

    private void isFeePaid() {

    }

    private void viewFeeReciept() {

    }

    private void viewReportCard() {
        
    }

    private void payfee() {
        
    } 
}