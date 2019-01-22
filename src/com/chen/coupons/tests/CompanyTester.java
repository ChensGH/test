package com.chen.coupons.tests;

import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.chen.coupons.beans.Company;
import com.chen.coupons.controllers.CompanyController;
import com.chen.coupons.dao.CompanyDao;
import com.chen.coupons.exceptions.ApplicationException;

import com.chen.coupons.utils.JdbcUtils;

public class CompanyTester {

	public static void main(String[] args) throws SQLException, InterruptedException, ApplicationException {
//		JdbcUtils.getConnection();
//		Thread.sleep(3000);
//		//Company company = new Company("chenTest", "Ss123", "soda@yahoo.com");
//		CompanyDao companyDao = new CompanyDao();
//		//companyDao.createCompany(company);
		
		//System.out.println(company.toString());
		
		//testing get company by id
		//System.out.println(companyDao.getCompanyByComapnyId(1).toString());
		
		//testing getAllCompanies
//		List<Company> companyList = new ArrayList<>();
//		companyList = companyDao.getAllCompanies();
//		System.out.println(companyList.toString());
		
		//testing the update dao
		//companyDao.updateCompanyById(4, "updateTest2", "testUpdate", "newCompanyEmail");
		//companyDao.updateCompanyName(4, "updateTest5");
		//companyDao.updateCompanyPassword(4, "newCompanyPassword");
		//companyDao.updateCompanyEmail(4, "newCompanyEmail");
		
		//testing the delete company method
		//companyDao.deleteCompanyById(7);
		
//		
//		
//	CompanyController companyController = new CompanyController();
//	
//	Company company1 = new Company("Anat2", "Company123", "chen1993@gmail.com");
//	//System.out.println(companyController.createCompany(company1));
	
	//companyController.deleteCompanyById(11);
	
	//companyController.updateCompanyEmail(13, "Anat@gmail.co.il");
	
	//companyController.updateCompanyPassword(13, "aa12345AA");
		
	
	
	
	
	
	
	
		
	}

}


