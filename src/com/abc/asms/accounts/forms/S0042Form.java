package com.abc.asms.accounts.forms;

public class S0042Form {

	private String id;
	private String name;
	private String mail;
	private String password;
	private String authority;
	private String version;
	private String check;
	private String sale;
	private String account;

	public S0042Form(String id, String name, String mail, String password, String authority, String version) {
		super();

		this.id = id;
		this.name = name;
		this.mail = mail;
		this.password = password;
		this.authority = authority;
		this.version = version;
	}

	public S0042Form(String id, String name, String mail, String password, String check, String sale,
			String account, String authority, String version) {
		super();

		this.id = id;
		this.name = name;
		this.mail = mail;
		this.password = password;
		this.check = check;
		this.sale = sale;
		this.account = account;
		this.setAuthority(authority);
		this.version = version;
	}


	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}



