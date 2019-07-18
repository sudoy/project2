package com.abc.asms.sales.forms;

public class S0021Form {
	private String saleId;
	private String saleDate;
	private String staff;
	private String categoryName;
	private String productName;
	private String unitPrice;
	private String saleNumber;
	private String total;
	public S0021Form(String saleId, String saleDate, String staff, String categoryName, String productName,
			String unitPrice, String saleNumber, String total) {
		super();
		this.saleId = saleId;
		this.saleDate = saleDate;
		this.staff = staff;
		this.categoryName = categoryName;
		this.productName = productName;
		this.unitPrice = unitPrice;
		this.saleNumber = saleNumber;
		this.total = total;
	}
	public String getSaleId() {
		return saleId;
	}
	public void setSaleId(String saleId) {
		this.saleId = saleId;
	}
	public String getSaleDate() {
		return saleDate;
	}
	public void setSaleDate(String saleDate) {
		this.saleDate = saleDate;
	}
	public String getStaff() {
		return staff;
	}
	public void setStaff(String staff) {
		this.staff = staff;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getSaleNumber() {
		return saleNumber;
	}
	public void setSaleNumber(String saleNumber) {
		this.saleNumber = saleNumber;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}



}
