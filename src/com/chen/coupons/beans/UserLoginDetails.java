package com.chen.coupons.beans;

import javax.xml.bind.annotation.XmlRootElement;

import com.chen.coupons.enums.UserType;

@XmlRootElement
public class UserLoginDetails {

    private String userName;

    private String password;

    UserType userType;

	public UserLoginDetails(String userName, String password, UserType userType) {
		super();

		this.userName = userName;
		this.password = password;
		this.userType = userType;
	}
	
    public UserLoginDetails () {
    	
    }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "UserLoginDetails [userName=" + userName + ", password=" + password + ", userType=" + userType + "]";
	}
    
	
    
    
	
	
	
	
}
