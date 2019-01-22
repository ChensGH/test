package com.chen.coupons.api;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.chen.coupons.beans.Company;
import com.chen.coupons.beans.Customer;
import com.chen.coupons.beans.UserLoginDetails;
import com.chen.coupons.controllers.CompanyController;
import com.chen.coupons.controllers.CustomerController;
import com.chen.coupons.dao.CompanyDao;
import com.chen.coupons.dao.CustomerDao;
import com.chen.coupons.enums.UserType;
import com.chen.coupons.exceptions.ApplicationException;

@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginApi {	

	@POST
	//@Consumes (MediaType.APPLICATION_JSON)
	public Response login(@Context HttpServletRequest request, @Context HttpServletResponse response, UserLoginDetails userLoginDetails) throws ApplicationException{
		
		//if the user is a company user type
		if(userLoginDetails.getUserType() == UserType.COMPANY){
			CompanyController companyController = new CompanyController();
			if (companyController.companyCheckLogin(userLoginDetails.getUserName(), userLoginDetails.getPassword()) != null){
				request.getSession(); // if the credentials are correct, get a session
				request.getSession().setMaxInactiveInterval(30*60); ////setting session to expire in 30 mins
				
				Company company = companyController.companyCheckLogin(userLoginDetails.getUserName(), userLoginDetails.getPassword());//getting the company that just loggged in
				long id = company.getCompanyId();
				//userId userId = new UserId(id); //???
				String userId =String.valueOf(id);
				Cookie idCookie = new Cookie("userId",userId);
				response.addCookie(idCookie);	
				Cookie userTypeCookie = new Cookie("userType",userLoginDetails.getUserType().name());
				response.addCookie(userTypeCookie);	
				Cookie loginCookie = new Cookie("loginStatus","success");
				//setting cookie to expire in 30 mins
				loginCookie.setMaxAge(30*60);
				response.addCookie(loginCookie);
				
				return Response.status(200).entity(id).build();
			}
			else {
				return Response.status(401).entity(null).build();
			}
		}
			
		else if(userLoginDetails.getUserType() == UserType.CUSTOMER ){
			CustomerController customerController = new CustomerController();
			if (customerController.customerCheckLogin(userLoginDetails.getUserName(), userLoginDetails.getPassword()) != null){
				request.getSession(); // if the credentials are correct, get a session.
				Customer customer = customerController.customerCheckLogin(userLoginDetails.getUserName(), userLoginDetails.getPassword());
				long id = customer.getCustomerId();
				String userId =String.valueOf(id);
				Cookie idCookie = new Cookie("userId",userId);
				response.addCookie(idCookie);
				Cookie userTypeCookie = new Cookie("userType",userLoginDetails.getUserType().name());
				response.addCookie(userTypeCookie);	
				Cookie loginCookie = new Cookie("loginStatus","success");
				//setting cookie to expire in 30 mins
				loginCookie.setMaxAge(30*60);
				response.addCookie(loginCookie);
				return Response.status(200).entity(id).build();
			}
				
				return Response.status(401).entity(null).build();
			}
			
			return Response.status(401).entity(null).build();
		}  
		

}
	


