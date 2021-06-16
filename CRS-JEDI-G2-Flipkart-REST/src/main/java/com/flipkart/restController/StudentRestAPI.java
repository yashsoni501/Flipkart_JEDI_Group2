/**
 * 
 */
package com.flipkart.restController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.flipkart.application.CRSApplication;
import com.flipkart.bean.Course;
import com.flipkart.bean.CourseCatalog;
import com.flipkart.bean.CourseDetail;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.SemesterReportCard;
import com.flipkart.bean.Student;
import com.flipkart.service.CourseCatalogInterface;
import com.flipkart.service.CourseCatalogServiceImpl;
import com.flipkart.service.CourseInterface;
import com.flipkart.service.CourseServiceImpl;

import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.flipkart.bean.Payment;
import com.flipkart.constant.Constants;
import com.flipkart.exception.CourseCatalogEntryNotFoundException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.NoRegisteredCoursesException;
import com.flipkart.exception.SemesterReportCardNotFound;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.service.AdminInterface;
import com.flipkart.service.AdminServiceImpl;
import com.flipkart.service.PaymentInterface;
import com.flipkart.service.PaymentServiceImpl;
import com.flipkart.service.RegisteredCourseInterface;
import com.flipkart.service.RegisteredCourseServiceImpl;
import com.flipkart.service.SemesterReportCardInterface;
import com.flipkart.service.SemesterReportCardServiceImpl;
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
	PaymentInterface paymentInterface = PaymentServiceImpl.getInstance();
	AdminInterface adminInterface = AdminServiceImpl.getInstance();
	SemesterReportCardInterface semesterReportCardInterface = SemesterReportCardServiceImpl.getInstance();

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
	public Response getAllCourses(@NotNull @QueryParam("studentid") String studentId,
			@NotNull @QueryParam("semester") int semester) {

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
	public Response getRegisteredCourses(@NotNull @QueryParam("studentid") String studentId,
			@NotNull @QueryParam("semester") int semester) {

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

	@GET
	@Path("/viewStudent")
	@Produces(MediaType.APPLICATION_JSON)
	public Response viewStudent(@NotNull @QueryParam("studentid") String id) {

		Student student;
		try {
			student = studentInterface.getStudentById(id);
			return Response.status(200).entity(student).build();

		} catch (SQLException | UserNotFoundException e) {
			return Response.status(500).entity(e.getMessage()).build();
		}
	}

	@POST
	@Path("/modifyStudent")
	public Response modifyStudent(@NotNull @FormParam("email") String email,
			@NotNull @FormParam("name") String studentName, @NotNull @FormParam("department") String department,
			@NotNull @FormParam("session") String session) {

		try {
			boolean isUpdated = adminInterface.modifyStudent(email, studentName, department, session);
			if (isUpdated)
				return Response.status(200).entity("Student details Updated Successfully").build();
			else
				return Response.status(400).entity("Something went wrong, please try again!").build();
		} catch (Exception e) {
			return Response.status(500).entity(e.getMessage()).build();
		}
	}

	@POST
	@Path("/submitRegistration")
	public Response submitRegistration(@NotNull @FormParam("studentid") String id,
			@NotNull @FormParam("session") String session, @NotNull @FormParam("semester") int semester,
			@FormParam("selectedcourses") List<String> selectedCourses) {
				
		if (selectedCourses.size() != 6) {
			return Response.status(200)
					.entity("Please select exactly 6 courses. You have chosen " + selectedCourses.size()).build();
		}

		try {
			for (String courseId : selectedCourses) {
//				System.out.println(courseId);
				registeredCourseInterface.addRegisteredCourse(courseId, semester, null, session, id);

			}
			return Response.status(200).entity("Registered Successfully").build();
		} catch (Exception e) {
			return Response.status(500).entity(e.getMessage()).build();
		}
	}

	@POST
	@Path("/feeReceipt")
	public Response feeReceipt(@NotNull @FormParam("studentid") String id,
			@NotNull @FormParam("semester") int semester) {

		try {
			Payment reciept = paymentInterface.getFeeReciept(id, semester);
			if (reciept.getStatus().equalsIgnoreCase(Constants.PAYMENT_FAILURE)) {
				return Response.status(200).entity("Fee is not paid for " + semester + " semester").build();
			}

			return Response.status(200).entity(reciept).build();

		} catch (Exception e) {
			return Response.status(500).entity(e.getMessage()).build();
		}
	}

	@POST
	@Path("/payFees")
	public Response payFees(@NotNull @FormParam("studentid") String id, @NotNull @FormParam("semester") int semester,
			@NotNull @FormParam("paymentmode") String mode) {

		try {
			boolean feeWindow = adminInterface.getPaymentFlag();
			if (!feeWindow) {
				return Response.status(200).entity("The Fee Payment window is closed.").build();
			}

			Payment reciept = new Payment();
			float amount = 1000;
			try {
				reciept = paymentInterface.getFeeReciept(id, semester);
				return Response.status(200).entity("Fee is already paid for " + semester + " semester").build();
			} catch (Exception e) {

				if (mode.equalsIgnoreCase(Constants.PAYMENT_OFFLINE)) {
					reciept = paymentInterface.offlinePayment(id, amount, semester);
				} else {
					reciept = paymentInterface.onlinePayment(id, amount, semester);
				}

				if (reciept.getStatus().equalsIgnoreCase(Constants.PAYMENT_SUCCESS)) {
					return Response.status(200).entity(reciept).build();
				} else {
					return Response.status(200).entity("Payment failed").build();
				}
			}

		} catch (Exception e) {
			return Response.status(500).entity(e.getMessage()).build();
		}
	}

	@GET
	@Path("/reportCard")
	public Response reportCard(@NotNull @QueryParam("studentid") String id) {
		try {
			ArrayList<SemesterReportCard> semesterReportCards = semesterReportCardInterface
					.getSemesterReportCardByStudentId(id);
			
			
			if (semesterReportCards.size() == 0) {
				System.out.println("The report cards aren't yet generated");

				return Response.status(200).entity("Reports Cards aren't generated yet").build();
			}

			return Response.status(200).entity(semesterReportCards).build();

		} catch (Exception e) {
			return Response.status(300).entity(e.getMessage()).build();
		}
	}
}
