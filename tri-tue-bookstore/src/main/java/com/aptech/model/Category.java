package com.aptech.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="category")
public class Category {
	@Id
	@Column(name="cateid")
	private int cateId;
	@Column(name="catename")
	private String cateName;
	
	public Category() {
		// TODO Auto-generated constructor stub
	}
	
	public Category(int cateId, String cateName) {
		super();
		this.cateId = cateId;
		this.cateName = cateName;
	}

	public int getCateId() {
		return cateId;
	}
	public void setCateId(int cateId) {
		this.cateId = cateId;
	}
	public String getCateName() {
		return cateName;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	
}
