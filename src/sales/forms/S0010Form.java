package sales.forms;

public class S0010Form {
	private String saledate;
	private String accountid;
	private String categoryid;
	private String tradename;
	private String price;
	private String salenumber;
	private String note;


	public S0010Form(String saledate, String accountid, String categoryid, String tradename, String price, String salenumber, String note) {
		super();
		this.saledate = saledate;
		this.accountid = accountid;
		this.categoryid = categoryid;
		this.tradename = tradename;
		this.price = price;
		this.salenumber = salenumber;
		this.note = note;

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
	public String getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}
	public String getTradename() {
		return tradename;
	}
	public void setTradename(String tradename) {
		this.tradename = tradename;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getSalenumber() {
		return salenumber;
	}
	public void setSalenumber(String salenumber) {
		this.salenumber = salenumber;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}



}
