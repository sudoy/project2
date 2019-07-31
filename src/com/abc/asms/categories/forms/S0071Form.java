package com.abc.asms.categories.forms;

public class S0071Form {

	private String categoryname;
	private String active;


	public S0071Form(String categoryname, String active) {
		super();

		this.categoryname = categoryname;
		this.active = active;
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
