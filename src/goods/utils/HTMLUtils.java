package goods.utils;

import java.util.HashMap;
import java.util.Map;

public class HTMLUtils {

	public static String formatDate(String date) {

		String slashDate = date;
		if(date != null) {
			slashDate = date.replace("-", "/");
		}
		return slashDate;
	}

	public static Map<String, String> formatAuthority(String sale, String account) {

		Map<String, String> map = new HashMap<>();

		if (sale.equals("1")) {
			if (account.equals("1")) {
				map.put("authority", " and authority = 11");

			} else if (account.equals("0")) {
				map.put("authority", " and authority = 1");

			} else {
				map.put("authority1", " and authority = 1");
				map.put("authority3", " or authority = 11");

			}

		} else if (sale.equals("0")) {
			if (account.equals("1")) {
				map.put("authority", " and authority = 10");

			} else if (account.equals("0")) {
				map.put("authority", " and authority = 0");

			} else {
				map.put("authority1", " and authority = 0");
				map.put("authority2", " or authority = 10");

			}

		} else {
			if (account.equals("1")) {
				map.put("authority1", " and authority = 10");
				map.put("authority2", " or authority = 11");

			} else if (account.equals("0")) {
				map.put("authority1", " and authority = 0");
				map.put("authority2", " or authority = 1");

			} else {
				map.put("authority", "");
			}

		}

		return map;

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
