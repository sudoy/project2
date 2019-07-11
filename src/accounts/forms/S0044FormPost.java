package accounts.forms;

public class S0044FormPost {

	private String id;
	private String name;
	private String mail;
	private String password;
	private String authrity;

	public S0044FormPost(String name, String mail, String password, String authrity) {
		super();
		this.name = name;
		this.mail = mail;
		this.password = password;
		this.authrity = authrity;
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


	public String getAuthrity() {
		return authrity;
	}


	public void setAuthrity(String authrity) {
		this.authrity = authrity;
	}



}
