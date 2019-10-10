package com.mp.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("admin")
public class AdminAuthenticationController {
	
	@GetMapping("/login")
	public String showAdminLoginPage(HttpServletRequest req) {
		return "backend/admin/login/login";
	}
	
	@GetMapping("/getHello")
	public @ResponseBody String sendGetHello() {
		return "Hello";
	}
	
	@GetMapping("/ajaxHello")
	public @ResponseBody String sendAjaxHello() {
		return "Hello";
	}

}
