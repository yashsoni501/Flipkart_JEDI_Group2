/**
 * 
 */
package com.flipkart.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author yashsoni501
 *
 */
public class DAOConnectionImpl implements DAOConnectionInterface {

	static final String DB_URL = "jdbc:mysql://localhost/crs";
	static final String USER = "root";
	static final String PASS = " ";
	
	public static Connection conn = null;
	
	/**
	 * @return
	 * @throws SQLException 
	 */
	public DAOConnectionImpl()
	{
		try
		{
			this.conn = DriverManager.getConnection(DB_URL,USER,PASS);
		} catch(SQLException er)
		{
			System.out.println("Cannot establish connection. Error: "+ er);
		}
	}
}
