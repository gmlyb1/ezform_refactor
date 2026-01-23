package com.ezform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ez_error")
public class EZ_error_Controller {

	@RequestMapping("/404")
	public String error404() throws Exception{
		
		return "/ez_error/404";
	}
	
	@RequestMapping("/403")
	public String error403() throws Exception{
		
		return "/ez_error/403";
	}
	
	@RequestMapping("/500")
	public String error500() throws Exception{
		
		return "/ez_error/500";
	}
	
	
}
