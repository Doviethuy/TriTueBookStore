package com.aptech.model;

import java.util.ArrayList;

public class ProductJsonObject {
	int iTotalRecords;
	int iTotalDisplayRecords;
	String sEcho;
	String sColumns;
	ArrayList<FakeProduct> aaData;
	public int getiTotalRecords() {
		return iTotalRecords;
	}
	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}
	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}
	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}
	public String getsEcho() {
		return sEcho;
	}
	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}
	public String getsColumns() {
		return sColumns;
	}
	public void setsColumns(String sColumns) {
		this.sColumns = sColumns;
	}
	public ArrayList<FakeProduct> getAaData() {
		return aaData;
	}
	public void setAaData(ArrayList<FakeProduct> aaData) {
		this.aaData = aaData;
	}
}
