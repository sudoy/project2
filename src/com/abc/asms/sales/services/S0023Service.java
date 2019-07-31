package com.abc.asms.sales.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import com.abc.asms.goods.utils.DBUtils;
import com.abc.asms.goods.utils.HTMLUtils;
import com.abc.asms.sales.forms.S0023Form;

public class S0023Service {

	public S0023Form select(String num) {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		String id = null;
		String saledate = null;
		String name = null;
		String categoryname = null;
		String tradename = null;
		String price = null;
		String salenumber = null;
		String note = null;
		String version = null;

		try {
			//データベース接続
			con = DBUtils.getConnection();

			//SQL
			sql = "select s.sale_id, s.sale_date, a.name, c.category_name, s.trade_name,"
					+ " s.unit_price, s.sale_number, s.unit_price * s.sale_number as total , s.note, s.version"
					+ " from sales s left join categories c on s.category_id = c.category_id "
					+ " left join accounts a on s.account_id = a.account_id where sale_id = ?";

			//SELECT命令の準備
			ps = con.prepareStatement(sql);

			ps.setString(1, num);

			//SELECT命令の実行
			rs = ps.executeQuery();

			//結果セットの内容を出力(DBから抽出したデータ)
			while (rs.next()) {

				id = rs.getString("s.sale_id");
				saledate = HTMLUtils.formatDate(rs.getString("s.sale_date"));
				name = rs.getString("a.name");
				categoryname = rs.getString("c.category_name");
				tradename = rs.getString("s.trade_name");
				price = rs.getString("s.unit_price");
				salenumber = rs.getString("s.sale_number");
				note = rs.getString("s.note");
				version = rs.getString("s.version");

			}

			S0023Form form = new S0023Form(id, saledate, name, categoryname, tradename, price, salenumber, note,
					version);

			return form;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con, ps, rs);
		}
		return null;

	}

	//アカウント情報一覧取得
	public List<S0023Form> accounts() throws ServletException {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		String id = null;
		String name = null;

		try {
			//データベース接続
			con = DBUtils.getConnection();

			//SQL
			sql = "select account_id, name from accounts order by account_id";

			//SELECT命令の準備
			ps = con.prepareStatement(sql);

			//SELECT命令の実行
			rs = ps.executeQuery();

			List<S0023Form> accounts = new ArrayList<>();

			while (rs.next()) {

				id = rs.getString("account_id");
				name = rs.getString("name");

				S0023Form form = new S0023Form(id, name);

				accounts.add(form);

			}

			return accounts;

		} catch (Exception e) {
			throw new ServletException(e);

		} finally {
			DBUtils.close(con, ps, rs);
		}

	}

	//商品カテゴリー名一覧取得
	public List<String> categories() throws ServletException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		String categoryName = null;

		List<String> categories = new ArrayList<>();

		try {
			con = DBUtils.getConnection();
			sql = "select category_name from categories order by category_id";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {

				categoryName = rs.getString("category_name");

				categories.add(categoryName);
			}

			return categories;

		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			DBUtils.close(con, ps, rs);
		}
	}

	//カテゴリーidの取得
	public String selectCategoryid(String categoryname) throws ServletException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		try {
			//データベース接続
			con = DBUtils.getConnection();

			//SQL
			sql = "select category_id from categories where category_name = ?";

			//SELECT命令の準備
			ps = con.prepareStatement(sql);

			ps.setString(1, categoryname);

			//SELECT命令の実行
			rs = ps.executeQuery();
			String categoryid = null;

			while (rs.next()) {
				categoryid = rs.getString("category_id");
			}
			return categoryid;

		} catch (Exception e) {
			throw new ServletException(e);

		} finally {
			DBUtils.close(con, ps, rs);
		}
	}

	//アカウントテーブル存在チェック
	public boolean accountexist(S0023Form form) {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		boolean accountexist = false;

		String id = null;

		try {
			//データベースの接続を確立
			con = DBUtils.getConnection();

			sql = "select account_id from accounts where account_id = ?";

			// プレースホルダに値を設定
			ps = con.prepareStatement(sql);

			ps.setString(1, form.getId());

			rs = ps.executeQuery();

			if (rs.next()) {//値が取れたら

				id = rs.getString("account_id");

				accountexist = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			DBUtils.close(con, ps, rs);
		}
		return accountexist;

	}

	//カテゴリーテーブル存在チェック
	public boolean categoryexist(S0023Form form) {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		boolean categoryexist = true;

		String id = null;

		try {
			//データベースの接続を確立
			con = DBUtils.getConnection();

			sql = "select category_id from categories where category_id = ? and active_flg = 1";

			// プレースホルダに値を設定
			ps = con.prepareStatement(sql);

			ps.setString(1, form.getCategoryid());

			rs = ps.executeQuery();

			if (rs.next()) {

				id = rs.getString("category_id");

				categoryexist = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			DBUtils.close(con, ps, rs);
		}
		return categoryexist;

	}
}
