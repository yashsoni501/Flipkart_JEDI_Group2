/**
 * 
 */
package com.flipkart.restController;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.flipkart.bean.Customer;

/**
 * @author aysh
 *
 */

@Path("/customerApi")
public class CustomerRestAPI {

	@GET
	@Path("/customer")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer getCustomerDetails() {

		// clinet --- service ---- dao ----> SQL

		Customer customer = new Customer();
		customer.setCustomerId(101);
		customer.setCustomerName("Flipcard");
		customer.setCustomerAddress("mumbai");

		return customer;

	}

	@POST
	@Path("/post")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createCustomer(Customer customer) {
		System.out.println("hit post service");

		System.out.println("value of customerId from UI " + customer.getCustomerId());
		System.out.println("value of customerName from UI " + customer.getCustomerName());

//        Putting customer details in service

		String result = "Customer saved : " + customer;

		return Response.status(201).entity(result).build();

	}

	@PUT
	@Path("/update")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer updateCustomer(Customer customer) {

		// call the update service call

		customer.setCustomerName(customer.getCustomerName() + " updated");
//		customer.setCustomerName(customer.getCustomerName() +" updated");
		return customer;

	}

}
