package com.aptech.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aptech.dao.ProductDAO;
import com.aptech.model.Product;

@Service("productService")
public class ProductService {
	@Autowired
	ProductDAO productDAO;

	@Transactional
	public ArrayList<Product> getAllProduct() {
		return productDAO.getAllProduct();
	}

	@Transactional
	public Product getProduct(long proId) {
		return productDAO.getProduct(proId);
	}

	@Transactional
	public Product addProduct(Product product) {
		return productDAO.addProduct(product);
	}

	@Transactional
	public boolean updateProduct(Product product) {
		return productDAO.updateProduct(product);
	}

	@Transactional
	public boolean deleteProduct(long proId) {
		return productDAO.deleteProduct(proId);
	}
}
