package com.abc.asms.sales.forms;

public class S0024Form {

	private String saleid;
	private String saledate;
	private String name;
	private String categoryname;
	private String tradename;
	private String price;
	private String salenumber;
	private String note;
	private String total;

	public S0024Form(String saleid, String saledate, String tradename, String price,
			String salenumber, String note) {
		super();
		this.saleid = saleid;
		this.saledate = saledate;
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

	public String getSaleid() {
		return saleid;
	}

	public void setSaleid(String saleid) {
		this.saleid = saleid;
	}

	public String getSaledate() {
		return saledate;
	}

	public void setSaledate(String saledate) {
		this.saledate = saledate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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


}
