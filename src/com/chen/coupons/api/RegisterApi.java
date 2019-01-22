package com.chen.coupons.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.chen.coupons.beans.Customer;
import com.chen.coupons.controllers.CustomerController;
import com.chen.coupons.exceptions.ApplicationException;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/register")
public class RegisterApi {
	

	public RegisterApi() {
	}

	CustomerController customerController = new CustomerController();


	@POST
	@Consumes (MediaType.APPLICATION_JSON)
	public Response register (@Context HttpServletRequest request, @Context HttpServletResponse response, Customer customer) throws ApplicationException, ServletException, IOException, InterruptedException {
		
		System.out.println(customer.toString());
		Long customerId =null;
		try {
		customerId = this.customerController.createCustomer(customer);
		System.out.println("a new customer was registerd: id# " + customerId);
		}catch (ApplicationException e) {
			e.printStackTrace();
			return Response.status(401).header("exception", e.getMessage()).build();
			
		}
		if (!customerId.equals(null)) {
			return Response.status(200).header("customerId", customerId.toString()).build();
		}
		else {
			return Response.status(403).header("customerId", null).build();
		}
		
	
	}
		
	
	}
		
	
	
	
	
	
	
	
	
	


