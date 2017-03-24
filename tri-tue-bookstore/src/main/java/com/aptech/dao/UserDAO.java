package com.aptech.dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aptech.model.User;

@Repository
public class UserDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public ArrayList<User> getAllUser() {
		Session session = this.sessionFactory.getCurrentSession();
		return new ArrayList<User>(session.createQuery("from User").list());
	}

	public User getUser(String userName) {
		Session session = this.sessionFactory.getCurrentSession();
		return (User) session.get(User.class, new String(userName));
	}

	public User addUser(User user) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(user);
		return user;
	}

	public boolean updateUser(User user) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.update(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean deleteUser(String userName) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			User user = (User) session.get(User.class, new String(userName));
			if (user != null) {
				session.delete(user);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}

	}
}
