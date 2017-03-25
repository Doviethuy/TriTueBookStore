package com.aptech.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USER")
public class User {
	@Id
	@Column(name="username")
	String userName;
	
	@Column(name="password")
	String password;
	
	@Column(name="name")
	String name;
	
	@Column(name="dob")
	Date dob;
	
	@Column(name="gender")
	byte gender;
	
	@Column(name="address")
	String address;
	
	@Column(name="phone")
	String phone;
	
	@Column(name="img")
	String img;
	
	@Column(name="description")
	String description;
	
	@Column(name="createdate")
	Date createDate;
	
	@Column(name="modifydate")
	Date modifyDate;
	
	@Column(name="role")
	byte role;
	
	public User() {
		super();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public byte getGender() {
		return gender;
	}

	public void setGender(byte gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public byte getRole() {
		return role;
	}

	public void setRole(byte role) {
		this.role = role;
	}

	public User(String userName, String password, String name, Date dob, byte gender, String address, String phone, String img, String description, Date createDate, Date modifyDate, byte role) {
		super();
		this.userName = userName;
		this.password = password;
		this.name = name;
		this.dob = dob;
		this.gender = gender;
		this.address = address;
		this.phone = phone;
		this.img = img;
		this.description = description;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + ", name=" + name + ", dob=" + dob + ", gender=" + gender + ", address=" + address + ", phone=" + phone + ", img=" + img + ", description=" + description + ", createDate=" + createDate + ", modifyDate=" + modifyDate + ", role=" + role + "]";
	}	
		
}
