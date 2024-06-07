package com.codesimple.bookstore.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.codesimple.bookstore.util.JwtUtils;

@Component
public class Jwtintercepter extends HandlerInterceptorAdapter{   
	                                //HandlerInterceptorAdapter its a abstract class
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Override
	
	//if hit any request in this project first this method only check
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String auth = request.getHeader("authorization");

		if(!(request.getRequestURI().contains("login") || request.getRequestURI().contains("signup")))
		{

			jwtUtils.verify(auth);
		}
		
		
		return super.preHandle(request, response, handler);
	}
	
	
}
