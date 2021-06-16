package com.flipkart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * The Class DBUtils.
 */
public class DBUtils {

	/** The connection. */
	private static Connection connection = null;

	/**
	 * Instantiates a DB connection if null, else return the existing connection.
	 *
	 * @return the connection
	 */
	public static Connection getConnection() {

		if (connection != null)
			return connection;
		else {
			try {
//				Properties prop = new Properties();
//				InputStream inputStream = DBUtils.class.getClassLoader().getResourceAsStream("/home/yashsoni501/config.properties");
//				prop.load(inputStream);
//				String driver = prop.getProperty("driver");
//				String url = prop.getProperty("url");
//				String user = prop.getProperty("user");
//				String password = prop.getProperty("password");
//				Class.forName(driver);
//				connection = DriverManager.getConnection(url, user, password);
				Properties prop = new Properties();
//                InputStream inputStream = new FileInputStream("/home/yashsoni501/config.properties");
//                prop.load(inputStream);
                String driver = "com.mysql.jdbc.Driver";
                String url = "jdbc:mysql://localhost/crs";
                String user = "yash";
                String password = "yash";
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
			} catch(Exception e)
			{
				System.out.println("some error with file");
				System.out.println(e.getMessage());
			}
			return connection;
		}

	}

}
