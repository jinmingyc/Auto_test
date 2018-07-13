package com.ycm.springmvc01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class HelloWorld {

	@RequestMapping("/hello")
	public String SayHi(Model model){
		model.addAttribute("message","Hello Spring MVC");
		return "hello";
	}
}
