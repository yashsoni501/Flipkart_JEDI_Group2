/**
 * 
 */
package com.flipkart.restController;

import java.sql.SQLException;

import javax.validation.constraints.NotNull;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.flipkart.bean.Student;
import com.flipkart.constant.Constants;
import com.flipkart.exception.InvalidCredentialsException;
import com.flipkart.exception.UserEmailAlreadyInUseException;
import com.flipkart.exception.UserEmailNotFoundException;
import com.flipkart.service.AdminInterface;
import com.flipkart.service.AdminServiceImpl;
import com.flipkart.service.AuthInterface;
import com.flipkart.service.AuthServiceImpl;
import com.flipkart.service.StudentInterface;
import com.flipkart.service.StudentServiceImpl;

/**
 * @author aysh
 *
 */
@Path("/user")
public class UserRestAPI {

	AuthInterface authInterface = AuthServiceImpl.getInstance();
	StudentInterface studentInterface = StudentServiceImpl.getInstance();
	AdminInterface adminInterface = AdminServiceImpl.getInstance();
//	ProfessorInterface profInterface = ProfessorServiceImpl.getInstance();
	
	@GET
	@Path("/hello")
	public Response getHello() {
		return Response.status(200).entity("Hello from server").build();
	}
	
	@POST
	@Path("/login")
	public Response verifyCredentials(@NotNull @FormParam("email") String email,
			@NotNull @FormParam("password") String password) {

		try {
			String userId = authInterface.verifyUserWithEmailPassword(email, password);

			if (userId != null) {
				String userRole = authInterface.getRole(userId);

				if (userRole.equals(Constants.USER_ROLE_STUDENT)) {
					Student student = studentInterface.getStudentById(userId);

					if (student.getApprovalStatus().equals(Constants.FALSE)) {
						return Response.status(200).entity("Login Failed. You are not Approved by admin").build();
					}
				}
				return Response.status(200).entity("Login successful").build();

			} else {
				return Response.status(200).entity("Login Failed").build();
			}
		} catch (Exception e) {
			return Response.status(201).entity(e.getMessage()).build();
		}
	}

	@POST
	@Path("/registerStudent")
	public Response registerStudent(
			@NotNull @FormParam("name") String name,
			@NotNull @FormParam("email") String email,
			@NotNull @FormParam("password") String password,
			@NotNull @FormParam("department") String department,
			@NotNull @FormParam("session") String session) {

		try {
			if (adminInterface.addStudent(name, email, password, department, session)) {
				return Response.status(300).entity("Registration Successful. You cannot login until admin's approval.").build();
			} else {
				return Response.status(300).entity("Something went wrong...Please try again").build();
			}
		} catch (Exception e) {
			return Response.status(300).entity(e.getMessage()).build();
		}
	}

	
	@POST
	@Path("/updatePassword")
	public Response updatePassword(@NotNull @FormParam("email") String email,
			@NotNull @FormParam("oldPassword") String oldPassword,
			@NotNull @FormParam("newPassword") String newPassword) {

		try {
			boolean isUpdated = authInterface.updatePassword(email, oldPassword, newPassword);
			if (isUpdated)
				return Response.status(200).entity("Password Updated Successfully").build();
			else
				return Response.status(200).entity("Something went wrong, please try again!").build();

		} catch (Exception e) {
			return Response.status(500).entity(e.getMessage()).build();
		}
	}

	@GET
	@Path("/role")
	public Response getRole(@NotNull @QueryParam("userid") String userId) {
		try {
			String role = authInterface.getRole(userId);

//			if(role.equals(Constants.USER_ROLE_PROFESSOR)) {
//				ArrayList<Professor> profs = new ArrayList<Professor>();
//				profs.add(profInterface.getProfessorDetails(userId));
//				profs.add(profInterface.getProfessorDetails(userId));
//				profs.add(profInterface.getProfessorDetails(userId));
//			
//				return Response.status(200).entity(profs).build();
//
//			}

			return Response.status(200).entity("Your Role is " + role).build();

		} catch (Exception e) {
			return Response.status(500).entity(e.getMessage()).build();
		}
	}

}
