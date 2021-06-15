/**
 * 
 */
package com.flipkart.restController;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.flipkart.application.CRSApplication;
import com.flipkart.application.CRSStudentMenu;
import com.flipkart.bean.CourseCatalog;
import com.flipkart.bean.Payment;
import com.flipkart.bean.Student;
import com.flipkart.constant.Constants;
import com.flipkart.exception.InvalidCredentialsException;
import com.flipkart.exception.UserEmailNotFoundException;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.service.AdminInterface;
import com.flipkart.service.AdminServiceImpl;
import com.flipkart.service.AuthInterface;
import com.flipkart.service.AuthServiceImpl;
import com.flipkart.service.PaymentInterface;
import com.flipkart.service.PaymentServiceImpl;
import com.flipkart.service.RegisteredCourseInterface;
import com.flipkart.service.RegisteredCourseServiceImpl;
import com.flipkart.service.StudentInterface;
import com.flipkart.service.StudentServiceImpl;

/**
 * @author Aeron
 *
 */

@Path("/student")
public class StudentRestAPI {

	StudentInterface studentInterface = StudentServiceImpl.getInstance();
	PaymentInterface paymentInterface = PaymentServiceImpl.getInstance();
	AdminInterface adminInterface = AdminServiceImpl.getInstance();
	RegisteredCourseInterface registeredCourseInterface = RegisteredCourseServiceImpl.getInstance();

	@GET
	@Path("/viewStudent")
	@Produces(MediaType.APPLICATION_JSON)
	public Response viewStudent(@NotNull @FormParam("student id") String id) {

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
	@Produces(MediaType.APPLICATION_JSON)
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
	@Produces(MediaType.APPLICATION_JSON)
	public Response submitRegistration(@NotNull @FormParam("student id") String id,
			@NotNull @FormParam("session") String session, @NotNull @FormParam("semester") int semester,
			@FormParam("selected courses") ArrayList<String> selectedCourses) {

		if (selectedCourses.size() != 6) {
			return Response.status(200)
					.entity("Please select exactly 6 courses. You have chosen " + selectedCourses.size()).build();
		}

		try {
			for (String courseId : selectedCourses) {
				registeredCourseInterface.addRegisteredCourse(courseId, semester, null, session, id);

			}
			return Response.status(200).entity("Registered Successfully").build();
		} catch (Exception e) {
			return Response.status(500).entity(e.getMessage()).build();
		}
	}

	@POST
	@Path("/feeReceipt")
	@Produces(MediaType.APPLICATION_JSON)
	public Response feeReceipt(@NotNull @FormParam("student id") String id,
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
	@Produces(MediaType.APPLICATION_JSON)
	public Response payFees(@NotNull @FormParam("student id") String id, @NotNull @FormParam("semester") int semester,
			@NotNull @FormParam("payment mode") String mode) {

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
}
