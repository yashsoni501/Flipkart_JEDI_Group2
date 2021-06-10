/**
 * 
 */
package com.flipkart.bean;

/**
 * @author Nihal
 *
 */
public class CourseCatalog {
	
	private String course_id;
	private String professor_id;
	private int semester;
	private int credits;
	
	/**
	 * @return the course_id
	 */
	public String getCourse_id() {
		return course_id;
	}
	/**
	 * @param course_id the course_id to set
	 */
	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}
	/**
	 * @return the professor_id
	 */
	public String getProfessor_id() {
		return professor_id;
	}
	/**
	 * @param professor_id the professor_id to set
	 */
	public void setProfessor_id(String professor_id) {
		this.professor_id = professor_id;
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
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}
	/**
	 * @param credits the credits to set
	 */
	public void setCredits(int credits) {
		this.credits = credits;
	}
	
}
