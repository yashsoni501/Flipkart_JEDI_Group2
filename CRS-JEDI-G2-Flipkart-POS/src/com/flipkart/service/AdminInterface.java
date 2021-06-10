/**
 * 
 */
package com.flipkart.service;

/**
 * @author aysh
 *
 */
public interface AdminInterface {

	
	// Admin can add courses with this catalog
    public void addCourse(int course_id, String course_name);

    // Admin can publish the course catalog for this semester
    public void addCourseCatalog();

    // Admin can delete the course catalog for this semester
    public void dropCourseCatalog();

    public void removeCourse(int course_id);
    
    public void addProfessor (int prof_id, String password);
    
    public void addStudent(int student_id, String password);
        
    public void fetchNonVerifiedCourseRegistration();
    
    public void verifyCourseRegistration (int student_id);
    
    public void enableOrDisableCourseRegistration (boolean flag);
    
    public void enableOrDisablePayment (boolean flag);
    
    public void generateReportCard ();
}
