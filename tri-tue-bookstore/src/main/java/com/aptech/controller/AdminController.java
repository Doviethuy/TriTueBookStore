package com.aptech.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aptech.service.UserService;
import com.aptech.util.Constant;
import com.aptech.util.PermissionUtil;

@Controller
public class AdminController {
	@Autowired
	UserService userService;

	@RequestMapping("/admin/report/doanh-thu")
	public String index(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (PermissionUtil.checkLogin(session)) {
			if (PermissionUtil.checkAdminRole(session)) {
				return "admin/view-quan-ly";
			} else {
				return "redirect:/";
			}
		} else {
			return "redirect:/";
		}
	}
}
