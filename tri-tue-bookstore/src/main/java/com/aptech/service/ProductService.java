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
	public ArrayList<Product> getAllProduct(){
		return productDAO.getAllProduct();
	}
	
	@Transactional
	public Product getProduct(int proId){
		return productDAO.getProduct(proId);
	}
	
	@Transactional
	public Product addProduct(Product product){
		return productDAO.addProduct(product);
	}
	
	@Transactional
	public void updateProduct(Product product){
		productDAO.updateProduct(product);
	}
	
	@Transactional
	public void deleteProduct(int proId){
		productDAO.deleteProduct(proId);
	}
}
