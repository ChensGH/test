package com.chen.coupons.beans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Company 
{
	private long companyId;
	private String companyName;
	private String CompanyPassword;
	private String CompanyEmail;

	public Company() {
		super();
	}

	public Company(String companyName, String CompanyPassword, String CompanyEmail) {
		super();
		this.companyName = companyName;
		this.CompanyPassword = CompanyPassword;
		this.CompanyEmail = CompanyEmail;
	}

	public Company(long id, String companyName, String CompanyPassword, String CompanyEmail) {
		super();
		this.companyId = id;
		this.companyName = companyName;
		this.CompanyPassword = CompanyPassword;
		this.CompanyEmail = CompanyEmail;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long id) {
		this.companyId = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyPassword() {
		return CompanyPassword;
	}

	public void setCompanyPassword(String CompanyPassword) {
		this.CompanyPassword = CompanyPassword;
	}
	
	public String getCompanyEmail() {
		return CompanyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		CompanyEmail = companyEmail;
	}
	
	
	
	
	@Override
	public String toString() {
		return "\n [Company id : " + companyId + ", Company name : " + companyName + ", Company password : " + CompanyPassword
				+ ", Company email : " + CompanyEmail + "]";
	}
}