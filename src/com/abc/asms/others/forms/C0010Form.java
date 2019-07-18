package com.abc.asms.others.forms;

public class C0010Form {

	private boolean login;

	private String id;
	private String name;
	private String mail;
	private String password;
	private String authority;


//jspから取得したmailとpasswordをServiceに送るときに使う（あと初期表示）
	public C0010Form(String mail, String password) {
		super();
		this.mail = mail;
		this.password = password;
	}


//ログイン状態 + ユーザー情報
	public C0010Form(boolean login, String id, String name, String mail, String password, String authority) {
		super();
		this.login = login;
		this.id = id;
		this.name = name;
		this.mail = mail;
		this.password = password;
		this.authority = authority;
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
	public boolean isLogin() {
		return login;
	}

	public void setLogin(boolean login) {
		this.login = login;
	}



}
