package com.aptech.dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aptech.model.Product;

@Repository
public class ProductDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public ArrayList<Product> getAllProduct() {
		Session session = this.sessionFactory.getCurrentSession();
		return new ArrayList<Product>(session.createQuery("from Product").list());
	}

	public Product getProduct(long proId) {
		Session session = this.sessionFactory.getCurrentSession();
		return (Product) session.get(Product.class, new Long(proId));
	}

	public Product addProduct(Product product) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(product);
		return product;
	}

	public boolean updateProduct(Product product) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.update(product);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean deleteProduct(long proId) {
		Session session = this.sessionFactory.getCurrentSession();
		Product product = (Product) session.get(Product.class, new Long(proId));
		try {
			if (product != null) {
				session.delete(product);
				return true;
			} else {
				return false;
			}

		} catch (Exception exception) {
			return false;
		}
	}

}
