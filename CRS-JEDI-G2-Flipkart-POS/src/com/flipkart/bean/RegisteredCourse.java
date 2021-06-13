/**
 * 
 */
package com.flipkart.bean;

/**
 * @author Nihal
 *
 */
public class RegisteredCourse {

	private String courseId;
	private int semester;
	private String grade;
	private String session;
	private String studentId;

	/**
	 * @return the course_id
	 */
	public String getCourseId() {
		return courseId;
	}

	/**
	 * @param course_id the course_id to set
	 */
	public void setCourseId(String courseId) {
		this.courseId = courseId;
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
	 * @return the grade
	 */
	public String getGrade() {
		return grade;
	}

	/**
	 * @param grade the grade to set
	 */
	public void setGrade(String grade) {
		this.grade = grade;
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
	 * @return the student_id
	 */
	public String getStudentId() {
		return studentId;
	}

	/**
	 * @param student_id the student_id to set
	 */
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

}
