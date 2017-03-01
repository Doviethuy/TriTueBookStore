package com.aptech.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="invoice")
public class Invoice {
	@Id
	@Column(name ="ivid")
	private long ivId;
	@Column(name="amount")
	private long amount;
	@Column(name="createdate")
	private Date createDate;
	@Column(name="modifydate")
	private Date modifyDate;
	@Column(name="username")
	private String username;
	
	public Invoice() {
		// TODO Auto-generated constructor stub
	}

	public Invoice(long ivId, long amount, Date createDate, Date modifyDate, String username) {
		super();
		this.ivId = ivId;
		this.amount = amount;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.username = username;
	}

	public long getIvId() {
		return ivId;
	}

	public void setIvId(long ivId) {
		this.ivId = ivId;
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

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
