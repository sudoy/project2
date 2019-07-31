package com.abc.asms.categories.forms;

public class S0061Form {
	private String name;
	private String flg;
	private String id;

	public S0061Form(String name, String flg, String id) {
		super();
		this.name = name;
		this.flg = flg;
		this.id = id;
	}

	public S0061Form(String name, String flg) {
		super();
		this.name = name;
		this.flg = flg;
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

	public String getFlg() {
		return flg;
	}

	public void setFlg(String flg) {
		this.flg = flg;
	}


}
