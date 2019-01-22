package com.chen.coupons.beans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Customer {
	
	private long customerId;
	private String customerName;
	private String customerPassword;
	
	
	public Customer () {
		super ();
	}


	public Customer(String customerName, String customerPassword) {
		this.customerName = customerName;
		this.customerPassword = customerPassword;
	}


	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPassword() {
		return customerPassword;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}
	
	
	@Override
	public String toString() {
		return "\n Customer [customerId=" + customerId + ", customerName=" + customerName + ", customerPassword="
				+ customerPassword + "]";
	}
	
	
	
	
	

}
