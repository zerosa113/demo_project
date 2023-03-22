package com.example.demo_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DemoController {
	
	@RequestMapping(value = "/hello")
	public String hello(@RequestParam(name ="name", required = false, defaultValue = "World")String name, Model model) {
		model.addAttribute("name", name);
		return "hello.html";		//html page的命名，回傳整個html
	}
}
