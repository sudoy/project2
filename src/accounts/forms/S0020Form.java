package accounts.forms;

public class S0020Form {
	private String dateBegin;
	private String dateEnd;
	private String name;
	private String[] cateName;//categoryNameが重複したので
	private String tradeName;
	private String note;

	private String accountId;
	private String accountName;

	private String saleId;
	private String saleDate;
	private String staff;//nameが重複していたので
	private String categoryName;
	private String productName;//tradeNameが重複していたので
	private String unitPrice;
	private String saleNumber;
	private String total;

	//普通に使うやつ
	public S0020Form(String dateBegin, String dateEnd, String name, String[] cateName, String tradeName,
			String note) {
		super();
		this.dateBegin = dateBegin;
		this.dateEnd = dateEnd;
		this.name = name;
		this.cateName = cateName;
		this.tradeName = tradeName;
		this.note = note;
	}

	//検索画面表示時の担当者を表示するためのやつ
	public S0020Form(String accountId, String accountName) {
		super();
		this.accountId = accountId;
		this.accountName = accountName;
	}

	//DBから値を取得して一覧を作るためのやつ

	public S0020Form(String saleId, String saleDate, String staff, String categoryName, String productName,
			String unitPrice, String saleNumber, String total) {
		super();
		this.saleId = saleId;
		this.saleDate = saleDate;
		this.staff = staff;
		this.categoryName = categoryName;
		this.productName = productName;
		this.unitPrice = unitPrice;
		this.saleNumber = saleNumber;
		this.total = total;
	}

	public String getId() {
		return accountId;
	}
	public void setId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getDateBegin() {
		return dateBegin;
	}
	public void setDateBegin(String dateBegin) {
		this.dateBegin = dateBegin;
	}
	public String getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String[] getCateName() {
		return cateName;
	}
	public void setCateName(String[] cateName) {
		this.cateName = cateName;
	}
	public String getTradeName() {
		return tradeName;
	}
	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getSaleId() {
		return saleId;
	}

	public void setSaleId(String saleId) {
		this.saleId = saleId;
	}

	public String getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(String saleDate) {
		this.saleDate = saleDate;
	}

	public String getStaff() {
		return staff;
	}

	public void setStaff(String staff) {
		this.staff = staff;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getSaleNumber() {
		return saleNumber;
	}

	public void setSaleNumber(String saleNumber) {
		this.saleNumber = saleNumber;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}


}
