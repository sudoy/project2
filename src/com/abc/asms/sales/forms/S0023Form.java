package com.abc.asms.sales.forms;

public class S0023Form {

	private String id;
	private String saledate;
	private String name;
	private String categoryid;
	private String categoryname;
	private String tradename;
	private String price;
	private String salenumber;
	private String note;
	private long total;
	private String version;


	public S0023Form(String id, String saledate, String name, String categoryid, String categoryname, String tradename, String price,
			String salenumber, String note, long total, String version) {
		super();
		this.id = id;
		this.saledate = saledate;
		this.name = name;
		this.categoryid = categoryid;
		this.categoryname = categoryname;
		this.tradename = tradename;
		this.price = price;
		this.salenumber = salenumber;
		this.note = note;
		this.total = total;
		this.version = version;
	}

	public S0023Form(String id, String saledate, String name, String categoryname, String tradename, String price,
			String salenumber, String note, String version) {
		super();
		this.id = id;
		this.saledate = saledate;
		this.name = name;
		this.categoryname = categoryname;
		this.tradename = tradename;
		this.price = price;
		this.salenumber = salenumber;
		this.note = note;
		this.version = version;
	}

	public S0023Form(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getCategoryid() {
		return categoryid;
	}


	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
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
	public long getTotal() {
		return total;
	}


	public void setTotal(long total) {
		this.total = total;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}


}
