package com.chen.coupons.tests;

import java.sql.Connection;
import java.sql.SQLException;

import com.chen.coupons.beans.Customer;
import com.chen.coupons.dao.CustomerDao;
import com.chen.coupons.exceptions.ApplicationException;
import com.chen.coupons.utils.JdbcUtils;

public class CustomerTester {
	
		public static void main(String[] args) throws SQLException, InterruptedException, ApplicationException {
//			JdbcUtils.getConnection();
//			Thread.sleep(3000);
//			Customer customer = new Customer("chenbye", "Ssbye1234");
//			CustomerDao customerDao = new CustomerDao();
//			customerDao.createCustomer(customer);
			
			//System.out.println(customer.toString());
			
			
			//testing the getCustomerById method
			//System.out.println(customerDao.getCustomerByCustomerId(1).toString());
			
			//testing the getAllCustomers method
			//System.out.println(customerDao.getAllCustomers());
			
			//testing the updateCustomerName method
			//customerDao.updateCustomerName(2, "newCustomerName2");
			
			//testing the updateCustomerPassword method
			//customerDao.updateCustomerPassword(1, "newCustomerPassword");
			
			//testing the deleteCustomerById method
			//customerDao.deleteCustomerById(2);
		
	}

}
