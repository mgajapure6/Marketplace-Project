package com.mp;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BackendErrorController implements ErrorController {

	@GetMapping("/error")
	public String show404Err() {
		return "error/error";
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}

}
