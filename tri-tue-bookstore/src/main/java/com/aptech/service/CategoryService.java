package com.aptech.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aptech.dao.CategoryDAO;
import com.aptech.model.Category;

@Service("categoryService")
public class CategoryService {
	@Autowired
	CategoryDAO categoryDAO;

	@Transactional
	public ArrayList<Category> getAllCategory() {
		return categoryDAO.getAllCategory();
	}

	@Transactional
	public Category getCategory(int cateId) {
		return categoryDAO.getCategory(cateId);
	}

	@Transactional
	public Category addCategory(Category category) {
		return categoryDAO.addCategory(category);
	}

	@Transactional
	public boolean updateCategory(Category category) {
		return categoryDAO.updateCategory(category);
	}

	@Transactional
	public boolean deleteCategory(int cateId) {
		return categoryDAO.deleteCategory(cateId);
	}
}
