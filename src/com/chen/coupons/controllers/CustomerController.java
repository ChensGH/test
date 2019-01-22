package com.chen.coupons.controllers;

import java.sql.SQLException;
import java.util.List;

import com.chen.coupons.beans.Customer;
import com.chen.coupons.dao.CompanyDao;
import com.chen.coupons.dao.CouponDao;
import com.chen.coupons.dao.CustomerDao;
import com.chen.coupons.enums.ErrorType;
import com.chen.coupons.exceptions.ApplicationException;
import com.chen.coupons.utils.DateUtils;
import com.chen.coupons.utils.LoginValidationUtils;

public class CustomerController {
	
	

	private CouponDao couponDao;
	private CompanyDao companyDao;
	private CustomerDao customerDao;

	public CustomerController () {
		this.couponDao=new CouponDao();
		this.companyDao=new CompanyDao();
		this.customerDao=new CustomerDao();
	}
	

	public Customer getCustomerByCustomerId (long customerId) throws ApplicationException {
		//validating that the customer id exists in the DB
	
		validateCustomerId(customerId);

		
		//If we didn't catch any exception, we call the 'getCustomerByComapnyId' method.
		return customerDao.getCustomerByCustomerId(customerId);
		
	}
	
	
	//getAllCompanies
	public List<Customer> getAllCustomers () throws ApplicationException {
		
		validateGetAllCustomers();		
		//If we didn't catch any exception, we call the 'getAllCompanies' method.
		return this.customerDao.getAllCustomers();
		
	}
		
	
	public long createCustomer(Customer customer)throws ApplicationException{
			
		try {
			validateCreateCustomer(customer);
		}
		catch (ApplicationException e){
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR, "failed to create customer");
		}
		
		//If we didn't catch any exception, we call the 'createCustomer' method.
		return this.customerDao.createCustomer(customer);

	}
	
	
	public void deleteCustomer(long customerId) throws ApplicationException {
		
		//Validating that the customer id exists
		validateCustomerId(customerId);
		//If we didn't catch any exception, we call the 'deleteCustomerById' method.
		this.customerDao.deleteCustomer(customerId);
		
	}
	

public Customer customerCheckLogin (String customerName, String password) throws ApplicationException{
		
		validateLogin(customerName, password);
		return this.customerDao.customerCheckLogin(customerName, password);
	
	}

public void updateCustomer (Customer customer) throws ApplicationException{
	//we check id the customer id exists in the DB
	validateCustomerId(customer.getCustomerId());
	
	//we check validation for the update fields
	validateUpdateCustomer(customer);
	
	//if we didn't catch any exception we call the updateCustomer method from the dao
	this.customerDao.updateCustomer(customer);
}

	
	
	
	// --validation methods--
	
	
	// * utility method: validating that customer Id exists in the db 
	private void validateCustomerId (long customerId) throws ApplicationException{
	
		if (!this.customerDao.doesCustomerExistById(customerId)) {
			throw new ApplicationException(ErrorType.INVALID_PARAMETER, "customerId doesnt exist.");
		}		
	}
	
	
	// * utility method: validating that customer name exists in the db 
		private void validateCustomerName (String customerName) throws ApplicationException{
			
			//We check if the customer's name is already exist in the DB
			if (this.customerDao.doesCustomerExistByName(customerName)) {
				throw new ApplicationException(ErrorType.INVALID_PARAMETER, ("the customer name you entered already exists. " + DateUtils.getCurrentDateAndTime()));
			}	
			
			//we check if the name is valid
			if (customerName.length()<2 || customerName.length()>50) {
				throw new ApplicationException(ErrorType.INVALID_PARAMETER, "customer's name is either too short or too long. customers name must be between 2 and 50 characters.");
			}	
			
		}
	
	
	//validating the createCustomer method
	private void validateCreateCustomer(Customer customer) throws ApplicationException{
		
		//we check if the name is valid
		validateCustomerName(customer.getCustomerName());
		
		//we check if the password is valid
		if(! LoginValidationUtils.passwordValidation(customer.getCustomerPassword())) {
			throw new ApplicationException(ErrorType.INVALID_PARAMETER, ("creation of customer failed: invalid password. " + DateUtils.getCurrentDateAndTime()));		
		}
			
			
		}
	
	
	private void validateUpdateCustomer(Customer customer) throws ApplicationException{
		
		long id = customer.getCustomerId();
		Customer besforeUpdate = this.getCustomerByCustomerId(id);
		String oldName = besforeUpdate.getCustomerName();
		String newName = customer.getCustomerName();
		String oldPassword = besforeUpdate.getCustomerPassword();
		String newPassword = customer.getCustomerPassword();
		
		//we check if the name is changed
		if(! oldName.equals(newName)) {
			//if yes, then validate the name
			validateCustomerName(newName);
		}	
			
		//we check if the password is changed
		if (! oldPassword.equals(newPassword)) {
			//if yes, then validate the new password
			if(! LoginValidationUtils.passwordValidation(newPassword)) {
				throw new ApplicationException(ErrorType.INVALID_PARAMETER, ("Update of customer failed: invalid password : " + newPassword + DateUtils.getCurrentDateAndTime()));		
			}
		}
		
		
		
		
	}
	
	
	
	
		private void validateGetAllCustomers() throws ApplicationException {
			//checking if the list is empty
			if(customerDao.getAllCustomers()==null) {		
				throw new ApplicationException(ErrorType.Empty_List, "The list of customers is empty, validateGetAllCompanies() " + DateUtils.getCurrentDateAndTime());
			}
		
		}
	
	
	
		private void validateLogin(String customerName, String password) throws ApplicationException{
			if(customerDao.customerCheckLogin(customerName, password) == null) {
				throw new ApplicationException(ErrorType.INVALID_LOGIN, "invalid login,user name and password do not match, validateLogin() " + DateUtils.getCurrentDateAndTime());
			}
		}
	

	
	
	
	
	
	
	
	
	

}
