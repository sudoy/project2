package com.abc.asms.others.forms;

import java.time.LocalDate;
import java.util.List;

public class C0020Form {

	private LocalDate today;
	private LocalDate startDay;
	private LocalDate lastDay;

	private String saleId;
	private String saleDate;
	private String name;
	private String categoryName;
	private String tradeName;
	private String unitPrice;
	private String saleNumber;
	private String total;

	private String yearMonth;
	private String thisMonthTotal;
	private String lastMonthTotal;
	private String ratio;
	private String individualTotal;
	private String thisM;
	private String lastM;

	List<String> thisGraph;
	List<String> lastGraph;
	private String thisYear;
	private String lastYear;


	//グラフ用のデータを入れる
	public C0020Form(List<String> thisGraph, List<String> lastGraph, String thisYear, String lastYear) {
		super();
		this.thisGraph = thisGraph;
		this.lastGraph = lastGraph;
		this.thisYear = thisYear;
		this.lastYear = lastYear;
	}

	//getDate()で使う
	public C0020Form(LocalDate today, LocalDate startDay, LocalDate lastDay) {
		super();
		this.today = today;
		this.startDay = startDay;
		this.lastDay = lastDay;
	}

	//returnVariousForm()で使う
	public C0020Form(String yearMonth, String thisMonthTotal, String lastMonthTotal, String ratio,
			String individualTotal,String thisM, String lastM) {
		super();
		this.yearMonth = yearMonth;
		this.thisMonthTotal = thisMonthTotal;
		this.lastMonthTotal = lastMonthTotal;
		this.ratio = ratio;
		this.individualTotal = individualTotal;
		this.thisM = thisM;
		this.lastM = lastM;
	}


	//service()で使う
	public C0020Form(String saleId, String saleDate, String name, String categoryName, String tradeName,
			String unitPrice, String saleNumber, String total) {
		super();
		this.saleId = saleId;
		this.saleDate = saleDate;
		this.name = name;
		this.categoryName = categoryName;
		this.tradeName = tradeName;
		this.unitPrice = unitPrice;
		this.saleNumber = saleNumber;
		this.total = total;

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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getTradeName() {
		return tradeName;
	}
	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
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
	public String getThisMonthTotal() {
		return thisMonthTotal;
	}
	public void setThisMonthTotal(String thisMonthTotal) {
		this.thisMonthTotal = thisMonthTotal;
	}
	public String getLastMonthTotal() {
		return lastMonthTotal;
	}
	public void setLastMonthTotal(String lastMonthTotal) {
		this.lastMonthTotal = lastMonthTotal;
	}
	public String getRatio() {
		return ratio;
	}
	public void setRatio(String ratio) {
		this.ratio = ratio;
	}

	public String getYearMonth() {
		return yearMonth;
	}

	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}

	public String getIndividualTotal() {
		return individualTotal;
	}

	public void setIndividualTotal(String individualTotal) {
		this.individualTotal = individualTotal;
	}

	public String getThisM() {
		return thisM;
	}

	public void setThisM(String thisM) {
		this.thisM = thisM;
	}

	public String getLastM() {
		return lastM;
	}

	public void setLastM(String lastM) {
		this.lastM = lastM;
	}

	public LocalDate getToday() {
		return today;
	}

	public void setToday(LocalDate today) {
		this.today = today;
	}

	public LocalDate getStartDay() {
		return startDay;
	}

	public void setStartDay(LocalDate startDay) {
		this.startDay = startDay;
	}

	public LocalDate getLastDay() {
		return lastDay;
	}

	public void setLastDay(LocalDate lastDay) {
		this.lastDay = lastDay;
	}

	public List<String> getThisGraph() {
		return thisGraph;
	}

	public void setThisGraph(List<String> thisGraph) {
		this.thisGraph = thisGraph;
	}

	public List<String> getLastGraph() {
		return lastGraph;
	}

	public void setLastGraph(List<String> lastGraph) {
		this.lastGraph = lastGraph;
	}

	public String getThisYear() {
		return thisYear;
	}

	public void setThisYear(String thisYear) {
		this.thisYear = thisYear;
	}

	public String getLastYear() {
		return lastYear;
	}

	public void setLastYear(String lastYear) {
		this.lastYear = lastYear;
	}


}
