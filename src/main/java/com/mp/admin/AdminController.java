package com.mp.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminController {
	
	@GetMapping("/")
	public String showPage() {
		return "backend/index/index";
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
