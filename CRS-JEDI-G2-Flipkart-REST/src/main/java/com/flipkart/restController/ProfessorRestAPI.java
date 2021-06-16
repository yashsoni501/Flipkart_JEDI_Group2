/**
 * 
 */
package com.flipkart.restController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.flipkart.bean.CourseCatalog;
import com.flipkart.bean.Student;
import com.flipkart.exception.EmptyCourseCatalogListExcpetion;
import com.flipkart.exception.GradeSubmissionFailedException;
import com.flipkart.exception.InvalidDepartmentException;
import com.flipkart.exception.NoEnrolledStudentsException;
import com.flipkart.exception.OptingTheCourseFailedException;
import com.flipkart.service.CourseCatalogInterface;
import com.flipkart.service.CourseCatalogServiceImpl;
import com.flipkart.service.ProfessorInterface;
import com.flipkart.service.ProfessorServiceImpl;

/**
 * @author yashsoni501
 *
 */
@Path("/professor")
public class ProfessorRestAPI {
	
	ProfessorInterface professorInterface = ProfessorServiceImpl.getInstance();
	CourseCatalogInterface catalogInterface = CourseCatalogServiceImpl.getInstance();
	
	@POST
	@Path("/courses")
	public Response viewCourses(@NotNull @FormParam("department") String department)
	{
		ArrayList<CourseCatalog> catalog = null;
		try {
			catalog = catalogInterface.getDepartmentCourseCatalog(department);
			
		} catch (InvalidDepartmentException | SQLException e) {
			return Response.status(404).entity(e.getMessage()).build();
		}
		
		return Response.status(200).entity(catalog).build();	
	}
	
	@POST
	@Path("/optedcourses")
	public Response viewOptedCourses(@NotNull @FormParam("profid") String professorId)
	{
		ArrayList<CourseCatalog> catalog = null;
		
		try {
			catalog = catalogInterface.getCourseCatalogByProfessorId(professorId);
		} catch (SQLException e) {
			// Auto-generated catch block
			return Response.status(500).entity(e.getMessage()).build();
		} catch (EmptyCourseCatalogListExcpetion e)
		{
			return Response.status(404).entity(e.getMessage()).build();
		}
		
		return Response.status(200).entity(catalog).build();	
	}
	
	@POST
	@Path("/optincourse")
	public Response optInCourse(@NotNull @FormParam("courseid") String courseId, 
			@NotNull @FormParam("profid") String professorId)
	{
		try {
			professorInterface.optInCourse(professorId, courseId);
		} catch (SQLException e) {
			return Response.status(500).entity(e.getMessage()).build();
		} catch (OptingTheCourseFailedException e) {
			return Response.status(500).entity(e.getMessage()).build();
		}
		
		return Response.status(200).entity("Course opted successfully").build();
	}
	
	@POST
	@Path("/students")
	public Response viewEnrolledStudents(@NotNull @FormParam("courseid") String courseId, 
			@NotNull @FormParam("session") String session)
	{
		try {
			ArrayList<Student> enrolledStudents = professorInterface.viewEnrolledStudents(courseId, session);
			return Response.status(200).entity(enrolledStudents).build();
		} catch (SQLException e) {
			return Response.status(500).entity(e.getMessage()).build();
		} catch (NoEnrolledStudentsException e) {
			return Response.status(500).entity(e.getMessage()).build();
		}
	}
	
	@POST
	@Path("/submitgrades")
	public Response submitGrades(
			@NotNull @FormParam("courseid") String courseId, 
			@NotNull @FormParam("studentids") List<String> studentIds, 
			@NotNull @FormParam("grades") List<String> grades)
	{
		if(studentIds.size() != grades.size())
		{
			return Response.status(400).entity("Grades and student ids are not equal in number").build();
		}
		
		int noOfStudents = studentIds.size();
		
		System.out.println(noOfStudents);
		try {
			for (int i = 0; i < noOfStudents; i++) {
				
					professorInterface.submitGrade(courseId, studentIds.get(i), grades.get(i));
	
			}
		} catch (SQLException e) {
			return Response.status(500).entity(e.getMessage()).build();
		} catch (GradeSubmissionFailedException e) {
			return Response.status(500).entity(e.getMessage()).build();
		}
		
		return Response.status(200).entity("Updated successfully").build();
	}
	
	@POST
	@Path("/optout")
	public Response optOutOfCourse(@NotNull @FormParam("courseid") String courseId, 
			@NotNull @FormParam("profid") String professorId)
	{
		try {
			catalogInterface.updateProfessorId(courseId, null);
		} catch (SQLException e) {
			return Response.status(500).entity(e.getMessage()).build();
		}
		
		return Response.status(200).entity("Opted out successfully").build();
	}
}
