package com.aptech.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aptech.model.User;
import com.aptech.service.UserService;
import com.aptech.util.Constant;

@Controller
public class HomeController {
	@Autowired
	UserService userService;

	@RequestMapping("/")
	public String index(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (checkLogin(session)) {
			byte role = Byte.valueOf(session.getAttribute(Constant.ROLE).toString());
			if (role == Constant.ADMIN_ROLE) {
				return "all-user";
			} else if (role == Constant.STAFF_ROLE) {
				return "all-user";	
			} else {
				return "login/view";
			}
		} else {
			return "login/view";
		}
	}

	@RequestMapping(value = "/get-all-user")
	public String getAllUser(Model model) {
		ArrayList<User> list = userService.getAllUser();
		model.addAttribute("user", new User());
		model.addAttribute("lstUser", list);
		for (User user : list) {
			System.out.println(user.getAddress());
		}
		return "all-user";
	}

	private boolean checkLogin(HttpSession session) {
		try {
			String userName = session.getAttribute(Constant.USERNAME).toString();
			if (userName == null || userName.equals("")) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
	}
}