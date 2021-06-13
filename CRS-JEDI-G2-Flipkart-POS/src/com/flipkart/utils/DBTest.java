/**
 * 
 */
package com.flipkart.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
			final String INSERT = "insert into auth (`uid`, `email`, `password`, `userRole`) values (?, ?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(INSERT);

			stmt.setString(1, "5");
			stmt.setString(2, "testm2");
			stmt.setString(3, "testps2");
			stmt.setString(4, "testur2");

			int row = stmt.executeUpdate();
			if (row == 0) {
				System.out.println("Failed to add");
			} else {
				System.out.println("added auth user");
			}

			final String VIEW = "select * from auth";
			stmt = conn.prepareStatement(VIEW);
			ResultSet res = stmt.executeQuery();

			while (res.next()) {
				int uid = res.getInt("uid");
				String email = res.getString("email");
				String role = res.getString("userRole");

				System.out.println(uid + "\t" + email + "\t" + role);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
