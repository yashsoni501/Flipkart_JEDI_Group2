/**
 * 
 */
package com.flipkart.service;

import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.CourseRegistration;

/**
 * @author aysh
 *
 */
public interface AdminInterface {

	
	// Admin can add courses to the catalog
    public boolean addCourse(int course_id, String courseName, String Department);

    // Admin can publish the course catalog for this semester
    public boolean addCourseCatalog(Course course);

    // Admin can delete the course catalog for this semester
    public void dropCourseCatalog(int courseId);

    public boolean removeCourse(int courseId);
    
    public boolean addProfessor(int professorId, String name, String address, String department, String emailId, String password);
    
    public boolean addStudent(int student_id, String name, String address, String department, String email, String password);
        
    public List<CourseRegistration> fetchNonVerifiedCourseRegistration();
    
    public boolean verifyCourseRegistration (int student_id);
    
    public boolean enableOrDisableCourseRegistration (boolean flag);
    
    public boolean enableOrDisablePayment (boolean flag);
    
    public boolean generateReportCard();
}
