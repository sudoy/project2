package accounts.forms;

public class S0046Form {

	private String id;
	private String mail;
	private String password;
	private String check;


	public S0046Form(String mail,String password, String check) {
		super();
		this.mail = mail;
		this.password = password;
		this.check = check;
	}

	public S0046Form(String mail) {
		super();
		this.mail = mail;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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

	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}
}
