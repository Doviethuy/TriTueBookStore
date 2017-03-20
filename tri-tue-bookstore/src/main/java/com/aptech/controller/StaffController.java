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
	
	@RequestMapping(value = "/staff/add-product", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, @RequestParam("files") MultipartFile file) throws ParseException, ServletException {
		HttpSession session = request.getSession();
		if (PermissionUtil.checkLogin(session)) {
			if (!PermissionUtil.checkAdminRole(session)) {
				String redirect = GetterUtil.getString(request.getParameter("redirect").toString(), StringPool.BLANK);
				String proName = GetterUtil.getString(request.getParameter("proName").toString(), StringPool.BLANK);
				Long cateId = GetterUtil.getLong(request.getParameter("cateId").toString());
				Long price = GetterUtil.getLong(request.getParameter("price").toString());
				int quantity = GetterUtil.getInteger(request.getParameter("quantity").toString());
				String fileName = FileUtil.upload(request, file);
				String description = GetterUtil.getString(request.getParameter("description").toString(), StringPool.BLANK);
				String userName = session.getAttribute(Constant.USERNAME).toString();
				Product item = new Product(proName, cateId, price, quantity, fileName, description, new Date(), new Date(), userName);
				productService.addProduct(item);
				return "redirect:" + redirect;
			} else {
				return "redirect:/";
			}
		} else {
			return "redirect:/";
		}
	}

	@RequestMapping(value = "/staff/delete-product", method = RequestMethod.POST)
	public void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ParseException, ServletException {
		HttpSession session = request.getSession();
		if (PermissionUtil.checkLogin(session)) {
			if (!PermissionUtil.checkAdminRole(session)) {
				long proId = GetterUtil.getLong(request.getParameter("proId").toString());
				productService.deleteProduct(proId);
			}
		}
	}

	@RequestMapping(value = "/staff/edit-product", method = RequestMethod.POST)
	public String editUser(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "files", required = false) MultipartFile file) throws ParseException, ServletException {
		HttpSession session = request.getSession();
		if (PermissionUtil.checkLogin(session)) {
			if (!PermissionUtil.checkAdminRole(session)) {
				long proId = GetterUtil.getLong(request.getParameter("proIdHidden").toString());
				String redirect = GetterUtil.getString(request.getParameter("redirect").toString(), StringPool.BLANK);
				String proName = GetterUtil.getString(request.getParameter("proName").toString(), StringPool.BLANK);
				Long cateId = GetterUtil.getLong(request.getParameter("cateId").toString());
				Long price = GetterUtil.getLong(request.getParameter("price").toString());
				int quantity = GetterUtil.getInteger(request.getParameter("quantity").toString());
				String fileName = FileUtil.upload(request, file);
				String description = GetterUtil.getString(request.getParameter("description").toString(), StringPool.BLANK);
				String userName = session.getAttribute(Constant.USERNAME).toString();
				if (fileName.equals(StringPool.BLANK)) {
					Product oldProduct = productService.getProduct(proId);
					fileName = oldProduct.getImg();
				}
				Product oldProduct = productService.getProduct(proId);
				Date createDate = oldProduct.getCreateDate();
				Product item = new Product(proName, cateId, price, quantity, fileName, description, createDate, new Date(), userName);
				productService.updateProduct(item);
				return "redirect:" + redirect;
			} else {
				return "redirect:/";
			}
		} else {
			return "redirect:/";
		}
	}

	@RequestMapping(value = "/staff/find-product", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Product getProductById(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (PermissionUtil.checkLogin(session)) {
			if (!PermissionUtil.checkAdminRole(session)) {
				long proId = GetterUtil.getLong(request.getParameter("id"));
				Product product = null;
				if (proId!=0L) {
					product = productService.getProduct(proId);
				}
				return product;
			}
		}
		return null;
	}
}
