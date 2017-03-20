package com.aptech.controller;

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
import com.aptech.model.Product;
import com.aptech.model.User;
import com.aptech.service.CategoryService;
import com.aptech.service.InvoiceDetailService;
import com.aptech.service.ProductService;
import com.aptech.service.UserService;
import com.aptech.util.Constant;
import com.aptech.util.FileUtil;
import com.aptech.util.PermissionUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;

@Controller
public class AdminController {
	@Autowired
	UserService userService;
	@Autowired
	ProductService productService;
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

	@RequestMapping("/admin/report/san-pham")
	public String reportProduct(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (PermissionUtil.checkLogin(session)) {
			if (PermissionUtil.checkAdminRole(session)) {
				ArrayList<Category> lstCategory = categoryService
						.getAllCategory();
				request.setAttribute("lstCate", lstCategory);
				ArrayList<Product> lstProduct = productService.getAllProduct();
				request.setAttribute("lstPro", lstProduct);
				return "admin/view-san-pham";
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
	public String addUser(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("files") MultipartFile file) throws ParseException,
			ServletException {
		HttpSession session = request.getSession();
		if (PermissionUtil.checkLogin(session)) {
			if (PermissionUtil.checkAdminRole(session)) {
				String redirect = GetterUtil.getString(
						request.getParameter("redirect").toString(),
						StringPool.BLANK);
				String password = GetterUtil.getString(
						request.getParameter("password").toString(),
						StringPool.BLANK);
				String name = GetterUtil.getString(request.getParameter("name")
						.toString(), StringPool.BLANK);
				String strDob = GetterUtil.getString(request
						.getParameter("dob").toString(), StringPool.BLANK);
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date dob = sdf.parse(strDob);
				byte gender = (byte) GetterUtil.getInteger(request
						.getParameter("gender").toString(), 0);
				String address = GetterUtil.getString(
						request.getParameter("address").toString(),
						StringPool.BLANK);
				String phone = GetterUtil.getString(
						request.getParameter("phone").toString(),
						StringPool.BLANK);
				String description = GetterUtil.getString(
						request.getParameter("description").toString(),
						StringPool.BLANK);
				byte role = (byte) GetterUtil.getInteger(
						request.getParameter("role").toString(), 0);
				String userName = GetterUtil.getString(
						request.getParameter("userName").toString(),
						StringPool.BLANK);
				String fileName = FileUtil.upload(request, file);
				User item = new User(userName, password, name, dob, gender,
						address, phone, fileName, description, new Date(),
						new Date(), role);
				userService.addUser(item);
				return "redirect:" + redirect;
			} else {
				return "redirect:/";
			}
		} else {
			return "redirect:/";
		}
	}

	@RequestMapping(value = "/admin/delete-staff", method = RequestMethod.POST)
	public void deleteUser(HttpServletRequest request,
			HttpServletResponse response) throws ParseException,
			ServletException {
		HttpSession session = request.getSession();
		if (PermissionUtil.checkLogin(session)) {
			if (PermissionUtil.checkAdminRole(session)) {
				String userName = GetterUtil.getString(
						request.getParameter("userName").toString(),
						StringPool.BLANK);
				userService.deleteUser(userName);
			}
		}
	}

	@RequestMapping(value = "/admin/edit-staff", method = RequestMethod.POST)
	public String editUser(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "files", required = false) MultipartFile file)
			throws ParseException, ServletException {
		HttpSession session = request.getSession();
		if (PermissionUtil.checkLogin(session)) {
			if (PermissionUtil.checkAdminRole(session)) {
				String userName = GetterUtil.getString(
						request.getParameter("userNameHidden"),
						StringPool.BLANK);
				String redirect = GetterUtil.getString(
						request.getParameter("redirect").toString(),
						StringPool.BLANK);
				String password = GetterUtil.getString(
						request.getParameter("password").toString(),
						StringPool.BLANK);
				String name = GetterUtil.getString(request.getParameter("name")
						.toString(), StringPool.BLANK);
				String strDob = GetterUtil.getString(request
						.getParameter("dob").toString(), StringPool.BLANK);
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date dob = sdf.parse(strDob);
				byte gender = (byte) GetterUtil.getInteger(request
						.getParameter("gender").toString(), 0);
				String address = GetterUtil.getString(
						request.getParameter("address").toString(),
						StringPool.BLANK);
				String phone = GetterUtil.getString(
						request.getParameter("phone").toString(),
						StringPool.BLANK);
				String description = GetterUtil.getString(
						request.getParameter("description").toString(),
						StringPool.BLANK);
				byte role = (byte) GetterUtil.getInteger(
						request.getParameter("role").toString(), 0);
				String fileName = FileUtil.upload(request, file);
				if (fileName.equals(StringPool.BLANK)) {
					User oldUser = userService.getUser(userName);
					fileName = oldUser.getImg();
				}
				User item = new User(userName, password, name, dob, gender,
						address, phone, fileName, description, new Date(),
						new Date(), role);
				userService.updateUser(item);
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
				String userName = GetterUtil.getString(
						request.getParameter("id"), StringPool.BLANK);
				User user = null;
				if (!userName.equals(StringPool.BLANK)) {
					user = userService.getUser(userName);

				}
				return user;
			}
		}
		return null;
	}

	@RequestMapping(value = "/admin/add-product", method = RequestMethod.POST)
	public String addProduct(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("files") MultipartFile file) throws ParseException,
			ServletException {
		HttpSession session = request.getSession();
		if (PermissionUtil.checkLogin(session)) {
			if (PermissionUtil.checkAdminRole(session)) {
				String redirect = GetterUtil.getString(
						request.getParameter("redirect").toString(),
						StringPool.BLANK);
				String proName = GetterUtil.getString(
						request.getParameter("proName").toString(),
						StringPool.BLANK);
				Long cateId = GetterUtil.getLong(request.getParameter("cateId")
						.toString());
				Long price = GetterUtil.getLong(request.getParameter("price")
						.toString());
				int quantity = GetterUtil.getInteger(request.getParameter(
						"quantity").toString());
				String fileName = FileUtil.upload(request, file);
				String description = GetterUtil.getString(
						request.getParameter("description").toString(),
						StringPool.BLANK);
				String userName = session.getAttribute(Constant.USERNAME)
						.toString();
				Product item = new Product(proName, cateId, price, quantity,
						fileName, description, new Date(), new Date(), userName);
				productService.addProduct(item);
				return "redirect:" + redirect;
			} else {
				return "redirect:/";
			}
		} else {
			return "redirect:/";
		}
	}

	@RequestMapping(value = "/admin/delete-product", method = RequestMethod.POST)
	public void deleteProduct(HttpServletRequest request,
			HttpServletResponse response) throws ParseException,
			ServletException {
		HttpSession session = request.getSession();
		if (PermissionUtil.checkLogin(session)) {
			if (PermissionUtil.checkAdminRole(session)) {
				long proId = GetterUtil.getLong(request.getParameter("proId")
						.toString());
				productService.deleteProduct(proId);
			}
		}
	}

	@RequestMapping(value = "/admin/edit-product", method = RequestMethod.POST)
	public String editProduct(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "files", required = false) MultipartFile file)
			throws ParseException, ServletException {
		HttpSession session = request.getSession();
		if (PermissionUtil.checkLogin(session)) {
			if (PermissionUtil.checkAdminRole(session)) {
				long proId = GetterUtil.getLong(request.getParameter(
						"proIdHidden").toString());
				String redirect = GetterUtil.getString(
						request.getParameter("redirect").toString(),
						StringPool.BLANK);
				String proName = GetterUtil.getString(
						request.getParameter("proName").toString(),
						StringPool.BLANK);
				Long cateId = GetterUtil.getLong(request.getParameter("cateId")
						.toString());
				Long price = GetterUtil.getLong(request.getParameter("price")
						.toString());
				int quantity = GetterUtil.getInteger(request.getParameter(
						"quantity").toString());
				String fileName = FileUtil.upload(request, file);
				String description = GetterUtil.getString(
						request.getParameter("description").toString(),
						StringPool.BLANK);
				String userName = session.getAttribute(Constant.USERNAME)
						.toString();
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
				System.out.println("pro id:"+proId);
				Product product = null;
				if (proId != 0L) {
					product = productService.getProduct(proId);
				}
				return product;
			}
		}
		return null;
	}

}
