package com.aptech.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aptech.dao.InvoiceDetailDAO;
import com.aptech.model.InvoiceDetail;

@Service("invoiceDetailService")
public class InvoiceDetailService {
	@Autowired
	InvoiceDetailDAO invoiceDetailDAO;

	@Transactional
	public ArrayList<InvoiceDetail> getAllInvoiceDetail() {
		return invoiceDetailDAO.getAllInvoiceDetail();
	}

	@Transactional
	public InvoiceDetail getInvoiceDetail(int ivId, int proId) {
		return invoiceDetailDAO.getInvoiceDetail(ivId, proId);
	}

	@Transactional
	public InvoiceDetail addInvoiceDetail(InvoiceDetail invoiceDetail) {
		return invoiceDetailDAO.addInvoiceDetail(invoiceDetail);
	}

	@Transactional
	public boolean updateInvoiceDetail(InvoiceDetail invoiceDetail) {
		return invoiceDetailDAO.updateInvoiceDetail(invoiceDetail);
	}

	@Transactional
	public boolean deleteInvoiceDetail(int ivId, int proId) {
		return invoiceDetailDAO.deleteInvoiceDetail(ivId, proId);
	}
}
