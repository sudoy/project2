package com.abc.asms.categories.forms;

public class S0083Form {

	private String categoryid;
	private String categoryname;
	private String active;


	public S0083Form(String categoryid, String categoryname, String active) {
		super();
		this.categoryid = categoryid;
		this.categoryname = categoryname;
		this.active = active;
	}

	public S0083Form(String categoryid) {
		super();
		this.categoryid = categoryid;
	}


	public String getCategoryid() {
		return categoryid;
	}


	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}


	public String getCategoryname() {
		return categoryname;
	}


	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}


	public String getActive() {
		return active;
	}


	public void setActive(String active) {
		this.active = active;
	}



}
