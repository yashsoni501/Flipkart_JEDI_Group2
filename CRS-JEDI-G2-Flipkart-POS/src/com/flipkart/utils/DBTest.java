/**
 * 
 */
package com.flipkart.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author aysh
 *
 */
public class DBTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = DBUtils.getConnection();
		try {
			String sql="select * from auth";
			PreparedStatement stmt = conn.prepareStatement(sql);			
			ResultSet res = stmt.executeQuery();
			
			while(res.next()) {
				String uid = res.getString("uid");
				String email = res.getString("email");
				String role = res.getString("userRole");
				
				System.out.println(uid + "\t" + email + "\t" + role);
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	
		
	      
	}

}
