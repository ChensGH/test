package com.chen.coupons.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.chen.coupons.beans.Customer;
import com.chen.coupons.controllers.CustomerController;
import com.chen.coupons.exceptions.ApplicationException;

@Path("/Customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerApi {
	
	//private CustomerController customerController = new CustomerController();
	
	private CustomerController customerController;
	
	//constructor
	public CustomerApi () {
		 this.customerController = new CustomerController();
	}
	
	
	
	@POST
	//http://localhost:8080/CouponsProjectAPI/rest/Customers/
	public void creatCustomer (Customer customer) throws ApplicationException{
		System.out.println(this.customerController.createCustomer(customer)); 
	}
	
	@GET
	@Path("/{customerId}/byId")
	//http://localhost:8080/CouponsProjectAPI/rest/Customers/6/byId
	public Customer getCustumerById(@PathParam("customerId") long customerId)throws ApplicationException{
		 return this.customerController.getCustomerByCustomerId(customerId);			
	}
	
	@DELETE
	@Path("/{customerId}")
	//http://localhost:8080/CouponsProjectAPI/rest/Customers/6/
	public void deleteCustomer(@PathParam("customerId") long customerId)throws ApplicationException{
		this.customerController.deleteCustomer(customerId);
	}
	
	@PUT
	//http://localhost:8080/CouponsProjectAPI/rest/Customers/
	public void updateCustomer(Customer customer) throws ApplicationException{
		this.customerController.updateCustomer(customer);
	}
	
	
	@GET
	//http://localhost:8080/CouponsProjectAPI/rest/Customers/
	public List<Customer> getAllCustumer()throws ApplicationException{
		 return this.customerController.getAllCustomers();			
	}
	

	
	
	
	
	
	
	

}
