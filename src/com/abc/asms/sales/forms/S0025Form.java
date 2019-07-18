package com.abc.asms.sales.forms;


public class S0025Form {
	private String id;
	private String saledate;
	private String accountid;
	private String categoryid;
	private String tradename;
	private String unitprice;
	private String salenumber;
	private String note;



	public S0025Form(String id, String saledate, String accountid, String categoryid, String tradename,
			String unitprice, String salenumber, String note) {
		super();
		this.id = id;
		this.saledate = saledate;
		this.accountid = accountid;
		this.categoryid = categoryid;
		this.tradename = tradename;
		this.unitprice = unitprice;
		this.salenumber = salenumber;
		this.note = note;

	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getCategoryid() {
		return categoryid;
	}


	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}


	public String getUnitprice() {
		return unitprice;
	}


	public void setUnitprice(String unitprice) {
		this.unitprice = unitprice;
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

	public String getTradename() {
		return tradename;
	}
	public void setTradename(String tradename) {
		this.tradename = tradename;
	}

}
