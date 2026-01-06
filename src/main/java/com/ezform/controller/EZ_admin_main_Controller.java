package com.ezform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/ez_admin")
public class EZ_admin_main_Controller {

	@RequestMapping(value = "/main", method=RequestMethod.GET)
	public String AdminMainPage() throws Exception {
		
		
		return "/ez_admin/main";
	}
	
	@RequestMapping(value = "/login", method=RequestMethod.GET)
	public String AdminLoginPage() throws Exception {
		
		
		return "/ez_admin/login";
	}
	
}
