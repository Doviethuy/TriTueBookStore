package com.aptech.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
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

	public Invoice getInvoice(long ivId) {
		Session session = this.sessionFactory.getCurrentSession();
		return (Invoice) session.get(Invoice.class, new Long(ivId));
	}

	public boolean addInvoice(Invoice invoice) {
		/*Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			List<InvoiceDetail> invoiceDetails = invoice.getInvoiceDetails();
			if (invoiceDetails.size()==0) {
				return false;
			}
			for (InvoiceDetail invoiceDetail : invoiceDetails) {
				long proId = invoiceDetail.getProId();
				if (!productDAO.isEnoughQuantity(proId, invoiceDetail.getQuantity())) {
					return false;
				}
			}

			session.persist(invoice);
			Long ivId = invoice.getIvId();
			for (InvoiceDetail invoiceDetail : invoiceDetails) {
				invoiceDetail.setIvId(ivId);
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
		}finally {
			session.close();
		}*/
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(invoice);
		return true;
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

	public ArrayList<Invoice> getInvoiceByUser(String user) {
		Session session = this.sessionFactory.getCurrentSession();
		return new ArrayList<Invoice>(session.createQuery("from Invoice where username =:name").setParameter("name", user).list());
	}
	
	public List<Invoice> getInvoiceByCreateDate(Date date) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Invoice.class);
		criteria.add(Restrictions.eq("createDate", date));
		List<Invoice> invoices = criteria.list();
		return invoices;
	}

//	public static void main(String[] args) {
////		System.out.println(rundomProduct());
//		
//		for (int i = 0; i < 10; i++) {
////			System.out.println(rundomProduct());
////			System.out.println(rundomDate());
//			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
//			System.out.println(format.format(createDate(2)));
//		}
//	}
	
	public int rundomProduct(){
		Random rand = new Random();
		int  randomInt = rand.nextInt(100) + 1;
		return randomInt;
	}
	
	public int rundom(int threshold){
		Random rand = new Random();
		int  randomInt = rand.nextInt(threshold) + 1;
		return randomInt;
	}
	
	public int rundomDate(){
		Random rand = new Random();
		int  date = rand.nextInt(30) + 1;
		return date;
	}
	
	public  Date createDate(){
	Calendar calendar = Calendar.getInstance();
	calendar.set(Calendar.DAY_OF_MONTH, this.rundomDate());
	Date date = calendar.getTime();
	return date;
	}
	
	public List<Product> getListProduct(){
		List<Product> list = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			int proid = rundomProduct();
			Product product = productDAO.getProduct(proid);
			if (list.contains(product) ) {
				continue;
			}
			if (product.getQuantity()==0) {
				product.setQuantity(20);
				productDAO.updateProduct(product);
			}
			list.add(product);
		}
		return list;
	}
	
	public InvoiceDetail createInvoiceDetail(Product product, Date createDate){
		InvoiceDetail detail = new InvoiceDetail();
		long proid = product.getProId();
		int quantity = product.getQuantity();
		int invoiceQuantity = this.rundom(quantity);
		detail.setProId(proid);
		detail.setCreateDate(createDate);
		detail.setQuantity(invoiceQuantity);
		detail.setAmount(invoiceQuantity*(product.getPrice()));
		return detail;
	}
	
	public Invoice createInvoice(){
		Invoice invoice = new Invoice();
		Date createDate = this.createDate();
		invoice.setCreateDate(createDate);
		invoice.setModifyDate(createDate);
		invoice.setUsername("huyenvtt");
		List<Product> listProduct = this.getListProduct();
		int size = listProduct.size();
		List<InvoiceDetail> invoiceDetails = new ArrayList<>();
		int amount= 0;
		for (Product product:listProduct) {
			InvoiceDetail detail = this.createInvoiceDetail(product, createDate);
			invoiceDetails.add(detail);
			amount += detail.getAmount();
		}
		invoice.setInvoiceDetails(invoiceDetails);
		invoice.setAmount(amount);
		return invoice;
	}
}
