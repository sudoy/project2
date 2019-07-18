package com.abc.asms.sales.forms;

public class S0023Form {

	private String id;
	private String saledate;
	private String name;
	private String categoryname;
	private String tradename;
	private String price;
	private String salenumber;
	private String note;
	private int total;


	public S0023Form(String saledate, String name, String categoryname, String tradename, String price,
			String salenumber, String note, int total) {
		super();

		this.saledate = saledate;
		this.name = name;
		this.categoryname = categoryname;
		this.tradename = tradename;
		this.price = price;
		this.salenumber = salenumber;
		this.note = note;
	}


	public S0023Form(String id, String saledate, String name, String categoryname, String tradename, String price,
			String salenumber, String note) {
		super();
		this.id = id;
		this.saledate = saledate;
		this.name = name;
		this.categoryname = categoryname;
		this.tradename = tradename;
		this.price = price;
		this.salenumber = salenumber;
		this.note = note;
	}


	public S0023Form(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public void setSaledate(String saledate) {
		this.saledate = saledate;
	}
	public String getSaledate() {
		return saledate;
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
	public int getTotal() {
		return total;
	}


	public void setTotal(int total) {
		this.total = total;
	}


}
