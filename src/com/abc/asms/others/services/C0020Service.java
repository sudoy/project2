package com.abc.asms.others.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import com.abc.asms.goods.utils.DBUtils;
import com.abc.asms.others.forms.C0020Form;

public class C0020Service {

	//日付の取得
	public C0020Form getDate(String value, LocalDate today) {

		if (value == null) {//今月
			//何もしない
		} else if (value.equals("-y1")) {//前年

			today = today.minusYears(1);

		} else if (value.equals("-M1")) {//前月

			today = today.minusMonths(1);

		} else if (value.equals("y1")) {//翌年

			today = today.plusYears(1);

		} else if (value.equals("M1")) {//翌月

			today = today.plusMonths(1);

		}

		LocalDate startDay = today.withDayOfMonth(1);
		LocalDate lastDay = today.plusMonths(1).withDayOfMonth(1).minusDays(1);

		C0020Form form = new C0020Form(today, startDay, lastDay);

		return form;

	}

	//現在の日付を取得して月始まりと月終わりも取得
	//DBからなんやかんやして一覧を取得
	public List<C0020Form> service(String userId, C0020Form date) throws ServletException {

		LocalDate startDay = date.getStartDay();
		LocalDate lastDay = date.getLastDay();

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		List<C0020Form> list = new ArrayList<>();

		try {
			con = DBUtils.getConnection();
			sql = "select s.sale_id, s.sale_date, a.name, c.category_name, s.trade_name,"
					+ " s.unit_price, s.sale_number, s.unit_price * s.sale_number as total , s.note"
					+ " from sales s join categories c on s.category_id = c.category_id"
					+ " join accounts a on s.account_id = a.account_id"
					+ " where ? <= sale_date and sale_date <= ? and a.account_id = ?"
					+ " order by s.sale_id asc";

			ps = con.prepareStatement(sql);
			ps.setString(1, startDay.toString());
			ps.setString(2, lastDay.toString());
			ps.setString(3, userId);

			rs = ps.executeQuery();

			C0020Form form = null;

			while (rs.next()) {
				String saleId = rs.getString("s.sale_id");
				String saleDate = rs.getString("s.sale_date");
				String name = rs.getString("a.name");
				String categoryName = rs.getString("c.category_name");
				String tradeName = rs.getString("s.trade_name");
				String unitPrice = rs.getString("s.unit_price");
				String saleNumber = rs.getString("s.sale_number");
				String total = rs.getString("total");

				form = new C0020Form(saleId, saleDate, name, categoryName, tradeName,
						unitPrice, saleNumber, total);
				list.add(form);
			}
			return list;

		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			DBUtils.close(con, ps, rs);
		}

	}

	public C0020Form returnVariousForm(String accountId, C0020Form date) throws ServletException {

		LocalDate startDay = date.getStartDay();
		LocalDate lastDay = date.getLastDay();

		//yy年M月
		DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy年M月");
		String yearMonth = dtf1.format(startDay);

		//今月の売上合計を取得
		String thisMonth = returnTotal(startDay.toString(), lastDay.toString());

		LocalDate lastMonthStartDay = startDay.minusMonths(1);//前月の始まりの日
		LocalDate lastMonthLastDay = startDay.minusDays(1);//前月の最終日
		//前月の売上合計を取得
		String lastMonth = returnTotal(lastMonthStartDay.toString(), lastMonthLastDay.toString());

		//今月の個人の売上合計を取得
		String individualTotal = returnIndividualTotal(startDay.toString(), lastDay.toString(), accountId);

		//値が取得できたらparseInt
		long thisMonthlong = 0;
		long lastMonthlong = 0;
		if (thisMonth != null) {
			thisMonthlong = Long.parseLong(thisMonth);
		}
		if (lastMonth != null) {
			lastMonthlong = Long.parseLong(lastMonth);
		}

		//前月比
		double ratio = 0;
		if (thisMonthlong != 0 && lastMonthlong != 0) {
			ratio = ((double) thisMonthlong / (double) lastMonthlong) * 100.0;
		}
		String stringRatio = String.format("%.2f", ratio);//小数第２位までにする

		DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("M月");
		//今月の月
		String thisM = dtf2.format(startDay);
		//前月の月
		String lastM = dtf2.format(lastMonthStartDay);

		C0020Form variousList = new C0020Form(yearMonth, String.valueOf(thisMonthlong), String.valueOf(lastMonthlong),
				stringRatio, individualTotal, thisM, lastM);

		return variousList;

	}

	//その月の売上合計を返す
	public String returnTotal(String startDay, String lastDay) throws ServletException {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		try {
			con = DBUtils.getConnection();
			sql = "select sum(unit_price * sale_number) as total from sales where ? <="
					+ " sale_date and sale_date <= ?";

			ps = con.prepareStatement(sql);
			ps.setString(1, startDay.toString());
			ps.setString(2, lastDay.toString());

			rs = ps.executeQuery();

			String total = null;
			while (rs.next()) {
				total = rs.getString("total");
			}

			return total;

		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			DBUtils.close(con, ps, rs);
		}
	}

	//ログインしている人のその月の売上合計を返す
	public String returnIndividualTotal(String startDay, String lastDay, String accountId) throws ServletException {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		try {
			con = DBUtils.getConnection();
			sql = "select sum(unit_price * sale_number) as total from sales where ? <="
					+ " sale_date and sale_date <= ? and account_id = ?";

			ps = con.prepareStatement(sql);
			ps.setString(1, startDay.toString());
			ps.setString(2, lastDay.toString());
			ps.setString(3, accountId);

			rs = ps.executeQuery();

			String individualTotal = null;
			while (rs.next()) {
				individualTotal = rs.getString("total");
			}

			return individualTotal;

		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			DBUtils.close(con, ps, rs);
		}
	}

	//月ごとの売上合計を取得してListに入れる（グラフ用）
	public List<String> getMonthlyTotal(LocalDate newYearsDay, LocalDate today) throws ServletException {

		LocalDate first = newYearsDay;
		LocalDate last = first.plusMonths(1).minusDays(1);

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		List<String> list = new ArrayList<>();

		for (int i = 1; i <= 12; i++) {//1月～12月まで

			if(first.isAfter(today)) {//今日の日付を超えたら終わり
				break;
			}

			try {
				con = DBUtils.getConnection();
				sql = "select sum(unit_price * sale_number) as total from sales"
						+ " where ? <= sale_date and sale_date <= ?";

				ps = con.prepareStatement(sql);
				ps.setString(1, first.toString());
				ps.setString(2, last.toString());

				rs = ps.executeQuery();

				String total = null;
				rs.next();
				if (rs.getString("total") == null) {//売上が一件も登録されていないとき
					total = "0";
				} else {//売上が一件以上登録されているとき
					total = rs.getString("total");
					//合計の千の位を四捨五入して××万円で表記するための操作
					double doubleTotal = Double.parseDouble(total);
					doubleTotal /= 10000;
					doubleTotal = Math.round(doubleTotal);
					total = String.valueOf(doubleTotal);
				}
				list.add(total);

			} catch (Exception e) {
				throw new ServletException(e);
			} finally {
				DBUtils.close(con, ps, rs);
			}

			first = first.plusMonths(1);//次の月の初日に
			last = first.plusMonths(1).minusDays(1);//次の月の最終日に

		}
		return list;

	}

}
