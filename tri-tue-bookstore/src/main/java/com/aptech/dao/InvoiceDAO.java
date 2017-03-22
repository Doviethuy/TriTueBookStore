package com.aptech.dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aptech.model.Invoice;

@Repository
public class InvoiceDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public ArrayList<Invoice> getAllInvoice() {
		Session session = this.sessionFactory.getCurrentSession();
		return new ArrayList<Invoice>(session.createQuery("from Invoice").list());
	}

	public Invoice getInvoice(int ivId) {
		Session session = this.sessionFactory.getCurrentSession();
		return (Invoice) session.get(Invoice.class, new Integer(ivId));
	}

	public Invoice addInvoice(Invoice invoice) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(invoice);
		return invoice;
	}

	public boolean updateInvoice(Invoice invoice) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.update(invoice);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean deleteInvoice(int ivId) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			Invoice invoice = (Invoice) session.get(Invoice.class, new Integer(ivId));
			if (invoice != null) {
				session.delete(invoice);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}

	}
}
