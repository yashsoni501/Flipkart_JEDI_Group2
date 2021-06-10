/**
 * 
 */
package com.flipkart.application;

import java.util.Scanner;

/**
 * @author jagru
 *
 */
public class CRSAdminMenu {
	Scanner scanner =new Scanner(System.in);
	public void createMenu() {
			System.out.println("*****************************");
			System.out.println("**********Admin Menu*********");
			System.out.println("*****************************");
			System.out.println("1. View All Course Catalogue");
			System.out.println("2. Add Professor");
			System.out.println("3. Add Student");
			System.out.println("4. Add course in Catalog");
			System.out.println("5. Add course ");
			System.out.println("6. Remove course");
			System.out.println("7. Remove Course in Catalog");
			System.out.println("8. Enable Registration");
			System.out.println("9. Disable Registration");
			System.out.println("10. Enable Payment");
			System.out.println("11. Disable Payment");
			System.out.println("12. Generate Report Card");
			System.out.println("13. Logout");
			System.out.println("*****************************");
			
		    
			int choice = scanner.nextInt();
			
			switch(choice) {
			case 1:
				getAllCourseCatalog();
				break;
				
			case 2:
				addProfessor();
				break;
				
			case 3:
				addStudent();
				break;
			case 4:
				addCourseCatalog();
				break;
			case 5:
				addCourse();
				break;	
			case 6:
				removeCourse();
				break;
			case 7:
				removeCourseCatalog();
				break;
			
			case 8:
				enableCourseRegistration();
				return;
			case 9:
				disableCourseRegistration();
				return;	
			case 10:
				enablePayment();
				return;
			
			case 11:
				disablePayment();
				return;	
			case 12:
				generateReportCard();
				return;	
			case 13:
				logout();
				return;		

			default:
				System.out.println("***** Wrong Choice *****");
			}
			scanner.close();
		}

	private void logout() {
		// TODO Auto-generated method stub
		
	}

	private void disablePayment() {
		// TODO Auto-generated method stub
		
	}

	private void enablePayment() {
		// TODO Auto-generated method stub
		
	}

	private void enableCourseRegistration() {
		// TODO Auto-generated method stub
		
	}

	private void disableCourseRegistration() {
		// TODO Auto-generated method stub
		
	}

	private void generateReportCard() {
		// TODO Auto-generated method stub
		
	}

	private void removeCourseCatalog() {
		// TODO Auto-generated method stub
		
	}

	

	private void addCourse() {
		// TODO Auto-generated method stub
		
	}

	private void addCourseCatalog() {
		// TODO Auto-generated method stub
		
	}

	private void addStudent() {
		// TODO Auto-generated method stub
		
	}

	private void addProfessor() {
		// TODO Auto-generated method stub
		
	}

	private void getAllCourseCatalog() {
		// TODO Auto-generated method stub
		
	}

	private void removeCourse() {
		// TODO Auto-generated method stub
		
	}
	
}
	
	