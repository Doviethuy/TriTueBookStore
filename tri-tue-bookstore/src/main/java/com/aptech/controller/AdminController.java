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

import com.aptech.model.Category;
import com.aptech.service.CategoryService;
import com.aptech.service.UserService;
import com.aptech.util.PermissionUtil;

@Controller
public class AdminController {
	@Autowired
	UserService userService;
	@Autowired
	CategoryService categoryService;

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
	
	@RequestMapping("/admin/san-pham")
	public String adminProduct(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (PermissionUtil.checkLogin(session)) {
			if (PermissionUtil.checkAdminRole(session)) {
				ArrayList<Category> allCategory = categoryService.getAllCategory();
				request.setAttribute("lstCate", allCategory);
				return "admin/view-san-pham";
			} else {
				return "redirect:/";
			}
		} else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/admin/add-product")
	public String addProduct(Model model, HttpServletRequest request, HttpServletResponse response) {
		return "";
	}
}
