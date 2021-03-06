package com.aptech.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aptech.model.InvoiceDetail;

@Repository
public class InvoiceDetailDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public ArrayList<InvoiceDetail> getAllInvoiceDetail() {
		Session session = this.sessionFactory.getCurrentSession();
		return new ArrayList<InvoiceDetail>(session.createQuery("from InvoiceDetail").list());
	}

	@SuppressWarnings("unchecked")
	public InvoiceDetail getInvoiceDetail(long ivId, long proId) {
		Session session = this.sessionFactory.getCurrentSession();
		ArrayList<InvoiceDetail> lst = new ArrayList<InvoiceDetail>(session.createQuery("from InvoiceDetail where ivId =:id").setParameter("id", ivId).list());
		for (InvoiceDetail invoiceDetail : lst) {
			if(invoiceDetail.getProId()==proId)
				return invoiceDetail;
		}
		return null;
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

	@SuppressWarnings("unchecked")
	public List<InvoiceDetail> getInvoiceDetailByIvID(long ivId) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(InvoiceDetail.class);
		criteria.add(Restrictions.eq("ivId", ivId));
		List<InvoiceDetail> details = criteria.list();
		return details;
	}

	@SuppressWarnings("unchecked")
	public List<InvoiceDetail> getInvoiceDetailByProId(long proId) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(InvoiceDetail.class);
		criteria.add(Restrictions.eq("proId", proId));
		List<InvoiceDetail> details = criteria.list();
		return details;
	}

	@SuppressWarnings("unchecked")
	public List<InvoiceDetail> getInvoiceDetailByCreateDate(Date createDate) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(InvoiceDetail.class);
		criteria.add(Restrictions.eq("createDate", createDate));
		List<InvoiceDetail> details = criteria.list();
		return details;
	}
}
