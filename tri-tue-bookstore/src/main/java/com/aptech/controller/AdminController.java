package com.aptech.controller;

import java.io.UnsupportedEncodingException;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.aptech.model.Category;
import com.aptech.model.Invoice;
import com.aptech.model.InvoiceDetail;
import com.aptech.model.Product;
import com.aptech.model.User;
import com.aptech.service.CategoryService;
import com.aptech.service.InvoiceDetailService;
import com.aptech.service.InvoiceService;
import com.aptech.service.ProductService;
import com.aptech.service.UserService;
import com.aptech.util.Constant;
import com.aptech.util.FileUtil;
import com.aptech.util.PermissionUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;

@Controller
public class AdminController {
	@Autowired
	UserService userService;
	@Autowired
	ProductService productService;
	@Autowired
	InvoiceService invoiceService;
	@Autowired
	InvoiceDetailService invoiceDetailService;
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
	
	@RequestMapping("/admin/report/get-data-report")
	public @ResponseBody String getReportData(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (PermissionUtil.checkLogin(session)) {
			if (PermissionUtil.checkAdminRole(session)) {
				ArrayList<Invoice> allInvoice = invoiceService.getAllInvoice();
				Date start = GetterUtil.getDate(request.getParameter("start").toString(), new SimpleDateFormat("dd/MM/yyyy"));
				Date end = GetterUtil.getDate(request.getParameter("end").toString(), new SimpleDateFormat("dd/MM/yyyy"));
				return Constant.toReportDataString(allInvoice, start, end);
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
				ArrayList<Category> lstCategory = categoryService.getAllCategory();
				request.setAttribute("lstCate", lstCategory);
				ArrayList<Product> lstProduct = productService.getAllProduct();
				request.setAttribute("lstPro", lstProduct);
				return "admin/view-san-pham";
			} else {
				return "redirect:/";
			}
		} else

		{
			return "redirect:/";
		}
	}

	@RequestMapping("/admin/report/danh-muc")
	public String reportCategory(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (PermissionUtil.checkLogin(session)) {
			if (PermissionUtil.checkAdminRole(session)) {
				ArrayList<Category> lstCategory = categoryService.getAllCategory();
				request.setAttribute("lstCate", lstCategory);
				return "admin/view-danh-muc";
			} else {
				return "redirect:/";
			}
		} else {
			return "redirect:/";
		}
	}

	@RequestMapping("/admin/report/hoa-don")
	public String adminInvoice(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (PermissionUtil.checkLogin(session)) {
			ArrayList<Invoice> lstInvoice = invoiceService.getAllInvoice();
			request.setAttribute("lstInv", lstInvoice);
			return "admin/view-hoa-don";
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
	public String addUser(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("files") MultipartFile file) throws ParseException, ServletException, UnsupportedEncodingException {
		HttpSession session = request.getSession();
		if (PermissionUtil.checkLogin(session)) {
			if (PermissionUtil.checkAdminRole(session)) {
				request.setCharacterEncoding("UTF-8");
				String redirect = request.getParameter("redirect").toString();
				String password = request.getParameter("password").toString();
				String name = request.getParameter("name").toString();
				String strDob = request.getParameter("dob").toString();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date dob = sdf.parse(strDob);
				byte gender = (byte) GetterUtil.getInteger(request.getParameter("gender").toString(), 0);
				String address = request.getParameter("address").toString();
				String phone = request.getParameter("phone").toString();
				String description = request.getParameter("description").toString();
				byte role = (byte) GetterUtil.getInteger(request.getParameter("role").toString(), 0);
				String userName = request.getParameter("userName").toString();
				String fileName = FileUtil.upload(request, file);
				User item = new User(userName, password, name, dob, gender, address, phone, fileName, description,
						new Date(), new Date(), role);
				userService.addUser(item);
				session.setAttribute(Constant.ADD_STAFF_SUCCESS, Constant.ADD_STAFF_SUCCESS);
				return "redirect:" + redirect;
			} else {
				return "redirect:/";
			}
		} else {
			return "redirect:/";
		}
	}

	@RequestMapping(value = "/admin/delete-staff", method = RequestMethod.POST)
	public void deleteUser(HttpServletRequest request, HttpServletResponse response)
			throws ParseException, ServletException {
		HttpSession session = request.getSession();
		if (PermissionUtil.checkLogin(session)) {
			if (PermissionUtil.checkAdminRole(session)) {
				String userName = request.getParameter("userName").toString();
				userService.deleteUser(userName);
				session.setAttribute(Constant.DELETE_STAFF_SUCCESS, Constant.DELETE_STAFF_SUCCESS);
			}
		}
	}

	@RequestMapping(value = "/admin/edit-staff", method = RequestMethod.POST)
	public String editUser(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "files", required = false) MultipartFile file)
			throws ParseException, ServletException, UnsupportedEncodingException {
		HttpSession session = request.getSession();
		if (PermissionUtil.checkLogin(session)) {
			if (PermissionUtil.checkAdminRole(session)) {
				request.setCharacterEncoding("UTF-8");
				String userName = request.getParameter("userNameHidden");
				String redirect = request.getParameter("redirect").toString();
				String password = request.getParameter("password").toString();
				String name = request.getParameter("name").toString();
				String strDob = request.getParameter("dob").toString();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date dob = sdf.parse(strDob);
				byte gender = (byte) GetterUtil.getInteger(request.getParameter("gender").toString(), 0);
				String address = request.getParameter("address").toString();
				String phone = request.getParameter("phone").toString();
				String description = request.getParameter("description").toString();
				byte role = (byte) GetterUtil.getInteger(request.getParameter("role").toString(), 0);
				String fileName = FileUtil.upload(request, file);
				if (fileName.equals(StringPool.BLANK)) {
					User oldUser = userService.getUser(userName);
					fileName = oldUser.getImg();
				}
				User item = new User(userName, password, name, dob, gender, address, phone, fileName, description,
						new Date(), new Date(), role);
				userService.updateUser(item);
				session.setAttribute(Constant.EDIT_STAFF_SUCCESS, Constant.EDIT_STAFF_SUCCESS);
				return "redirect:" + redirect;
			} else {
				return "redirect:/";
			}
		} else {
			return "redirect:/";
		}
	}

	@RequestMapping(value = "/admin/find-user", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public User getUserById(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (PermissionUtil.checkLogin(session)) {
			if (PermissionUtil.checkAdminRole(session)) {
				String userName = GetterUtil.getString(request.getParameter("id"), StringPool.BLANK);
				User user = null;
				if (!userName.equals(StringPool.BLANK)) {
					user = userService.getUser(userName);

				}
				return user;
			}
		}
		return null;
	}

	@RequestMapping(value = "/admin/add-category", method = RequestMethod.POST)
	public String addCategory(HttpServletRequest request, HttpServletResponse response)
			throws ParseException, ServletException {
		HttpSession session = request.getSession();
		if (PermissionUtil.checkLogin(session)) {
			if (PermissionUtil.checkAdminRole(session)) {
				String redirect = GetterUtil.getString(request.getParameter("redirect").toString(), StringPool.BLANK);
				String cateName = GetterUtil.getString(request.getParameter("cateName").toString(), StringPool.BLANK);
				Category item = new Category(cateName);
				categoryService.addCategory(item);
				session.setAttribute(Constant.ADD_CATEGORY_SUCCESS, Constant.ADD_CATEGORY_SUCCESS);
				return "redirect:" + redirect;
			} else {
				return "redirect:/";
			}
		} else {
			return "redirect:/";
		}
	}

	@RequestMapping(value = "/admin/delete-category", method = RequestMethod.POST)
	public void deleteCategory(HttpServletRequest request, HttpServletResponse response)
			throws ParseException, ServletException {
		HttpSession session = request.getSession();
		if (PermissionUtil.checkLogin(session)) {
			if (PermissionUtil.checkAdminRole(session)) {
				int cateId = GetterUtil.getInteger(request.getParameter("cateId").toString());
				categoryService.deleteCategory(cateId);
				session.setAttribute(Constant.DELETE_CATEGORY_SUCCESS, Constant.DELETE_CATEGORY_SUCCESS);
			}
		}
	}

	@RequestMapping(value = "/admin/edit-category", method = RequestMethod.POST)
	public String editCategory(HttpServletRequest request, HttpServletResponse response)
			throws ParseException, ServletException {
		HttpSession session = request.getSession();
		if (PermissionUtil.checkLogin(session)) {
			if (PermissionUtil.checkAdminRole(session)) {
				int cateId = GetterUtil.getInteger(request.getParameter("cateId").toString());
				String redirect = GetterUtil.getString(request.getParameter("redirect").toString(), StringPool.BLANK);
				String cateName = GetterUtil.getString(request.getParameter("cateName").toString(), StringPool.BLANK);
				Category item = categoryService.getCategory(cateId);
				item.setCateName(cateName);
				categoryService.updateCategory(item);
				session.setAttribute(Constant.EDIT_CATEGORY_SUCCESS, Constant.EDIT_CATEGORY_SUCCESS);
				return "redirect:" + redirect;
			} else {
				return "redirect:/";
			}
		} else {
			return "redirect:/";
		}
	}

	@RequestMapping(value = "/admin/find-category", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Category getCategoryById(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (PermissionUtil.checkLogin(session)) {
			if (PermissionUtil.checkAdminRole(session)) {
				int cateId = GetterUtil.getInteger(request.getParameter("id"), 0);
				Category category = null;
				if (cateId != 0) {
					category = categoryService.getCategory(cateId);
				}
				return category;
			}
		}
		return null;
	}

	@RequestMapping(value = "/admin/add-product", method = RequestMethod.POST)
	public String addProduct(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("files") MultipartFile file) throws ParseException, ServletException {
		HttpSession session = request.getSession();
		if (PermissionUtil.checkLogin(session)) {
			if (PermissionUtil.checkAdminRole(session)) {
				String redirect = GetterUtil.getString(request.getParameter("redirect").toString(), StringPool.BLANK);
				String proName = GetterUtil.getString(request.getParameter("proName").toString(), StringPool.BLANK);
				int cateId = GetterUtil.getInteger(request.getParameter("cateId").toString());
				Long price = GetterUtil.getLong(request.getParameter("price").toString());
				int quantity = GetterUtil.getInteger(request.getParameter("quantity").toString());
				String fileName = FileUtil.upload(request, file);
				String description = GetterUtil.getString(request.getParameter("description").toString(),
						StringPool.BLANK);
				String userName = session.getAttribute(Constant.USERNAME).toString();
				Product item = new Product(proName, cateId, price, quantity, fileName, description, new Date(),
						new Date(), userName);
				productService.addProduct(item);
				session.setAttribute(Constant.ADD_PRODUCT_SUCCESS, Constant.ADD_PRODUCT_SUCCESS);
				return "redirect:" + redirect;
			} else {
				return "redirect:/";
			}
		} else {
			return "redirect:/";
		}
	}

	@RequestMapping(value = "/admin/delete-product", method = RequestMethod.POST)
	public void deleteProduct(HttpServletRequest request, HttpServletResponse response)
			throws ParseException, ServletException {
		HttpSession session = request.getSession();
		if (PermissionUtil.checkLogin(session)) {
			if (PermissionUtil.checkAdminRole(session)) {
				long proId = GetterUtil.getLong(request.getParameter("proId").toString());
				productService.deleteProduct(proId);
				session.setAttribute(Constant.DELETE_PRODUCT_SUCCESS, Constant.DELETE_PRODUCT_SUCCESS);
			}
		}
	}

	@RequestMapping(value = "/admin/edit-product", method = RequestMethod.POST)
	public String editProduct(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "files", required = false) MultipartFile file)
			throws ParseException, ServletException {
		HttpSession session = request.getSession();
		if (PermissionUtil.checkLogin(session)) {
			if (PermissionUtil.checkAdminRole(session)) {
				long proId = GetterUtil.getLong(request.getParameter("proIdHidden").toString());
				String redirect = GetterUtil.getString(request.getParameter("redirect").toString(), StringPool.BLANK);
				String proName = GetterUtil.getString(request.getParameter("proName").toString(), StringPool.BLANK);
				int cateId = GetterUtil.getInteger(request.getParameter("cateId").toString());
				Long price = GetterUtil.getLong(request.getParameter("price").toString());
				int quantity = GetterUtil.getInteger(request.getParameter("quantity").toString());
				String fileName = FileUtil.upload(request, file);
				String description = GetterUtil.getString(request.getParameter("description").toString(),
						StringPool.BLANK);
				String userName = session.getAttribute(Constant.USERNAME).toString();
				if (fileName.equals(StringPool.BLANK)) {
					Product oldProduct = productService.getProduct(proId);
					fileName = oldProduct.getImg();
				}
				Product item = productService.getProduct(proId);
				item.setProName(proName);
				item.setCateId(cateId);
				item.setPrice(price);
				item.setQuantity(quantity);
				item.setImg(fileName);
				item.setDescription(description);
				item.setUserName(userName);
				productService.updateProduct(item);
				session.setAttribute(Constant.EDIT_PRODUCT_SUCCESS, Constant.EDIT_PRODUCT_SUCCESS);
				return "redirect:" + redirect;
			} else {
				return "redirect:/";
			}
		} else {
			return "redirect:/";
		}
	}

	@RequestMapping(value = "/admin/find-product", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Product getProductById(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (PermissionUtil.checkLogin(session)) {
			if (PermissionUtil.checkAdminRole(session)) {
				long proId = GetterUtil.getLong(request.getParameter("id"), 0L);
				Product product = null;
				if (proId != 0L) {
					product = productService.getProduct(proId);
				}
				return product;
			}
		}
		return null;
	}

	@RequestMapping(value = "/admin/find-invoice")
	@ResponseBody
	public ArrayList<InvoiceDetail> getInvoiceById(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (PermissionUtil.checkLogin(session)) {
			if (PermissionUtil.checkAdminRole(session)) {
				long ivId = GetterUtil.getLong(request.getParameter("id").toString());
				ArrayList<InvoiceDetail> lstInvoiceDetail = new ArrayList<InvoiceDetail>();
				for (InvoiceDetail invoiceDetail : invoiceDetailService.getAllInvoiceDetail()) {
				if (invoiceDetail.getIvId() == ivId)
					lstInvoiceDetail.add(invoiceDetail);
				}
				return lstInvoiceDetail;
			}
		}
		return null;
	}

	@RequestMapping(value = "/admin/edit-invoice", method = RequestMethod.POST)
	public String editInvoice(HttpServletRequest request) throws ParseException, ServletException {
		HttpSession session = request.getSession();
		if (PermissionUtil.checkLogin(session)) {
			String redirect = GetterUtil.getString(request.getParameter("redirect").toString(), StringPool.BLANK);
			long ivId = GetterUtil.getLong(request.getParameter("ivId").toString());
			Invoice invoice = invoiceService.getInvoice(ivId);
			int arrLength = GetterUtil.getInteger(request.getParameter("arrLength").toString());
			long newTotal = 0;

			for (int i = 0; i < arrLength; i++) {
				long proId = GetterUtil.getLong(request.getParameter("id" + (i + 1)).toString());
				int proQty = GetterUtil.getInteger(request.getParameter("qty" + (i + 1)).toString());
				long price = productService.getProduct(proId).getPrice();
				newTotal += proQty * price;
				if (proQty > 0) {
					InvoiceDetail detail = invoiceDetailService.getInvoiceDetail(ivId, proId);
					detail.setQuantity(proQty);
					detail.setAmount(proQty * price);
					invoiceDetailService.updateInvoiceDetail(detail);
				}

			}
			invoice.setAmount(newTotal);
			invoice.setModifyDate(new Date());
			invoiceService.updateInvoice(invoice);
			session.setAttribute(Constant.EDIT_INVOICE_SUCCESS, Constant.EDIT_INVOICE_SUCCESS);
			return "redirect:" + redirect;
		} else {
			return "redirect:/";
		}
	}

	@RequestMapping(value="admin/autoInsert")
	public String insertInvoice(){
		boolean b = false;
		for (int i = 0; i < 5; i++) {
			Invoice invoice = invoiceService.createInvoice();
			b=invoiceService.addInvoice(invoice);
			if (b) {
				System.out.println("Insert thanh cong");
			}
			
		}
		return "redirect:/";
	}
}
