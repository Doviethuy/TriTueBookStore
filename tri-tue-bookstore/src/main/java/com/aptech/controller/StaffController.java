package com.aptech.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aptech.model.Category;
import com.aptech.model.FakeProduct;
import com.aptech.model.Invoice;
import com.aptech.model.Product;
import com.aptech.model.InvoiceDetail;
import com.aptech.model.ProductJsonObject;
import com.aptech.model.User;
import com.aptech.service.CategoryService;
import com.aptech.service.InvoiceDetailService;
import com.aptech.service.InvoiceService;
import com.aptech.service.ProductService;
import com.aptech.service.UserService;
import com.aptech.util.Constant;
import com.aptech.util.PermissionUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;

@Controller
public class StaffController {
	@Autowired
	UserService userService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;
	@Autowired
	InvoiceService invoiceService;
	@Autowired
	InvoiceDetailService invoiceDetailService;

	@RequestMapping("/staff/ban-hang")
	public String index(HttpServletRequest request) {
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
	public String addInvoice(HttpServletRequest request) throws ParseException, ServletException {
		HttpSession session = request.getSession();
		if (PermissionUtil.checkLogin(session)) {
			String redirect = GetterUtil.getString(request.getParameter("redirect").toString(), StringPool.BLANK);
			Long total = GetterUtil.getLong(request.getParameter("total").toString());
			String userName = session.getAttribute(Constant.USERNAME).toString();
			int arrLength = GetterUtil.getInteger(request.getParameter("arrLength").toString());

			Invoice invoice = new Invoice(total, new Date(), new Date(), userName);
			invoiceService.addInvoice(invoice);

			ArrayList<Invoice> lstInvoice = invoiceService.getAllInvoice();
			long ivId = lstInvoice.get(lstInvoice.size() - 1).getIvId();
			for (int i = 0; i < arrLength; i++) {
				long proId = GetterUtil.getLong(request.getParameter("id" + (i + 1)).toString());
				int proQty = GetterUtil.getInteger(request.getParameter("qty" + (i + 1)).toString());
				long price = productService.getProduct(proId).getPrice();
				invoiceDetailService.addInvoiceDetail(new InvoiceDetail(ivId, proId, proQty, proQty * price, new Date()));
			}
			session.setAttribute(Constant.ADD_INVOICE_SUCCESS, Constant.ADD_INVOICE_SUCCESS);
			return "redirect:" + redirect;
		} else {
			return "redirect:/";
		}
	}

	@RequestMapping("/staff/dat-hang/{id}")
	public String dathang(@PathVariable("id")long ivId,HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (PermissionUtil.checkLogin(session)) {
			request.setAttribute("invoice", invoiceService.getInvoice(ivId));
			ArrayList<InvoiceDetail> lst = new ArrayList<InvoiceDetail>();
			for (InvoiceDetail invoiceDetail : invoiceDetailService.getAllInvoiceDetail()) {
				if (invoiceDetail.getIvId() == ivId)
					lst.add(invoiceDetail);
			}
			request.setAttribute("lstDetail", lst);
			ArrayList<Product> lstProduct = productService.getAllProduct();
			request.setAttribute("lstPro", lstProduct);
			User user = userService.getUser(session.getAttribute(Constant.USERNAME).toString());
			request.setAttribute("user", user);
			return "staff/view-dat-hang";
		} else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/staff/hoa-don")
	public String staffInvoice(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (PermissionUtil.checkLogin(session)) {
			ArrayList<Invoice> lstInvoice = invoiceService.getInvoiceByUser(session.getAttribute(Constant.USERNAME).toString());
			request.setAttribute("lstInv", lstInvoice);
			return "staff/view-hoa-don";
		} else {
			return "redirect:/";
		}
	}

	@RequestMapping("/staff/san-pham")
	public String staffProduct(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (PermissionUtil.checkLogin(session)) {
			ArrayList<Product> lstProduct = productService.getAllProduct();
			request.setAttribute("lstPro", lstProduct);
			ArrayList<Category> lstCategory = categoryService.getAllCategory();
			request.setAttribute("lstCate", lstCategory);
			return "staff/view-hang-hoa";
		} else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/staff/get-all-product")
	public @ResponseBody String staffAllProduct(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (PermissionUtil.checkLogin(session)) {
			Integer pageNumber = 0;
			if (null != request.getParameter("iDisplayStart")) {
				pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / 10) + 1;
			}
			Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));
			ArrayList<Product> lstProduct = productService.getAllProduct();
			int productSize = lstProduct.size();
			ArrayList<Category> allCategory = categoryService.getAllCategory();
			int start = ((pageNumber - 1) * pageDisplayLength) + 1;
			int end = pageNumber * pageDisplayLength;
			ArrayList<Product> pagedProduct = Constant.paginateProduct(start, end, lstProduct);
			ArrayList<FakeProduct> productFakeData = Constant.getProductFakeData(pagedProduct, allCategory);
			ProductJsonObject object = new ProductJsonObject();
			object.setiTotalDisplayRecords(productSize);
			object.setiTotalRecords(productSize);
			object.setAaData(productFakeData);
			Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
			String json = gson.toJson(object);
			return json;
		} else {
			return "redirect:/";
		}
	}

}
