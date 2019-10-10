package com.mp.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class UrlInterceptor implements HandlerInterceptor {

    @Value("${security.csrf-name}")
    private String csrfName;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	    throws Exception {

	if (request.getServletPath().equals("/")) {
	    return true;
	}

//	if (request.getHeader(csrfName) == null || request.getHeader(csrfName).isEmpty()) {
//	    System.out.println("err path::" + request.getServletPath());
//	    request.getRequestDispatcher("/error").forward(request, response);
//	    return false;
//	}

	if (request.getServletPath().startsWith("/backend/") || request.getServletPath().startsWith("/error")) {
	    return true;
	} else {
	    System.out.println("err path::" + request.getServletPath());
	    request.getRequestDispatcher("/error").forward(request, response);
	    return false;
	}

	// return true;

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
