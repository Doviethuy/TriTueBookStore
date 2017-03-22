package com.aptech.dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aptech.model.InvoiceDetail;

@Repository
public class InvoiceDetailDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public ArrayList<InvoiceDetail> getAllInvoiceDetail() {
		Session session = this.sessionFactory.getCurrentSession();
		return new ArrayList<InvoiceDetail>(session.createQuery("from InvoiceDetail").list());
	}

	public InvoiceDetail getInvoiceDetail(int ivId, int proId) {
		Session session = this.sessionFactory.getCurrentSession();
		return (InvoiceDetail) session.get(InvoiceDetail.class, new Integer(ivId));
	}

	public InvoiceDetail addInvoiceDetail(InvoiceDetail invoiceDetail) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(invoiceDetail);
		return invoiceDetail;
	}

	public boolean updateInvoiceDetail(InvoiceDetail invoiceDetail) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.update(invoiceDetail);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean deleteInvoiceDetail(int ivId, int proId) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			InvoiceDetail invoiceDetail = (InvoiceDetail) session.get(InvoiceDetail.class, new Integer(ivId));
			if (invoiceDetail != null) {
				session.delete(invoiceDetail);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}

	}
}
