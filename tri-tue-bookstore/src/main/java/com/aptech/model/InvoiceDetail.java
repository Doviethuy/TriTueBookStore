package com.aptech.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "invoicedetail")
public class InvoiceDetail {
	@Id
	@Column(name = "ivid")
	private long ivId;

	@Column(name = "proid")
	private long proId;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "amount")
	private long amount;

	@Column(name = "createdate")
	private Date createDate;

	public InvoiceDetail() {
	}

	public InvoiceDetail(long ivId, long proId, int quantity, long amount, Date createDate) {
		super();
		this.ivId = ivId;
		this.proId = proId;
		this.quantity = quantity;
		this.amount = amount;
		this.createDate = createDate;
	}

	public long getIvId() {
		return ivId;
	}

	public void setIvId(long ivId) {
		this.ivId = ivId;
	}

	public long getProId() {
		return proId;
	}

	public void setProId(long proId) {
		this.proId = proId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
