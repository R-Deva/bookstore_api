package com.codesimple.bookstore.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.codesimple.bookstore.config.Jwtintercepter;

@Configuration
public class CustomWebConfig implements WebMvcConfigurer {

	@Autowired
	private Jwtintercepter jwtintercepter;
	
	public  void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		
		PageableHandlerMethodArgumentResolver pageResolver = new PageableHandlerMethodArgumentResolver();
		pageResolver.setPageParameterName("page-number");
		pageResolver.setSizeParameterName("page-size");
		pageResolver.setOneIndexedParameters(true);
		
		PageRequest defaultPageable = PageRequest.of(0, 5);
		pageResolver.setFallbackPageable(defaultPageable);
		argumentResolvers.add(pageResolver);
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	
		registry.addInterceptor(jwtintercepter);
		

	}
}
