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
import com.aptech.util.PermissionUtil;

@Controller
public class LoginController {
	@Autowired
	UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("userName") String userName, @RequestParam("password") String password, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = userService.getUser(userName);
		if (user != null) {
			if (user.getPassword().equals(PermissionUtil.toMd5(password))) {
				session.setAttribute(Constant.USERNAME, user.getUserName());
				session.setAttribute(Constant.FULLNAME, user.getName());
				session.setAttribute(Constant.AVARTAR, user.getImg());
				session.setAttribute(Constant.ROLE, user.getRole());
				session.setAttribute(Constant.LOGIN_SUCCESS, Constant.LOGIN_SUCCESS);
			} else {
				session.setAttribute(Constant.LOGIN_FAIL, Constant.LOGIN_FAIL);
			}
		}else {
			session.setAttribute(Constant.LOGIN_FAIL, Constant.LOGIN_FAIL);
		}
		return "redirect:/";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String login(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		/*session.removeAttribute(Constant.USERNAME);
		session.removeAttribute(Constant.ROLE);
		session.removeAttribute(Constant.AVARTAR);
		session.removeAttribute(Constant.ROLE);*/
		return "redirect:/";
	}

	@RequestMapping(value = "/info")
	public String info(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(PermissionUtil.checkLogin(session)){
			User user = userService.getUser(session.getAttribute(Constant.USERNAME).toString());
			request.setAttribute("user", user);
			return "staff/view-info";
		}else{
			return "redirect:/";
		}
	}

	@RequestMapping(value = "/pass")
	public String pass(HttpServletRequest request) {
		return "login/change-pass";
	}
	@RequestMapping(value = "/changePass", method = RequestMethod.POST)
	public String changePass(@RequestParam("userName") String userName, @RequestParam("passOld") String passOld, @RequestParam("passNew") String passNew, HttpServletRequest request) {
		User user = userService.getUser(userName);
		if (user != null) {
			if (user.getPassword().equals(passOld)&&passNew!="") {
				user.setPassword(passNew);
				userService.updateUser(user);
				request.setAttribute("status", "Change password successful!");
			}else{
				request.setAttribute("status", "Wrong password!");
			}
		}else{
			request.setAttribute("status", "Wrong user name!");
		}
		return "login/change-result";
	}

	@RequestMapping(value = "/adminInfo")
	public String adminInfo(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(PermissionUtil.checkLogin(session)){
			if(PermissionUtil.checkAdminRole(session)){
				User user = userService.getUser(session.getAttribute(Constant.USERNAME).toString());
				request.setAttribute("user", user);
				return "admin/view-admin-info";
			}else{
				return "redirect:/";
			}
		}else{
			return "redirect:/";
		}
	}
}
