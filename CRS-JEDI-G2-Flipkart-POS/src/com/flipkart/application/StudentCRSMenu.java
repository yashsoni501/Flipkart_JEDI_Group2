/**
 * 
 */
package com.flipkart.application;
 
/**
 * @author Aeron
 *
 */
public class StudentCRSMenu {

	StudentInterface studentInterface = StudentServiceImpl.getInstance();
	
	/** Creates the menu for Students
	 */
	public void create_menu( ) {
	
			if(studentInterface.checkVerification( )) {
				while(true) {
					System.out.println("\n----------Student Menu-----------");
					System.out.println("1. View registered courses");
					System.out.println("2. Check if Fee is Paid");
					System.out.println("3. View Fee receipt");
					System.out.println("4. View report card");
					System.out.println("5. Pay fees");
					System.out.println("6. Register in courses");
					
					int optionChoosed = io.input.nextInt();
					io.input.nextLine();
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
							registerCourses();
							break;
						default:
                            "Please enter valid option";
					}
				}
			}
			else {
				System.out.println("Profile not verified");
			}
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

    private void registerCourses() {
        
    }	 
}