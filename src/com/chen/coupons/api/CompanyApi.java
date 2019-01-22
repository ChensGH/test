package com.chen.coupons.api;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.chen.coupons.beans.Company;
import com.chen.coupons.beans.Customer;
import com.chen.coupons.controllers.CompanyController;
import com.chen.coupons.controllers.CustomerController;
import com.chen.coupons.exceptions.ApplicationException;
import com.chen.coupons.utils.CookiesUtils;

@Path("/Companies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CompanyApi {

	private CompanyController companyController ;
	
	//constructor
	public CompanyApi () {
		 this.companyController = new CompanyController();
				 
	}
	
	
	@POST
	//http://localhost:8080/CouponsProjectAPI/rest/Companies/
	public void createCompany (Company company) throws ApplicationException{
		System.out.println(this.companyController.createCompany(company)); 
	}
	
	@GET
	@Path("/{companyId}/byId")
	//http://localhost:8080/CouponsProjectAPI/rest/Companies/1/byId
	public Company getCompanyById(@PathParam("companyId") long companyId)throws ApplicationException{
		 return this.companyController.getCompanyByComapnyId(companyId);			
	}
	
	@DELETE
	@Path("/{companyId}")
	//http://localhost:8080/CouponsProjectAPI/rest/Companies/15
	public void deleteCompany(@PathParam("companyId") long companyId)throws ApplicationException{
		this.companyController.deleteCompany(companyId);
	}
	

//	 @DELETE
//	public void deleteCompany (@Context HttpServletRequest request) throws ApplicationException {
//			
//		Cookie IdCookie= CookiesUtils.getCookie(request, "userId");		
//		long companyId = Long.parseLong(IdCookie.getValue());	
//		
//		 this.companyController.deleteCompany(companyId);
//	 }

	
	@PUT
	//http://localhost:8080/CouponsProjectAPI/rest/Companies/
	public void updateCompany(Company company) throws ApplicationException{
		this.companyController.updateCompany(company);
	}
	
	
	@GET
	//http://localhost:8080/CouponsProjectAPI/rest/Companies/
	public List<Company> getAllCompanies()throws ApplicationException{
		 return this.companyController.getAllCompanies();			
	}
	

	
	
	
	
	
	
}
