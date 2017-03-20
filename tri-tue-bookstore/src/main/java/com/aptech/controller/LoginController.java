package com.aptech.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aptech.model.User;
import com.aptech.service.UserService;
import com.aptech.util.Constant;

@Controller
public class LoginController {
	@Autowired
	UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("userName") String userName, @RequestParam("password") String password, HttpServletRequest request) {
		User user = userService.getUser(userName);
		if (user != null) {
			if (user.getPassword().equals(password)) {
				HttpSession session = request.getSession();
				session.setAttribute(Constant.USERNAME, user.getUserName());
				session.setAttribute(Constant.FULLNAME, user.getName());
				session.setAttribute(Constant.AVARTAR, user.getImg());
				session.setAttribute(Constant.ROLE, user.getRole());
			}
		}
		return "redirect:/";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String login(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute(Constant.USERNAME);
		session.removeAttribute(Constant.ROLE);
		session.removeAttribute(Constant.AVARTAR);
		session.removeAttribute(Constant.ROLE);
		return "redirect:/";
	}
}
