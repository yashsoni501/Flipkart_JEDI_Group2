/**
 * 
 */
package com.flipkart.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author aysh
 *
 */
public class DBUtils {
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/crs";

	// Database credentials
	static final String USER = "aysh";
	static final String PASS = "password";

	private static volatile DBUtils instance = null;
	private static volatile Connection connection = null;

	// Default Constructor
	private DBUtils() {

	}

	public static Connection getConnection() {
		if (instance == null) {
			synchronized (DBUtils.class) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");

					System.out.println("Connecting to database...");
					connection = DriverManager.getConnection(DB_URL, USER, PASS);
					System.out.println("Connected!");
				} catch (SQLException se) {
					se.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return connection;
	}
}
