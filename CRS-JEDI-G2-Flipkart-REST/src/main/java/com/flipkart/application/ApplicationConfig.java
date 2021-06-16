/**
 * 
 */
package com.flipkart.application;

import org.glassfish.jersey.server.ResourceConfig;

import com.flipkart.restController.AdminRestAPI;
import com.flipkart.restController.CustomerRestAPI;
import com.flipkart.restController.StudentRestAPI;
import com.flipkart.restController.ProfessorRestAPI;
import com.flipkart.restController.UserRestAPI;

/**
 * @author aysh
 *
 */
public class ApplicationConfig extends ResourceConfig {
	
	public ApplicationConfig () {
//		Register all the services here
		register(CustomerRestAPI.class);
		register(UserRestAPI.class);
		register(StudentRestAPI.class);
		register(ProfessorRestAPI.class);
		register(AdminRestAPI.class);
	}

}
