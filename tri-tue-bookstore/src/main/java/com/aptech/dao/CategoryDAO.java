package com.aptech.dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aptech.model.Category;

@Repository
public class CategoryDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public ArrayList<Category> getAllCategory() {
		Session session = this.sessionFactory.getCurrentSession();
		return new ArrayList<Category>(session.createQuery("from Category").list());
	}

	public Category getCategory(int cateId) {
		Session session = this.sessionFactory.getCurrentSession();
		return (Category) session.get(Category.class, new Integer(cateId));
	}

	public Category addCategory(Category category) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(category);
		return category;
	}

	public boolean updateCategory(Category category) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.update(category);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean deleteCategory(int cateId) {
		Session session = this.sessionFactory.getCurrentSession();
		Category category = (Category) session.get(Category.class, new Integer(cateId));
		try {
			if (category != null) {
				session.delete(category);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
}
