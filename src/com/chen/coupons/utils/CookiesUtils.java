package com.chen.coupons.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chen.coupons.beans.Company;

public class CookiesUtils {

	//adding the request cookies to the response
	public static HttpServletResponse setResponseCookies(HttpServletRequest request,HttpServletResponse response) {
		
		Cookie cookies [] = request.getCookies();	
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				response.addCookie(cookie);
			}		
		}
		
		return response;		
	}
	
	
	public static Cookie getCookie(HttpServletRequest request, String name) {

		 Cookie[] cookies = request.getCookies();
		 
			if (cookies != null) { 
				for (Cookie cookie : cookies) {
				           if (cookie.getName().equals(name)) {
				                return cookie;
				            }
				   }
		  }
		 return null;
		}


	
	

}
