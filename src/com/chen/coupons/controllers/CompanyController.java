package com.chen.coupons.controllers;

import java.util.List;

import com.chen.coupons.beans.Company;
import com.chen.coupons.beans.Coupon;
import com.chen.coupons.dao.CompanyDao;
import com.chen.coupons.dao.CouponDao;
import com.chen.coupons.dao.CustomerDao;
import com.chen.coupons.enums.ErrorType;
import com.chen.coupons.exceptions.ApplicationException;
import com.chen.coupons.utils.DateUtils;
import com.chen.coupons.utils.LoginValidationUtils;

public class CompanyController {
	
	

	private CouponDao couponDao;
	private CompanyDao companyDao;
	private CustomerDao customerDao;

	public CompanyController () {
		this.couponDao=new CouponDao();
		this.companyDao=new CompanyDao();
		this.customerDao=new CustomerDao();
	}
	
	
	
	public Company getCompanyByComapnyId (long companyId) throws ApplicationException {
		//validating that the company id exists in the DB
	
		validateCompanyId(companyId);

		
		//If we didn't catch any exception, we call the 'getCompanyByComapnyId' method.
		return companyDao.getCompanyByComapnyId(companyId);
		
	}
	
	
	//getAllCompanies
	public List<Company> getAllCompanies () throws ApplicationException {
		
		validateGetAllCompanies();		
		//If we didn't catch any exception, we call the 'getAllCompanies' method.
		return companyDao.getAllCompanies();
		
	}
		
	
	public long createCompany(Company company)throws ApplicationException{
			
		try {
			validateCreateCompany(company);
		}
		catch (ApplicationException e){
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR, "failed to create company");
		}
		
		//If we didn't catch any exception, we call the 'createCompany' method.
		return this.companyDao.createCompany(company);

	}
	
	
	public void deleteCompany(long companyId) throws ApplicationException {
		
		//Validating that the company id exists
		validateCompanyId(companyId);
		//If we didn't catch any exception, we call the 'deleteCompanyById' method.
		this.companyDao.deleteCompanyById(companyId);
		
	}
	
	public void updateCompany(Company company) throws ApplicationException{
		
		validateUpdateCompany(company);
		
		//If we didn't catch any exception, we call the 'updateCompany' method.
		this.companyDao.updateCompany(company);;		
	}
	

	
public Company companyCheckLogin (String companyName, String password) throws ApplicationException{
	
	validateLogin(companyName, password);
	return this.companyDao.companyCheckLogin(companyName, password);
	
}

	
	
	
	// --validation methods--
	
	
	// * utility method: validating that company Id exists in the db 
	private void validateCompanyId (long companyId) throws ApplicationException{
	
		if (!this.companyDao.doesCompanyExistById(companyId)) {
			throw new ApplicationException(ErrorType.INVALID_PARAMETER, "companyId doesnt exist.");
		}		
	}
	
	
	// * utility method: validating that company name exists in the db 
		private void validateNewCompanyName (String companyName) throws ApplicationException{
			
			//We check if the company's name is already exist in the DB
			if (this.companyDao.doesCompanyExistByName(companyName)) {
				throw new ApplicationException(ErrorType.INVALID_PARAMETER, ("the comapny name you entered already exists. " + DateUtils.getCurrentDateAndTime()));
			}	
			
			//we check if the name is valid
			if (companyName.length()<2 || companyName.length()>50) {
				throw new ApplicationException(ErrorType.INVALID_PARAMETER, "company's name is either too short or too long. companys name must be between 2 and 50 characters.");
			}	
			
		}
	
	
	
	
	//validating the createCompany method
	private void validateCreateCompany(Company company) throws ApplicationException{
		
		//we check if the name is valid
		validateNewCompanyName(company.getCompanyName());
		
			//we check if the email is valid	
		if(! LoginValidationUtils.emailValidation(company.getCompanyEmail())) {
			throw new ApplicationException(ErrorType.INVALID_PARAMETER, ("creation of company failed: invalid email adress. " + DateUtils.getCurrentDateAndTime()));
		}
		//we check if the password is valid
		if(! LoginValidationUtils.passwordValidation(company.getCompanyPassword())) {
			throw new ApplicationException(ErrorType.INVALID_PARAMETER, ("creation of company failed: invalid password. " + DateUtils.getCurrentDateAndTime()));		
		}
			
			
	}
	
	//validating the createCompany method
		private void validateUpdateCompany(Company company) throws ApplicationException{
			
			long companyId = company.getCompanyId();
			
			//we check id the company id exists in the DB
			validateCompanyId(companyId);
			
			Company beforeCompany = this.companyDao.getCompanyByComapnyId(companyId);
			String oldCompanyName = beforeCompany.getCompanyName();
			String companyNewName = company.getCompanyName();
			String oldCompanyEmail = beforeCompany.getCompanyEmail();
			String newCompanyEmail = company.getCompanyEmail();
			
			//we check if the name is changed
			if(! oldCompanyName.equals(companyNewName)) {
				//if yes, then validate the name
				validateNewCompanyName(companyNewName);
			}	
			
			if(! oldCompanyEmail.equals(newCompanyEmail)) {
				//if true, then we check if the email is valid	
				if(! LoginValidationUtils.emailValidation(company.getCompanyEmail())) {
					throw new ApplicationException(ErrorType.INVALID_PARAMETER, ("creation of company failed: invalid email adress. " + DateUtils.getCurrentDateAndTime()));
				}
			}
			
			//we check if the password is valid
			if(! LoginValidationUtils.passwordValidation(company.getCompanyPassword())) {
				throw new ApplicationException(ErrorType.INVALID_PARAMETER, ("creation of company failed: invalid password. " + DateUtils.getCurrentDateAndTime()));		
			}
				
				
		}
		
	
		
	
		private void validateGetAllCompanies() throws ApplicationException {
			//checking if the list is empty
			if(companyDao.getAllCompanies()==null) {		
				throw new ApplicationException(ErrorType.Empty_List, "The list of companies is empty, validateGetAllCompanies() " + DateUtils.getCurrentDateAndTime());
			}
		
		}
	
	
	
		private void validateLogin(String companyName, String password) throws ApplicationException{
			if(companyDao.companyCheckLogin(companyName, password) == null) {
				throw new ApplicationException(ErrorType.INVALID_LOGIN, "invalid login,user name and password of company do not match, validateLogin() " + DateUtils.getCurrentDateAndTime());
			}
		} 
	
	
	
	
	
	
	
	
	
	

}
