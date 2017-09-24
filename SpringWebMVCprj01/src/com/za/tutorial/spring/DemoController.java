package com.za.tutorial.spring;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DemoController {
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String displayMessage(ModelMap model) {
		System.out.println("ahojkz");
		model.addAttribute("thankYouMessage", "Hello Spring MVC Framework!");		
		return "default";
	}
}
