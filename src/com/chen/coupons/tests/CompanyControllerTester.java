package com.chen.coupons.tests;

import com.chen.coupons.beans.Company;
import com.chen.coupons.controllers.CompanyController;
import com.chen.coupons.exceptions.ApplicationException;

public class CompanyControllerTester {
	
		public static void main(String[] args) throws ApplicationException {

			CompanyController companyController = new CompanyController();
			Company company = null;

			//validate create company:company name
			//company = new Company ("x" , "Xiaomi8", "info@xiaomi.cn");
			//company1 = new Company(companyName, CompanyPassword, CompanyEmail)
			//companyController.createCompany(company);
			
			//validate create company: company email
			//company = new Company ("xiaomi" , "Xiaomi111", "info.xiaomi.il");
			//companyController.createCompany(company);
			
			//validate create  company: company password
			//company = new Company ("xiaomi" , "pass", "info@xiaomi.cn");
			//companyController.createCompany(company);
			
			//validate create company : all OK
			//company = new Company ("xiaomi" , "Xx123456", "info@xiaomi.cn");
			//System.out.println("id of new company: " + companyController.createCompany(company));
			
			//validate delete company: id doesnt exists
			//companyController.deleteCompany(14);
			
			//testing delete company + validate get company (with a non-existing id)
//			companyController.deleteCompany(32);
//			System.out.println(companyController.getCompany(32));
//			
			//validate update company : name invalid
			//company = companyController.getCompanyByComapnyId(16);
			//company.setCompanyName("a");
			//companyController.updateCompany(company);
			
			//validate update company : name already exists
			//company = companyController.getCompanyByComapnyId(16);
			//company.setCompanyName("orange");
			//companyController.updateCompany(company);
			
			//validate update company: email
			//company = companyController.getCompanyByComapnyId(16);
			//company.setCompanyEmail("a.a.il@");
			//companyController.updateCompany(company);
			
			//validate update company : password
			//company = companyController.getCompanyByComapnyId(16);
			//company.setCompanyPassword("1234a");
			//companyController.updateCompany(company);
			
			//validate update company : all ok
//			company = companyController.getCompanyByComapnyId(16);
//			System.out.println("before update: " + company.toString());
//			company.setCompanyName("XiaomiNew");
//			company.setCompanyEmail("coupons@gmail.com");
//			company.setCompanyPassword("newPass12");
//			companyController.updateCompany(company);
//			System.out.println("after update: " + companyController.getCompanyByComapnyId(16).toString());

			
			
			//validate get all companies
//			System.out.println(companyController.getAllCompanies());
			
			//validate login checker: invalid login
//			company = companyController.getCompany(6);
//			String newPassword = (company.getCompanyPassword() + "a");
//			System.out.println(companyController.login(company.getCompanyName(), newPassword));
			
			//validate login checker: valid login
//			company = companyController.getCompany(6);
//			System.out.println(companyController.login(company.getCompanyName(), company.getCompanyPassword()));
			

		
		
		
		
		
		
		
		
		
		

	}

}
