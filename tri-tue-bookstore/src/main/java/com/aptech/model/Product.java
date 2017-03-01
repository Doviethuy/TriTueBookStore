package com.aptech.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class Product {
	@Id
	@Column(name="proid")
	private long proId;
	@Column(name="proname")
	private String proName;
	@Column(name="cateid")
	private long cateId;
	@Column(name="price")
	private long price;
	@Column(name="quantity")
	private int quantity;
	@Column(name="img")
	private String img;
	@Column(name="description")
	private String description;
	@Column(name="createdate")
	private Date createDate;
	@Column(name="modifydate")
	private Date modifyDate;
	
	public Product() {
		// TODO Auto-generated constructor stub
	}

	public Product(long proId, String proName, long cateId, long price, int quantity, String img, String description,
			Date createDate, Date modifyDate) {
		super();
		this.proId = proId;
		this.proName = proName;
		this.cateId = cateId;
		this.price = price;
		this.quantity = quantity;
		this.img = img;
		this.description = description;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
	}

	public long getProId() {
		return proId;
	}

	public void setProId(long proId) {
		this.proId = proId;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public long getCateId() {
		return cateId;
	}

	public void setCateId(long cateId) {
		this.cateId = cateId;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
}
