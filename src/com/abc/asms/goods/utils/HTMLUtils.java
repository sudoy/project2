package com.abc.asms.goods.utils;

import java.text.NumberFormat;

public class HTMLUtils {

	public static String formatDate(String date) {//日付の-を/に
		String slashDate = date;
		if (date != null) {
			slashDate = date.replace("-", "/");
		}
		return slashDate;
	}

	public static String formatTotal(String total) {//totalをカンマ区切りに
		if (total != null && !(total.equals(""))) {
			NumberFormat nf = NumberFormat.getNumberInstance();
			return nf.format(Integer.parseInt(total));
		} else {
			return total;
		}
	}

	public static String formatAuthority(String sale, String account) {

		String authoritySQL;

		if (sale.equals("1")) {
			if (account.equals("1")) {
				authoritySQL = " and authority = 11";

			} else if (account.equals("0")) {
				authoritySQL = " and authority = 1";

			} else {
				authoritySQL = " and (authority = 1 or authority = 11)";

			}

		} else if (sale.equals("0")) {
			if (account.equals("1")) {
				authoritySQL = " and authority = 10";

			} else if (account.equals("0")) {
				authoritySQL = " and authority = 0";

			} else {
				authoritySQL = " and (authority = 0 or authority = 10)";

			}

		} else {
			if (account.equals("1")) {
				authoritySQL = " and authority = 10 or authority = 11";

			} else if (account.equals("0")) {
				authoritySQL = " and (authority = 0 or authority = 1)";

			} else {
				authoritySQL = "";
			}

		}

		return authoritySQL;

	}

	public static String expressAuthority(String authority) {
		if (authority.equals("0")) {
			return "権限なし";

		} else if (authority.equals("1")) {
			return "売上登録";
		} else if (authority.equals("10")) {
			return "アカウント登録";
		} else {//11
			return "売上登録/アカウント登録";
		}

	}

	public static String judgeAccountAuthority(String value1, String value2) {

		if (value2.equals("")) {
			value2 = "all";
		}

		if (value1.equals(value2)) {
			return "checked";
		} else {
			return "";
		}
	}

	public static String judgeSaleAuthority(String value1, String value2) {

		if (value2.equals("")) {
			value2 = "all";
		}

		if (value1.equals(value2)) {
			return "checked";
		} else {
			return "";
		}
	}

	public static String judgeStaffSelected(String staff, String select) {

		if (staff.equals(select)) {
			return "selected";
		} else {
			return "";
		}
	}

	public static String judgeCategoryChecked(String category, String[] check) {

		String judge = "";
		if (check != null) {
			for (int i = 0; i < check.length; i++) {
				if (category.equals(check[i])) {
					judge = "checked";
				}
			}
		}
		return judge;
	}

	public static String judgeCategoryChecked2(String categoryid, String i) {

		if (categoryid.equals(i)) {
			return "checked";
		} else {
			return "";
		}
	}

}