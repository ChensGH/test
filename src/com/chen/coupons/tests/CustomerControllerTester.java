package com.chen.coupons.tests;

import com.chen.coupons.beans.Customer;
import com.chen.coupons.controllers.CustomerController;
import com.chen.coupons.exceptions.ApplicationException;

public class CustomerControllerTester {

	public static void main(String[] args) throws ApplicationException {
		

		CustomerController customerController = new CustomerController();
		Customer customer = null;

		
		//validate create customer:customer name is invalid
		//customer = new Customer ("m" , "maxWell11");
		//customerController.createCustomer(customer);
	
		
		//validate create customer: customer password is invalid
		//customer = new Customer ("maxim" , "maxi111");
		//customerController.createCustomer(customer);
		
		//validate create customer : all OK
		//customer = new Customer ("maxim" , "maxWell22");
		//System.out.println("id of new customer: " + customerController.createCustomer(customer));
		
		//validate delete customer: id doesnt exists
		//customerController.deleteCustomer(6);
		
		//validate delete customer + validate get customer (with a non-existing id)
//		customerController.deleteCustomer(16);
//		System.out.println(customerController.getCustomer(16));
//		
		//validate update customer : name invalid
		//customer = customerController.getCustomerByCustomerId(2);
		//customer.setCustomerName("a");
		//customerController.updateCustomer(customer);
		
		//validate update customer : name already exists
		//customer = customerController.getCustomerByCustomerId(2);
		//customer.setCustomerName("maxim");
		//customerController.updateCustomer(customer);
		
		//validate update customer : invalid password
		//customer = customerController.getCustomerByCustomerId(2);
		//customer.setCustomerPassword("customerPassword");
		//customerController.updateCustomer(customer);
		
		//validate update customer : all ok
		customer = customerController.getCustomerByCustomerId(2);
		System.out.println("before update: " + customer.toString());
		customer.setCustomerName("test chen");
		customer.setCustomerPassword("newPass15");
		customerController.updateCustomer(customer);
		System.out.println("after update: " + customerController.getCustomerByCustomerId(2).toString());
			
		//validate get all customers
//		System.out.println(customerController.getAllCustomers());
		
		//validate login checker: invalid login
//		customer = customerController.getCustomer(2);
//		String newPassword = (customer.getCustomerPassword() + "a");
//		System.out.println(customerController.login(customer.getCustomerName(), newPassword));
		
		//validate login checker: valid login
//		customer = customerController.getCustomer(2);
//		System.out.println(customerController.login(customer.getCustomerName(), customer.getCustomerPassword()));

		
		
		
		
		
		
		
		
		
		
		

	}

}
