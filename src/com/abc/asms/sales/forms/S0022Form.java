package com.abc.asms.sales.forms;

public class S0022Form {
	private String saleId;
	private String saleDate;
	private String name;
	private String categoryName;
	private String tradeName;
	private String unitPrice;
	private String saleNumber;
	private String total;
	private String note;


	public S0022Form(String saleId, String saleDate, String name, String categoryName, String tradeName,
			String unitPrice, String saleNumber, String total, String note) {
		super();
		this.saleId = saleId;
		this.saleDate = saleDate;
		this.name = name;
		this.categoryName = categoryName;
		this.tradeName = tradeName;
		this.unitPrice = unitPrice;
		this.saleNumber = saleNumber;
		this.total = total;
		this.note = note;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getTradeName() {
		return tradeName;
	}
	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
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
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

}
