package com.abc.asms.sales.forms;

public class S0010Form {
	public  String name;
	private String saledate;
	private String accountid;
	private String categoryid;
	private String categoryname;
	private String tradename;
	private String price;
	private String salenumber;
	private String note;
	private String saleid;

	private String today;


	public S0010Form(String today) {
		super();
		this.today = today;
	}
	public S0010Form(String saledate, String accountid, String categoryname, String tradename, String price,
			String salenumber, String note, String name) {
		super();
		this.saledate = saledate;
		this.accountid = accountid;
		this.categoryname = categoryname;
		this.tradename = tradename;
		this.price = price;
		this.salenumber = salenumber;
		this.note = note;
		this.name = name;
	}
	public S0010Form() {
		super();
	}
	public String getSaleid() {
		return saleid;
	}
	public void setSaleid(String saleid) {
		this.saleid = saleid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
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
	public String getToday() {
		return today;
	}
	public void setToday(String today) {
		this.today = today;
	}
	public String getCategoryname() {
		return categoryname;
	}
	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}



}
