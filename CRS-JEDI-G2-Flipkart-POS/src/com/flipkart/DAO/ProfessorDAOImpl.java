/**
 * 
 */
package com.flipkart.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.flipkart.bean.CourseCatalog;
/**
 * @author jagru
 *
 */
public class ProfessorDAOImpl implements ProfessorDAOInterface {
	
	DAOConnectionInterface instanceDAO = new DAOConnectionImpl();
	
	public ArrayList<CourseCatalog> viewOptedCourses(String professorId) throws SQLException {

		String sql = "select * from 'courseCatalog' where profId = ?";
		PreparedStatement stmt = instanceDAO.conn.prepareStatement(sql);
		stmt.setString(1, professorId);
		ResultSet rs = stmt.executeQuery(sql);
		ArrayList<CourseCatalog> arr = new ArrayList<CourseCatalog>();
		while(rs.next()){
			int id  = rs.getInt("courseId");
//			String name = rs.getString("courseName");
			
			CourseCatalog temp=new CourseCatalog();
	        temp.setCourseId(String.valueOf(id));
//	        temp.setCourseId(name);
			
	        arr.add(temp);
		}
		return arr;
	}

	public boolean optInCourse(String professorId, String courseId) throws SQLException {

		String sql = "update `courseCatalog` set profId = ? where courseId = ?";
		PreparedStatement stmt = instanceDAO.conn.prepareStatement(sql);
		stmt.setString(1, professorId);
		stmt.setString(2, courseId);

		int rows = stmt.executeUpdate();
		System.out.println("Rows impacted : " + rows);

		return true;
	}
}


