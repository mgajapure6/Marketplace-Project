package com.mp.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class SubdomainInterceptor implements HandlerInterceptor {

    @Value("${security.csrf-name}")
    private String csrfName;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	    throws Exception {
    	
    	String subdomain = request.getServerName();
    	System.out.println("path domin::"+subdomain);
    	if(subdomain.contains(".")) {
    		String redirectPath = null;
    		subdomain = subdomain.split("\\.")[0];
    		String hSubdomain = request.getHeader("subdomain");
    		
    		System.out.println("hSubdomain::"+hSubdomain);
    		
    		request.setAttribute("subdomain::", subdomain);
    		System.out.println("request.getServletPath()::"+request.getServletPath());
    		
    		if(hSubdomain==null || !subdomain.equals(hSubdomain)) {
    			System.out.println("not eq");
    			redirectPath = subdomain+"/login";
        		//return true;
    		}else if(subdomain.equals(hSubdomain)){
    			System.out.println("eq");
    			redirectPath = subdomain+request.getServletPath();
    		}
    		//request.getRequestDispatcher("/error").forward(request, response);
    		System.out.println("redirectPath::"+redirectPath);
    		//request.getRequestDispatcher(redirectPath).forward(request, response);
    		response.sendRedirect(redirectPath);
    		return false;
    	}else {
    		response.sendRedirect("/error");
    		return false;
    	}
    	

    	
    	
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
	    ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
	    Exception exception) throws Exception {
    }

}
