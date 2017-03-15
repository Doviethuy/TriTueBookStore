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
import com.aptech.model.Invoice;
import com.aptech.model.Product;
import com.aptech.service.CategoryService;
import com.aptech.service.InvoiceDetailService;
import com.aptech.service.InvoiceService;
import com.aptech.service.ProductService;
import com.aptech.util.PermissionUtil;

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
			return "staff/view-ban-hang";
		} else {
			return "redirect:/";
		}
	}
		
	@RequestMapping("/staff/san-pham")
	public String staffProduct(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (PermissionUtil.checkLogin(session)) {
			ArrayList<Category> lstCategory = categoryService.getAllCategory();
			request.setAttribute("lstCate", lstCategory);
			ArrayList<Product> lstProduct = productService.getAllProduct();
			request.setAttribute("lstPro", lstProduct);
			return "staff/view-san-pham";			
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
	
	@RequestMapping(value="/staff/add-product", method = RequestMethod.POST)
	public String addProduct(@RequestParam("proName")String proName, @RequestParam("cateId")long cateId, @RequestParam("price") long price,@RequestParam("description") String description, HttpServletRequest request, HttpServletResponse response) {
			
		System.out.println("proName:"+proName);
		System.out.println("cateId:"+cateId);
		System.out.println("price:"+price);
		System.out.println("description"+description);
		return "";
	}
}
