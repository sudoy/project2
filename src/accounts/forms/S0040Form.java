package accounts.forms;

public class S0040Form {
	private int id;
	private String name;
	private String mail;
	private String authority;
	private String sale;
	private String account;

	public S0040Form(String name, String mail, String sale, String account) {
		super();
		this.name = name;
		this.mail = mail;
		this.sale = sale;
		this.account = account;
	}

	public S0040Form(int id, String name, String mail, String authority) {//引数の型が同じになってしまったのでintに
		super();
		this.id = id;
		this.name = name;
		this.mail = mail;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}


}
