package com.aptech.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.aptech.model.User;
import com.aptech.service.UserService;
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
	
	@RequestMapping("/admin/report/san-pham")
	public String reportProduct(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (PermissionUtil.checkLogin(session)) {
			if (PermissionUtil.checkAdminRole(session)) {
				return "admin/view-hang-hoa";
			} else {
				return "redirect:/";
			}
		} else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/admin/nhan-vien")
	public String manageUser(Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		if(PermissionUtil.checkLogin(session)){
			if(PermissionUtil.checkAdminRole(session)){
				ArrayList<User> allUser = userService.getAllUser();
				request.setAttribute("lstUser", allUser);
				return "admin/view-nhan-vien";
			} else {
				return "redirect:/";
			}
		} else {
			return "redirect:/";
		}
	}
	
	@RequestMapping(value="/admin/add-user", method = RequestMethod.POST)
	public String addUser(@RequestParam("userName")String userName,@RequestParam("passWord")String passWord, HttpServletRequest request, HttpServletResponse response){
		System.out.println("userName"+userName);
		System.out.println("passWord"+passWord);
		return "";
	}
}
