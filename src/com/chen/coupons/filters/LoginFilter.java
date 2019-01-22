package com.chen.coupons.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import javax.ws.rs.Path;

import com.chen.coupons.utils.CookiesUtils;
import com.sun.jersey.core.reflection.AnnotatedMethod;
import com.sun.jersey.core.reflection.MethodList.Filter;


public class LoginFilter implements javax.servlet.Filter{
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		//we check if session exists
		HttpSession session = httpRequest.getSession(false);
		//if session exists or the page requested does not end with "/login",
		// then we return the reference of that session object
		String pageRequested = httpRequest.getRequestURI().toString();
				if(session != null || pageRequested.endsWith("/login") || pageRequested.endsWith("/register")){
					//adding the coockies from the request to response
					CookiesUtils.setResponseCookies(httpRequest, httpResponse);
					chain.doFilter(httpRequest,httpResponse);
					return;	
				}
		
		//if the session is null, we set the status of the request to unauthorized	
		//httpResponse.sendRedirect("http://localhost:8080/COUPONS_PROJECT_API's/invalidLogin.html");
		httpResponse.setStatus(401);
}
	
	

	@Override
	public void destroy() {
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
	
}
