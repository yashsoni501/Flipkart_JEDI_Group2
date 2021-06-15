/**
 * 
 */
package com.flipkart.application;

import org.glassfish.jersey.server.ResourceConfig;

import com.flipkart.restController.CustomerRestAPI;

/**
 * @author aysh
 *
 */
public class ApplicationConfig extends ResourceConfig {
	
	public ApplicationConfig () {
//		Register all the services here
		register(CustomerRestAPI.class);
	}

}
