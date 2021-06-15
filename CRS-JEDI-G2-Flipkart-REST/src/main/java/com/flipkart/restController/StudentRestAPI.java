/**
 * 
 */
package com.flipkart.restController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.flipkart.bean.Course;
import com.flipkart.bean.CourseCatalog;
import com.flipkart.bean.CourseDetail;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.InvalidCCSessionSemesterException;
import com.flipkart.service.CourseCatalogInterface;
import com.flipkart.service.CourseCatalogServiceImpl;
import com.flipkart.service.CourseInterface;
import com.flipkart.service.CourseServiceImpl;
import com.flipkart.service.RegisteredCourseInterface;
import com.flipkart.service.RegisteredCourseServiceImpl;
import com.flipkart.service.StudentInterface;
import com.flipkart.service.StudentServiceImpl;

/**
 * @author aysh
 *
 */
@Path("/student")
public class StudentRestAPI {

	CourseCatalogInterface courseCatalogInterface = CourseCatalogServiceImpl.getInstance();
	CourseInterface courseInterface = CourseServiceImpl.getInstance();
	StudentInterface studentInterface = StudentServiceImpl.getInstance();
	RegisteredCourseInterface registeredCourseInterface = RegisteredCourseServiceImpl.getInstance();

	private List<CourseDetail> getCourseDetail(List<String> courseids) {
		try {

			List<CourseDetail> courseDetails = new ArrayList<CourseDetail>();

			for (String id : courseids) {
				Course course = courseInterface.getCourse(id);
				CourseCatalog catalog = courseCatalogInterface.getCourseCatalog(id);

				CourseDetail detail = new CourseDetail();

				detail.setCourse(course);
				detail.setCatalog(catalog);

				courseDetails.add(detail);
			}

			return courseDetails;

		} catch (Exception e) {
			return null;
		}
	}

	@GET
	@Path("/allCourses")
	public Response getAllCourses(@NotNull @FormParam("studentid") String studentId,
			@NotNull @FormParam("semester") int semester) {

		try {
			Student student = studentInterface.getStudentById(studentId);
			ArrayList<CourseCatalog> arr = new ArrayList<CourseCatalog>();

			arr = courseCatalogInterface.getCourseCatalogBySessionSemester(student.getSession(), semester);

			List<String> courseids = arr.stream().map(cat -> cat.getCourseId()).collect(Collectors.toList());

			return Response.status(200).entity(getCourseDetail(courseids)).build();

		} catch (Exception e) {
			// Auto-generated catch block
			return Response.status(400).entity(e.getMessage()).build();
		}
	}

	@GET
	@Path("/registeredCourses")
	public Response getRegisteredCourses(@NotNull @FormParam("studentid") String studentId,
			@NotNull @FormParam("semester") int semester) {

		try {
			Student student = studentInterface.getStudentById(studentId);
			ArrayList<RegisteredCourse> arr = registeredCourseInterface.getRegisteredCourses(student.getStudentID(),
					semester);

			List<String> courseids = arr.stream().map(cat -> cat.getCourseId()).collect(Collectors.toList());

			return Response.status(200).entity(getCourseDetail(courseids)).build();

		} catch (Exception e) {
			// Auto-generated catch block
			return Response.status(400).entity(e.getMessage()).build();
		}
	}
}
