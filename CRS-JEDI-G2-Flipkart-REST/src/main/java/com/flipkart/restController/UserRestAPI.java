/**
 * 
 */
package com.flipkart.restController;

import java.sql.SQLException;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.flipkart.application.CRSAdminMenu;
import com.flipkart.application.CRSProfessorMenu;
import com.flipkart.application.CRSStudentMenu;
import com.flipkart.bean.Student;
import com.flipkart.constant.Constants;
import com.flipkart.exception.InvalidCredentialsException;
import com.flipkart.exception.UserEmailNotFoundException;
import com.flipkart.exception.UserNotFoundException;
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

	@POST
	@Path("/login")
	public Response verifyCredentials(@NotNull @FormParam("email") String email,
			@NotNull @FormParam("password") String password) {

		try {
			String userId = authInterface.verifyUserWithEmailPassword(email, password);

			if (userId != null) {
				String userRole = authInterface.getRole(userId);

				if(userRole.equals(Constants.USER_ROLE_STUDENT)){
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
			return Response.status(500).entity(e.getMessage()).build();
		}
	}
	
	
	@POST
	@Path("/updatePassword")
	public Response updatePassword(@NotNull @FormParam("email") String email,
			@NotNull @FormParam("password") String oldPassword,
		@NotNull @FormParam("password") String newPassword) {

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
	
	
	

}
