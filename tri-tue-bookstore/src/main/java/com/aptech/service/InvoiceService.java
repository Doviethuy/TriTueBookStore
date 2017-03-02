package com.aptech.service;

import java.util.ArrayList;

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
	public ArrayList<Invoice> getAllInvoice(){
		return invoiceDAO.getAllInvoice();
	}
	
	@Transactional
	public Invoice getInvoice(int ivId){
		return invoiceDAO.getInvoice(ivId);
	}
	
	@Transactional
	public Invoice addInvoice(Invoice invoice){
		return invoiceDAO.addInvoice(invoice);
	}
	
	@Transactional
	public void updateInvoice(Invoice invoice){
		invoiceDAO.updateInvoice(invoice);
	}
	
	@Transactional
	public void deleteInvoice(int ivId){
		invoiceDAO.deleteInvoice(ivId);
	}
}
