/**
 * 
 */
package com.flipkart.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

// TODO: Auto-generated Javadoc
/**
 * The Class MenuOptionScanner.
 *
 * @author aysh
 */
public class MenuOptionScanner {

	/** The scanner. */
	public static volatile Scanner scanner = null;

	/**
	 * Create Private Constructor.
	 */
	private MenuOptionScanner() {

	}

	/**
	 * Get input number.
	 *
	 * @return the input number
	 * @return 1000 incase of exception
	 */

	public static int nextInt() {
		if (scanner == null) {
			scanner = new Scanner(System.in);
		}
		try {
			return scanner.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Please use integer inputs.");
			return 1000;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 1000;
		}
	}

}
