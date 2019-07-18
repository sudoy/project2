package com.abc.asms.others.forms;

public class C0020Form {

	private String saleId;
	private String saleDate;
	private String name;
	private String categoryName;
	private String tradeName;
	private String unitPrice;
	private String saleNumber;
	private String total;

	private String yearMonth;
	private String thisMonthTotal;
	private String lastMonthTotal;
	private String ratio;


	public C0020Form(String yearMonth, String thisMonthTotal, String lastMonthTotal, String ratio) {
		super();
		this.yearMonth = yearMonth;
		this.thisMonthTotal = thisMonthTotal;
		this.lastMonthTotal = lastMonthTotal;
		this.ratio = ratio;
	}

	public C0020Form(String saleId, String saleDate, String name, String categoryName, String tradeName,
			String unitPrice, String saleNumber, String total) {
		super();
		this.saleId = saleId;
		this.saleDate = saleDate;
		this.name = name;
		this.categoryName = categoryName;
		this.tradeName = tradeName;
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
	public String getThisMonthTotal() {
		return thisMonthTotal;
	}
	public void setThisMonthTotal(String thisMonthTotal) {
		this.thisMonthTotal = thisMonthTotal;
	}
	public String getLastMonthTotal() {
		return lastMonthTotal;
	}
	public void setLastMonthTotal(String lastMonthTotal) {
		this.lastMonthTotal = lastMonthTotal;
	}
	public String getRatio() {
		return ratio;
	}
	public void setRatio(String ratio) {
		this.ratio = ratio;
	}

	public String getYearMonth() {
		return yearMonth;
	}

	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}



}
