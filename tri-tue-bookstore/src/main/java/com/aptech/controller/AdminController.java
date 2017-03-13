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
import com.aptech.model.User;
import com.aptech.service.CategoryService;
import com.aptech.service.UserService;
import com.aptech.util.PermissionUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.util.PortalUtil;

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
	
	@RequestMapping("/admin/nhan-vien")
	public String adminUser(Model model, HttpServletRequest request){
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
	
	@RequestMapping(value="/admin/add-product", method = RequestMethod.POST)
	public String addProduct(@RequestParam("proName")String proName, @RequestParam("cateId")long cateId, @RequestParam("price") long price,@RequestParam("description") String description, HttpServletRequest request, HttpServletResponse response) {
			
		System.out.println("proName:"+proName);
		System.out.println("cateId:"+cateId);
		System.out.println("price:"+price);
		System.out.println("description"+description);
		return "";
	}
	
	@RequestMapping(value="/admin/add-user", method = RequestMethod.POST)
	public String addUser(@RequestParam("userName")String userName,@RequestParam("passWord")String passWord, HttpServletRequest request, HttpServletResponse response){
		System.out.println("userName"+userName);
		System.out.println("passWord"+passWord);
		return "";
	}
}
