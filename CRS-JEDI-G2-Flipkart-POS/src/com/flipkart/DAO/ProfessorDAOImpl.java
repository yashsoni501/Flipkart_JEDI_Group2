/**
 * 
 */
package com.flipkart.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.flipkart.bean.CourseCatalog;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
/**
 * @author jagru
 *
 */
public class ProfessorDAOImpl implements ProfessorDAOInterface {
	
	private static volatile ProfessorDAOImpl instance = null;
	DAOConnectionInterface instanceDAO = new DAOConnectionImpl();
	
	/**
	 * Method to make ProfessorDAOImpl Singleton
	 * @return
	 */
	public static ProfessorDAOImpl getInstance()
	{
		if(instance == null)
		{
			synchronized(ProfessorDAOImpl.class){
				instance = new ProfessorDAOImpl();
			}
		}
		return instance;
	}
	
	public ArrayList<CourseCatalog> viewOptedCourses(String professorId) throws SQLException {

		String SelectOptedCoursesProf = "select * from 'courseCatalog' where profId = ?";
		PreparedStatement stmt = instanceDAO.conn.prepareStatement(SelectOptedCoursesProf);
		stmt.setString(1, professorId);
		ResultSet rs = stmt.executeQuery(SelectOptedCoursesProf);
		ArrayList<CourseCatalog> arr = new ArrayList<CourseCatalog>();
		while(rs.next()){
			int id  = rs.getInt("courseId");
			CourseCatalog temp=new CourseCatalog();
	        temp.setCourseId(String.valueOf(id));
//	        temp.setCourseId(name);
			
	        arr.add(temp);
		}
		return arr;
	}

	public boolean optInCourse(String professorId, String courseId) throws SQLException {

		String OptInCoureProf = "update `courseCatalog` set profId = ? where courseId = ?";
		PreparedStatement stmt = instanceDAO.conn.prepareStatement(OptInCoure);
		stmt.setString(1, professorId);
		stmt.setString(2, courseId);

		int rows = stmt.executeUpdate();
		System.out.println("Rows impacted : " + rows);

		return true;
	}

	@Override
	public Professor getProfessorDetails(String userId) throws SQLException {
		// TODO Auto-generated method stub
		String GetProfessorDet = "Select * from professor where profid = ?";
		PreparedStatement stmt = instanceDAO.conn.prepareStatement(GetProfessorDet);
		stmt.setString(1, userId);
		ResultSet rs = stmt.executeQuery(GetProfessorDet);
		Professor p = new Professor();
		while(rs.next()){
			p.setProfessorId(rs.getInt("profid"));
			p.setEmailID(rs.getString("email"));
			p.setProfessorName(rs.getString("name"));
			p.setDepartment(rs.getString("department"));
			
		}
		return p;
	}

	@Override
	public ArrayList<CourseCatalog> getDepartmentCourses(String department) throws SQLException {
		// TODO Auto-generated method stub
		String getDeptCoursesProf = "select * from 'courseCatalog' where department = ?";
		PreparedStatement stmt = instanceDAO.conn.prepareStatement(getDeptCoursesProf);
		stmt.setString(1, department);
		ResultSet rs = stmt.executeQuery(getDeptCoursesProf);
		ArrayList<CourseCatalog> arr = new ArrayList<CourseCatalog>();
		while(rs.next()){
			int id  = rs.getInt("courseId");
			CourseCatalog temp=new CourseCatalog();
	        temp.setCourseId(String.valueOf(id));
//	        temp.setCourseId(name);
			
	        arr.add(temp);
		}
		return arr;
	}

	@Override
	public ArrayList<Student> viewEnrolledStudents(String courseId) throws SQLException  {
		// TODO Auto-generated method stub
		String ViewEnrolledStu = "select stuId from 'registeredCourse' where courseId = ?";
		PreparedStatement stmt = instanceDAO.conn.prepareStatement(ViewEnrolledStu);
		stmt.setString(1, courseId);
		ResultSet rs = stmt.executeQuery(ViewEnrolledStu);
		ArrayList<Student> arr = new ArrayList<Student>();
		while(rs.next()){
			int id  = rs.getInt("courseId");
			Student s = new Student();
			s.setStudentID(String.valueOf(id));
			
	        arr.add(s);
		}
		return arr;
	}

	@Override
	public boolean submitGrade(int courseId, int studentId, int semester, String session, String grade) throws SQLException {
		// TODO Auto-generated method stub
		String GradeSubmission = "update `registeredCourse` set grade = ? where courseId = ? and stuid = ? and semester = ? and session = ?";
		PreparedStatement stmt = instanceDAO.conn.prepareStatement(GradeSubmission);
		stmt.setString(1, grade);
		stmt.setInt(2, courseId);
		stmt.setInt(3, studentId);
		stmt.setInt(4, semester);
		stmt.setString(5, session);

		int rows = stmt.executeUpdate();
		System.out.println("Rows impacted : " + rows);
		return false;
	}

	
}


