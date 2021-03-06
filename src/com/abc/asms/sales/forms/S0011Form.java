package com.abc.asms.sales.forms;

public class S0011Form {
	private String saledate;
	private String accountid;
	private String categoryname;
	private String categoryid;
	private String tradename;
	private String price;
	private String salenumber;
	private String note;
	private String name;
	private String total;



	public S0011Form(String saledate, String accountid, String categoryid, String categoryname, String tradename,
			String price, String salenumber, String note, String name) {
		super();
		this.saledate = saledate;
		this.accountid = accountid;
		this.categoryid = categoryid;
		this.categoryname = categoryname;
		this.tradename = tradename;
		this.price = price;
		this.salenumber = salenumber;
		this.note = note;
		this.name = name;


	}

	public S0011Form(String saledate, String accountid, String categoryid, String tradename, String price,
			String salenumber, String note) {
		super();
		this.saledate = saledate;
		this.accountid = accountid;
		this.categoryid = categoryid;
		this.tradename = tradename;
		this.price = price;
		this.salenumber = salenumber;
		this.note = note;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}



	public String getSaledate() {
		return saledate;
	}
	public void setSaledate(String saledate) {
		this.saledate = saledate;
	}
	public String getAccountid() {
		return accountid;
	}
	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}
	public String getCategoryname() {
		return categoryname;
	}
	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}
	public String getTradename() {
		return tradename;
	}
	public void setTradename(String tradename) {
		this.tradename = tradename;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getSalenumber() {
		return salenumber;
	}

	public void setSalenumber(String salenumber) {
		this.salenumber = salenumber;
	}
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}




}
