package com.aptech.controller;

import java.text.ParseException;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.aptech.model.Category;
import com.aptech.model.Product;
import com.aptech.service.CategoryService;
import com.aptech.service.InvoiceDetailService;
import com.aptech.service.InvoiceService;
import com.aptech.service.ProductService;
import com.aptech.util.Constant;
import com.aptech.util.FileUtil;
import com.aptech.util.PermissionUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;

@Controller
public class StaffController {
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;
	@Autowired
	InvoiceService invoiceService;
	@Autowired
	InvoiceDetailService invoiceDetailService;

	@RequestMapping("/staff/ban-hang")
	public String index(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (PermissionUtil.checkLogin(session)) {
			ArrayList<Product> lstProduct = productService.getAllProduct();
			request.setAttribute("lstPro", lstProduct);
			ArrayList<Category> lstCategory = categoryService.getAllCategory();
			request.setAttribute("lstCate", lstCategory);
			return "staff/view-ban-hang";
		} else {
			return "redirect:/";
		}
	}
		
	@RequestMapping("/staff/san-pham")
	public String staffProduct(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (PermissionUtil.checkLogin(session)) {
			return "staff/view-hang-hoa";			
		} else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/staff/hoa-don")
	public String staffInvoice(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (PermissionUtil.checkLogin(session)) {
			ArrayList<Category> lstCategory = categoryService.getAllCategory();
			request.setAttribute("lstCate", lstCategory);
			return "staff/view-hoa-don";			
		} else {
			return "redirect:/";
		}
	}
	
	
}
