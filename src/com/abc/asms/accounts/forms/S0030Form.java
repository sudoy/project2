package com.abc.asms.accounts.forms;

public class S0030Form {
	private String name;
	private String mail;
	private String password;
	private String authority;
	private String sale;
	private String account;


	public S0030Form(String name, String mail, String sale, String account) {
		super();
		this.name = name;
		this.mail = mail;
		this.sale = sale;
		this.account = account;
	}




//修正中
	public S0030Form() {
	}





	public String getSale() {
		return sale;
	}
	public void setSale(String sale) {
		this.sale = sale;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}

	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}






}
