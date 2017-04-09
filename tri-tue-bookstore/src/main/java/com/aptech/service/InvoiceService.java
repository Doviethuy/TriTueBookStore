package com.aptech.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aptech.dao.InvoiceDAO;
import com.aptech.model.Invoice;

@Service("invoiceService")
public class InvoiceService {
	@Autowired
	InvoiceDAO invoiceDAO;

	@Transactional
	public ArrayList<Invoice> getAllInvoice() {
		return invoiceDAO.getAllInvoice();
	}

	@Transactional
	public Invoice getInvoice(long ivId) {
		return invoiceDAO.getInvoice(ivId);
	}

	@Transactional
	public boolean addInvoice(Invoice invoice) {
		return invoiceDAO.addInvoice(invoice);
	}

	@Transactional
	public boolean updateInvoice(Invoice invoice) {
		return invoiceDAO.updateInvoice(invoice);
	}

	@Transactional
	public boolean deleteInvoice(int ivId) {
		return invoiceDAO.deleteInvoice(ivId);
	}

	@Transactional
	public List<Invoice> getInvoiceByUser(String user) {
		return invoiceDAO.getInvoiceByUser(user);
	}
	
	@Transactional
	public Invoice createInvoice(){
		return invoiceDAO.createInvoice();
	}
}
