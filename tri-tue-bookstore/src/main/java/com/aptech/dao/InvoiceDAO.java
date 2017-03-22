package com.aptech.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aptech.model.Invoice;
import com.aptech.model.InvoiceDetail;
import com.aptech.model.Product;

@Repository
public class InvoiceDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private InvoiceDetailDAO detailDAO;

	@Autowired
	private ProductDAO productDAO;

	public ArrayList<Invoice> getAllInvoice() {
		Session session = this.sessionFactory.getCurrentSession();
		return new ArrayList<Invoice>(session.createQuery("from Invoice").list());
	}

	public Invoice getInvoice(int ivId) {
		Session session = this.sessionFactory.getCurrentSession();
		return (Invoice) session.get(Invoice.class, new Integer(ivId));
	}

	public boolean addInvoice(Invoice invoice) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			List<InvoiceDetail> invoiceDetails = invoice.getInvoiceDetails();
			for (InvoiceDetail invoiceDetail : invoiceDetails) {
				long proId = invoiceDetail.getProId();
				if (!productDAO.isEnoughQuantity(proId, invoiceDetail.getQuantity())) {
					return false;
				}
			}

			session.persist(invoice);
			for (InvoiceDetail invoiceDetail : invoiceDetails) {
				session.persist(invoiceDetail);
				long proId = invoiceDetail.getProId();
				Product product = productDAO.getProduct(proId);
				product.setQuantity(product.getQuantity() - invoiceDetail.getQuantity());
				productDAO.updateProduct(product);
			}
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			return false;
		}

	}

	// public boolean updateInvoice(Invoice invoice) {
	// Session session = this.sessionFactory.getCurrentSession();
	// try {
	// session.update(invoice);
	// return true;
	// } catch (Exception e) {
	// return false;
	// }
	// }

	public boolean deleteInvoice(int ivId) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			Invoice invoice = (Invoice) session.get(Invoice.class, new Integer(ivId));
			tx.begin();
			if (invoice != null) {
				session.delete(invoice);
				List<InvoiceDetail> invoiceDetails = invoice.getInvoiceDetails();
				for (InvoiceDetail invoiceDetail : invoiceDetails) {
					Product product = productDAO.getProduct(invoiceDetail.getProId());
					product.setQuantity(product.getQuantity() + invoiceDetail.getQuantity());
					detailDAO.deleteInvoiceDetail(new Long(invoiceDetail.getIvId()).intValue(),
							new Long(invoiceDetail.getProId()).intValue());
				}
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			tx.rollback();
			return false;
		}
	}

}
