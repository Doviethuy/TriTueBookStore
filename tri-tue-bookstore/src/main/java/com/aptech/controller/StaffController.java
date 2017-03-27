package com.aptech.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aptech.model.Category;
import com.aptech.model.Invoice;
import com.aptech.model.Product;
import com.aptech.model.InvoiceDetail;
import com.aptech.service.CategoryService;
import com.aptech.service.InvoiceDetailService;
import com.aptech.service.InvoiceService;
import com.aptech.service.ProductService;
import com.aptech.util.Constant;
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


	@RequestMapping(value = "/staff/add-invoice", method = RequestMethod.POST)
	public String addInvoice(HttpServletRequest request, HttpServletResponse response) throws ParseException, ServletException {
		HttpSession session = request.getSession();
		if (PermissionUtil.checkLogin(session)) {
			String redirect = GetterUtil.getString(request.getParameter("redirect").toString(), StringPool.BLANK);
			Long total = GetterUtil.getLong(request.getParameter("total").toString());
			String userName = session.getAttribute(Constant.USERNAME).toString();
			
			Invoice invoice = new Invoice();
			invoice.setAmount(total);
			invoice.setCreateDate(new Date());
			invoice.setModifyDate(new Date());
			invoice.setUsername(userName);
			
			Long ivId ;
			ArrayList<Invoice> lstInvoice = invoiceService.getAllInvoice();
			try {
				Invoice invoiceLast = lstInvoice.get(lstInvoice.size()-1);			
				ivId = invoiceLast.getIvId();
			} catch (Exception e) {
				// TODO: handle exception
			}
			ivId = new Long(0);
//			
			List<InvoiceDetail> invoiceDetails = new ArrayList<>();
			int arrLength = GetterUtil.getInteger(request.getParameter("arrLength").toString());
			for (int i = 0; i < arrLength; i++) {
				long proId = GetterUtil.getLong(request.getParameter("id"+(i+1)).toString());
				int proQty = GetterUtil.getInteger(request.getParameter("qty"+(i+1)).toString());
				long price = productService.getProduct(proId).getPrice();
				InvoiceDetail invoiceDetail = new InvoiceDetail(ivId, proId, proQty, proQty*price, new Date());
				invoiceDetails.add(invoiceDetail);
//				invoiceDetailService.addInvoiceDetail(invoiceDetail);
			}
			
			invoice.setInvoiceDetails(invoiceDetails);
			invoiceService.addInvoice(invoice);
			return "redirect:" + redirect;
		} else {
			return "redirect:/";
		}
	}
		
	@RequestMapping("/staff/san-pham")
	public String staffProduct(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (PermissionUtil.checkLogin(session)) {
			return "redirect:/view-hang-hoa";
		} else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/staff/hoa-don")
	public String staffInvoice(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (PermissionUtil.checkLogin(session)) {
			ArrayList<Invoice> lstInvoice = invoiceService.getAllInvoice();
			request.setAttribute("lstInv", lstInvoice);
			return "staff/view-hoa-don";			
		} else {
			return "redirect:/";
		}
	}
	
	
}
