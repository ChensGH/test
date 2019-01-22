package com.chen.coupons.tests;

import com.chen.coupons.beans.Company;
import com.chen.coupons.beans.Coupon;
import com.chen.coupons.controllers.CompanyController;
import com.chen.coupons.controllers.CouponController;
import com.chen.coupons.controllers.CustomerController;
import com.chen.coupons.dao.CouponDao;
import com.chen.coupons.enums.CouponType;
import com.chen.coupons.exceptions.ApplicationException;

public class Tester {

	public static void main(String[] args) throws ApplicationException{
		
//		CouponController couponController = new CouponController();
//		CustomerController customerController = new CustomerController();
//		CompanyController companyContoller = new CompanyController();

		
		//System.out.println(couponController.getActiveCouponsByCompanyId(1));
		
		//System.out.println(couponController.getCouponById(20));
		
		
		//couponController.getAllCoupons();
		
		//Coupon coupon = new Coupon("test", "2018-10-20", "2019-10-20", 100, CouponType.Food, "message", 150, "image", 9);
		//couponController.createCoupon(coupon);
		
		//companyContoller.deleteCompanyById(9);
		
		//testing create validation - name already exists
//		Company company = new Company("pepsi", "Aa123456", "chr@df.df");
//		companyContoller.createCompany(company);
		
//		//testing create validation - name not valid
//		Company company = new Company("p", "Aa123456", "chr@df.df");
//		companyContoller.createCompany(company);
		
		
		//testing create validation - password not valid
//		Company company = new Company("pepsichen", "a123456", "chr@df.df");
//		companyContoller.createCompany(company);
		
		//testing create validation - mail not valid
//		Company company = new Company("pepsichen", "Ba123456", "chrdf.df");
//		companyContoller.createCompany(company);
		
		//testing create company -
	//Company company = new Company("pepsichen", "Ba123456", "chr@df.df");
//		companyContoller.createCompany(company);

		//creating a new coupon (with the company id you just created) for the purchase
		//Coupon coupon = new Coupon("test", "2018-10-20", "2019-10-20", 100, CouponType.Food, "message", 30, "image", 14);
		//couponController.createCoupon(coupon);
		
		//purchase
		//couponController.purchaseCoupon(4, 22, 1);
	
		//testing delete company
		//companyContoller.deleteCompanyById(14);
	
	
	
		
		//update company mail
//		Company company = companyContoller.getCompanyByComapnyId(12);
//		company.setCompanyEmail("chen@gmail.com");
//		companyContoller.updateCompany(company);
		
		//System.out.println(customerController.getAllCustomers());
				
		//CouponDao cd = new CouponDao();
		//System.out.println(cd.isCouponExistById(20));
		
		
		//System.out.println(couponController.getActiveCouponsByCompanyId(2));
		
		//System.out.println(couponController.getAllActiveCoupons());
		
		//System.out.println(customerController.customerCheckLogin("apitest", "Aa5432187"));
		
	}

}
