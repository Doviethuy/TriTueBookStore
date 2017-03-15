package com.aptech.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.aptech.model.User;
import com.aptech.service.UserService;
import com.aptech.util.FileUtil;
import com.aptech.util.PermissionUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;

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
	public String manageUser(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (PermissionUtil.checkLogin(session)) {
			if (PermissionUtil.checkAdminRole(session)) {
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

	@RequestMapping(value = "/admin/add-staff", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, @RequestParam("files") MultipartFile file) throws ParseException, ServletException {
		HttpSession session = request.getSession();
		if (PermissionUtil.checkLogin(session)) {
			if (PermissionUtil.checkAdminRole(session)) {
				String redirect = GetterUtil.getString(request.getParameter("redirect").toString(), StringPool.BLANK);
				String password = GetterUtil.getString(request.getParameter("password").toString(), StringPool.BLANK);
				String name = GetterUtil.getString(request.getParameter("name").toString(), StringPool.BLANK);
				String strDob = GetterUtil.getString(request.getParameter("dob").toString(), StringPool.BLANK);
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date dob = sdf.parse(strDob);
				byte gender = (byte) GetterUtil.getInteger(request.getParameter("gender").toString(), 0);
				String address = GetterUtil.getString(request.getParameter("address").toString(), StringPool.BLANK);
				String phone = GetterUtil.getString(request.getParameter("phone").toString(), StringPool.BLANK);
				String description = GetterUtil.getString(request.getParameter("description").toString(), StringPool.BLANK);
				byte role = (byte) GetterUtil.getInteger(request.getParameter("role").toString(), 0);
				String userName = GetterUtil.getString(request.getParameter("userName").toString(), StringPool.BLANK);
				FileUtil.upload(request, file);
				User item = new User(userName, password, name, dob, gender, address, phone, "", description, new Date(), new Date(), role);
				// userService.addUser(item);
				return "redirect:" + redirect;
			} else {
				return "redirect:/";
			}
		} else {
			return "redirect:/";
		}
	}
}