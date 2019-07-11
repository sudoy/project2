package accounts.forms;

public class S0043Form{


	private String id;
	private String name;
	private String mail;
	private String password;
	private String authority;

	public S0043Form(String id, String name, String mail, String password,String authority) {
		super();

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



}
