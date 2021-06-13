/**
 * 
 */
package com.flipkart.bean;

/**
 * @author Nihal
 *
 */
public class CourseCatalog {

	private String courseId;
	private String professorId;
	private int semester;
	private String session;
	private float credits;

	/**
	 * @return the courseId
	 */
	public String getCourseId() {
		return courseId;
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
	 * @param courseId the courseId to set
	 */
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	/**
	 * @return the professorId
	 */
	public String getProfessorId() {
		return professorId;
	}

	/**
	 * @param professorId the professorId to set
	 */
	public void setProfessorId(String professorId) {
		this.professorId = professorId;
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
	public float getCredits() {
		return credits;
	}

	/**
	 * @param credits the credits to set
	 */
	public void setCredits(float credits) {
		this.credits = credits;
	}

}
