package com.fastcampus.ch21.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.URLEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/login")
public class LoginController {

	@RequestMapping("/login")
	public String showLogin() {
		return "login";
	}
	
	@PostMapping("/logins")
	public String logins(String id, String pwd, Model model) throws UnsupportedEncodingException {
		System.out.println("INPUT ============ " + id + " : " + pwd );
		if(loginCheck(id, pwd)) {
			model.addAttribute("id", id);
			model.addAttribute("pwd", pwd);
			return "userInfo";
		} else {
			String msg = URLEncoder.encode("id or pwd가 일치하지 않습니다.", "utf-8");
			return "redirect:/login/login" + "?msg=" + msg;
		}
	}

	@PostMapping("/login")
	public String login(HttpServletRequest request, String id, String pwd, RedirectAttributes model) throws UnsupportedEncodingException {
		System.out.println("INPUT ============ " + id + " : " + pwd );
		if(loginCheck(id, pwd)) {
			model.addAttribute("id", id);
			model.addAttribute("pwd", pwd);
			return "userInfo";
		} else {
			String msg = "id or pwd가 일치하지 않습니다.";
			model.addAttribute("msg", msg);
			model.addFlashAttribute("msg", "일회용 메시지");
			request.setAttribute("msg", msg);		// redirect 이기 때문에, 이 메시지는 보이지 않음

			return "redirect:/login/login";
		}
	}

	private boolean loginCheck(String id, String pwd) {
		// TODO Auto-generated method stub
		return id.equals("asdf") && pwd.equals("1234");
	}
}
