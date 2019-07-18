package com.abc.asms.sales.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;

import com.abc.asms.goods.utils.DBUtils;
import com.abc.asms.sales.forms.S0024Form;

public class S0024Service {

	//データ更新
	public void update(S0024Form form) {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;

		try {
			//データベース接続
			con = DBUtils.getConnection();

			sql = "update sales set sale_date=?, trade_name = ?, "
					+ "unit_price = ?, sale_number = ?, note = ?, account_id = ?, category_id = ? where sale_id = ?";

			//UPDATE命令の準備
			ps = con.prepareStatement(sql);

			//UPDATE命令にポストデータの内容をセット
			ps.setString(1, form.getSaledate());
			ps.setString(2, form.getTradename());
			ps.setString(3, form.getPrice());
			ps.setString(4, form.getSalenumber());
			ps.setString(5, form.getNote());
			ps.setString(6, form.getAccountid());
			ps.setString(7, form.getCategoryid());
			ps.setString(8, form.getSaleid());


			System.out.println(form.getSaledate());
			System.out.println(form.getCategoryname());
			System.out.println(form.getPrice());

			//UPDATE命令の実行
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con, ps, null);
		}

	}

	//nameをaccountidに変更
	public String selectid(String name) throws ServletException {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		String id = null;

		try {
			//データベース接続
			con = DBUtils.getConnection();

			//SQL
			sql = "select account_id from accounts where name = ?";

			//SELECT命令の準備
			ps = con.prepareStatement(sql);

			ps.setString(1, name);

			//SELECT命令の実行
			rs = ps.executeQuery();

			while (rs.next()) {

				id = rs.getString("account_id");

			}

			return id;

		} catch (Exception e) {
			throw new ServletException(e);

		} finally {
			DBUtils.close(con, ps, rs);
		}

	}

	//categorynameをcategoryidに変換
	public String selectcategoryid(String name) throws ServletException {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		String id = null;

		try {
			//データベース接続
			con = DBUtils.getConnection();

			//SQL
			sql = "select category_id from categories where category_name = ?";

			//SELECT命令の準備
			ps = con.prepareStatement(sql);

			ps.setString(1, name);

			//SELECT命令の実行
			rs = ps.executeQuery();

			while (rs.next()) {

				id = rs.getString("category_id");

			}

			return id;

		} catch (Exception e) {
			throw new ServletException(e);

		} finally {
			DBUtils.close(con, ps, rs);
		}

	}


}
