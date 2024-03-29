package com.mp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class InterceptorRegistration extends WebMvcConfigurerAdapter {

	/*@Autowired
	UrlInterceptor urlInterceptor;*/
	
	@Autowired
	SubdomainInterceptor subdomainInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(subdomainInterceptor);
		//registry.addInterceptor(urlInterceptor);
	}

}
