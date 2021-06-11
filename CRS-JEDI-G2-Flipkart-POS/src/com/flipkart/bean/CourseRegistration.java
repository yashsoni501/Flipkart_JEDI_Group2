/**
 * 
 */
package com.flipkart.bean;
import java.util.ArrayList;

/**
 * @author Nihal
 *
 */
public class CourseRegistration {
	
	private ArrayList<RegisteredCourse> registered_courses = new ArrayList<RegisteredCourse>();
	private String student_id;
	private String session;
	private int semester;
	private boolean approval_status;
	
	/**
	 * @return the registered_courses
	 */
	public ArrayList<RegisteredCourse> getRegistered_courses() {
		return registered_courses;
	}
	/**
	 * @param registered_courses the registered_courses to set
	 */
	public void setRegistered_courses(RegisteredCourse registered_course) {
		this.registered_courses.add(registered_course);
	}
	/**
	 * @return the student_id
	 */
	public String getStudent_id() {
		return student_id;
	}
	/**
	 * @param student_id the student_id to set
	 */
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	/**
	 * @return the session
	 */
	public String getSession() {
		return session;
	}
	/**
	 * @param session the session to set
	 */
	public void setSession(String session) {
		this.session = session;
	}
	/**
	 * @return the semester
	 */
	public int getSemester() {
		return semester;
	}
	/**
	 * @param semester the semester to set
	 */
	public void setSemester(int semester) {
		this.semester = semester;
	}
	/**
	 * @return the approval_status
	 */
	public boolean isApproval_status() {
		return approval_status;
	}
	/**
	 * @param approval_status the approval_status to set
	 */
	public void setApproval_status(boolean approval_status) {
		this.approval_status = approval_status;
	}
	
	
}
