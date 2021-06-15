/**
 * 
 */
package com.flipkart.utils;

import java.util.InputMismatchException;

import com.flipkart.application.CRSApplication;

// TODO: Auto-generated Javadoc
/**
 * The Class MenuOptionScanner.
 *
 * @author aysh
 */
public class MenuOptionScanner {

	/**
	 * Create Private Constructor.
	 */
	private MenuOptionScanner() {

	}

	/**
	 * Get input number.
	 *
	 * @return the input number
	 * @return 1000 in case of exception
	 */

	public static int nextInt() {
		try {
			int userInput = CRSApplication.scan.nextInt();
			return userInput;
		} catch (InputMismatchException e) {
			CRSApplication.scan.nextLine();
			System.out.println("Please use integer inputs.");
			return 1000;
		} catch (Exception e) {
			CRSApplication.scan.nextLine();
			System.out.println(e.getMessage());
			return 1000;
		}
	}

}
