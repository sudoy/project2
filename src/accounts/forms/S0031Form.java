package accounts.forms;

public class S0031Form {
	private String name;
	private String mail;
	private String password;
	private String check;
	private String sale;
	private String account;
	private String authority;



	public S0031Form(String name, String mail, String password, String check, String sale, String account) {
		super();
		this.name = name;
		this.mail = mail;
		this.password = password;
		this.check = check;
		this.sale = sale;
		this.account = account;




	}
	public S0031Form(String name, String mail, String password) {
		super();
		this.name = name;
		this.mail = mail;
		this.password = password;

	}
	public String getCheck() {
		return check;
	}
	public void setCheck(String check) {
		this.check = check;
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
	public String getName() {
		return name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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



}
