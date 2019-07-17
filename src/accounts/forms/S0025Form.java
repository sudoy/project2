package accounts.forms;


public class S0025Form {
	private String saledate;
	private String accountid;
	private String tradename;



	public S0025Form(String saledate, String accountid, String tradename) {
		super();
		this.saledate = saledate;
		this.accountid = accountid;
		this.tradename = tradename;
	}


	public String getSaledate() {
		return saledate;
	}
	public void setSaledate(String saledate) {
		this.saledate = saledate;
	}
	public String getAccountid() {
		return accountid;
	}
	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

	public String getTradename() {
		return tradename;
	}
	public void setTradename(String tradename) {
		this.tradename = tradename;
	}

}
