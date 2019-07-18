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

	public static LocalDate today = null;//C0020Servletで参照するので
	private static LocalDate startDay = null;
	private static LocalDate lastDay = null;

	//現在の日付を取得して月始まりと月終わりも取得
	//DBからなんやかんやして一覧を取得
	public List<C0020Form> service(String userMail, String value, LocalDate todayArg) throws ServletException {

		if (value == null) {//今月

			today = todayArg;
			startDay = today.withDayOfMonth(1);
			lastDay = today.plusMonths(1).withDayOfMonth(1).minusDays(1);

		} else if (value.equals("-y1")) {//前年

			today = todayArg.minusYears(1);
			startDay = today.withDayOfMonth(1);
			lastDay = today.plusMonths(1).withDayOfMonth(1).minusDays(1);

		} else if (value.equals("-M1")) {//前月

			today = todayArg.minusMonths(1);
			startDay = today.withDayOfMonth(1);
			lastDay = today.plusMonths(1).withDayOfMonth(1).minusDays(1);

		} else if (value.equals("y1")) {//翌年

			today = todayArg.plusYears(1);
			startDay = today.withDayOfMonth(1);
			lastDay = today.plusMonths(1).withDayOfMonth(1).minusDays(1);

		} else if (value.equals("M1")) {//翌月

			today = todayArg.plusMonths(1);
			startDay = today.withDayOfMonth(1);
			lastDay = today.plusMonths(1).withDayOfMonth(1).minusDays(1);

		}

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
					+ " where ? <= sale_date and sale_date <= ? and a.mail = ?";

			ps = con.prepareStatement(sql);
			ps.setString(1, startDay.toString());
			ps.setString(2, lastDay.toString());
			ps.setString(3, userMail);

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

	public C0020Form returnVariousForm(String accountId) throws ServletException{

		//yy年MM月
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月");
		String yearMonth = dtf.format(startDay);

		//今月の売上合計を取得
		String thisMonth = returnTotal(startDay.toString(),lastDay.toString());

		LocalDate lastMonthStartDay = startDay.minusMonths(1);//前月の始まりの日
		LocalDate lastMonthLastDay = startDay.minusDays(1);//前月の最終日
		//前月の売上合計を取得
		String lastMonth = returnTotal(lastMonthStartDay.toString(),lastMonthLastDay.toString());

		//今月の個人の売上合計を取得
		String individualTotal = returnIndividualTotal(startDay.toString(),lastDay.toString(), accountId);

		//値が取得できたらparseInt
		int thisMonthInt = 0;
		int lastMonthInt = 0;
		if(thisMonth != null) {
			thisMonthInt = Integer.parseInt(thisMonth);
		}
		if(lastMonth != null) {
			lastMonthInt = Integer.parseInt(lastMonth);
		}

		//前月比
		double ratio = 0;
		if(thisMonthInt != 0 && lastMonthInt != 0) {
		 ratio = ((double)thisMonthInt / (double)lastMonthInt) * 100.0;
		}
		String stringRatio = String.format("%.2f", ratio);//小数第２位までにする

		C0020Form variousList = new C0020Form(yearMonth, String.valueOf(thisMonthInt), String.valueOf(lastMonthInt),
				stringRatio, individualTotal);

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
					+ " sale_date and sale_date <= ? where account_id = ? group by account_id";

			ps = con.prepareStatement(sql);
			ps.setString(1, startDay.toString());
			ps.setString(2, lastDay.toString());
			ps.setString(3, accountId);

			System.out.println(ps);

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

}