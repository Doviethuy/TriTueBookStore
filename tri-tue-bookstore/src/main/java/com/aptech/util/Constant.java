package com.aptech.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;

import com.aptech.model.Category;
import com.aptech.model.FakeProduct;
import com.aptech.model.Invoice;
import com.aptech.model.Product;
import com.aptech.service.CategoryService;

public class Constant {
	public static final String USERNAME = "sessionUserName";
	public static final String FULLNAME = "sessionFullName";
	public static final String AVARTAR = "sessionAvartar";
	public static final String ROLE = "sessionUserRole";
	public static final byte ADMIN_ROLE = 1;
	public static final byte STAFF_ROLE = 0;
	public static final String ADD_INVOICE_SUCCESS = "ADD_INVOICE_SUCCESS";
	public static final String EDIT_INVOICE_SUCCESS = "EDIT_INVOICE_SUCCESS";
	public static final String ADD_STAFF_SUCCESS = "ADD_STAFF_SUCCESS";
	public static final String EDIT_STAFF_SUCCESS = "EDIT_STAFF_SUCCESS";
	public static final String DELETE_STAFF_SUCCESS = "DELETE_STAFF_SUCCESS";
	public static final String ADD_CATEGORY_SUCCESS = "ADD_CATEGORY_SUCCESS";
	public static final String EDIT_CATEGORY_SUCCESS = "EDIT_CATEGORY_SUCCESS";
	public static final String DELETE_CATEGORY_SUCCESS = "DELETE_CATEGORY_SUCCESS";
	public static final String ADD_PRODUCT_SUCCESS = "ADD_PRODUCT_SUCCESS";
	public static final String EDIT_PRODUCT_SUCCESS = "EDIT_PRODUCT_SUCCESS";
	public static final String DELETE_PRODUCT_SUCCESS = "DELETE_PRODUCT_SUCCESS";
	public static final String LOGIN_SUCCESS = "LOGIN_SUCCESS";
	public static final String LOGIN_FAIL = "LOGIN_FAIL";

	public static String toReportDataString(ArrayList<Invoice> invoices, Date start, Date end) {
		StringBuilder sb = new StringBuilder();
		Collections.sort(invoices, new Comparator<Invoice>() {
			public int compare(Invoice o1, Invoice o2) {
				return o1.getCreateDate().compareTo(o2.getCreateDate());
			}
		});
		for (Iterator iterator = invoices.iterator(); iterator.hasNext();) {
			Invoice invoice = (Invoice) iterator.next();
			if (!isThisDateBettwenRange(invoice.getCreateDate(), start, end)) {
				iterator.remove();
			}
		}
		sb.append("[");
		for (int i = 0; i < invoices.size(); i++) {
			sb.append("[");
			sb.append(invoices.get(i).getCreateDate().getTime() + ", " + invoices.get(i).getAmount());
			if (i == invoices.size() - 1) {
				sb.append("]");
			} else {
				sb.append("],");
			}
		}
		sb.append("]");
		return sb.toString();
	}

	private static boolean isThisDateBettwenRange(Date dateToValidate, Date start, Date end) {
		Calendar c = Calendar.getInstance();
		c.setTime(start);
		c.add(Calendar.DATE, -1);
		start = c.getTime();
		c.setTime(end);
		c.add(Calendar.DATE, 1);
		end = c.getTime();
		if (dateToValidate.before(end) && dateToValidate.after(start)) {
			return true;
		} else {
			return false;
		}
	}

	public static ArrayList<Product> paginateProduct(int start, int end, ArrayList<Product> products) {
		int i = 0;
		for (Iterator iterator = products.iterator(); iterator.hasNext();) {
			Product product = (Product) iterator.next();
			if (i < start - 1 || i > end - 1) {
				iterator.remove();
			}
			i++;
		}
		return products;
	}

	public static ArrayList<FakeProduct> getProductFakeData(ArrayList<Product> products, ArrayList<Category> allCategory) {
		ArrayList<FakeProduct> fakeProducts = new ArrayList<FakeProduct>();
		for (Product item : products) {
			FakeProduct temp = new FakeProduct();
			temp.setProId(item.getProId());
			temp.setProName(item.getProName());
			for (Category ctemp : allCategory) {
				if (ctemp.getCateId() == item.getCateId()) {
					temp.setCateName(ctemp.getCateName());
				}
			}
			temp.setPrice(item.getPrice());
			temp.setQuantity(item.getQuantity());
			temp.setDescription(item.getDescription());
			temp.setImg(item.getImg());
			fakeProducts.add(temp);
		}
		return fakeProducts;
	}
}
