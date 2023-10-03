package com.fastcampus.ch21.controller;

import java.util.Arrays;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String main() {
		return "index";
	}
	
	@GetMapping("/utext")
	public void utext(Model model, HttpServletRequest request) {
		model.addAttribute("lastName", "StringTest");
		model.addAttribute("rawString", "<i>String Test</i>");
		model.addAttribute("list", Arrays.asList("aaa", "bbb", "ccc"));
		model.addAttribute("lists", Arrays.asList());
		
		request.setAttribute("year", 2023);
		
		HttpSession session = request.getSession();
		session.setAttribute("month", 9);
		
		ServletContext appContext = session.getServletContext();
		appContext.setAttribute("day", 30);
		
//		return "utext";
	}
	
}
