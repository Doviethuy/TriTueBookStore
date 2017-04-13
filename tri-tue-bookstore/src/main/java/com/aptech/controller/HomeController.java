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
public class HomeController {
	@Autowired
	UserService userService;

	@RequestMapping("/")
	public String index(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (PermissionUtil.checkLogin(session)) {
			byte role = Byte.valueOf(session.getAttribute(Constant.ROLE).toString());
			if (role == Constant.ADMIN_ROLE) {
				session.setAttribute(Constant.LOGIN_SUCCESS, Constant.LOGIN_SUCCESS);
				return "redirect:/admin/report/doanh-thu";
			} else if (role == Constant.STAFF_ROLE) {
				session.setAttribute(Constant.LOGIN_SUCCESS, Constant.LOGIN_SUCCESS);
				return "redirect:/staff/ban-hang";	
			} else {
				return "login/view";
			}
		} else {
			return "login/view";
		}
	}
}