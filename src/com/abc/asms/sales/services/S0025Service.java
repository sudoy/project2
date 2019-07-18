package com.abc.asms.sales.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.abc.asms.goods.utils.DBUtils;
import com.abc.asms.sales.forms.S0025Form;

public class S0025Service {
	public S0025Form select(String num) {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		String id = null;
		String saledate = null;
		String accountid = null;
		String categoryid = null;
		String tradename = null;
		String unitprice = null;
		String salenumber = null;
		String note = null;

		try {
			//データベース接続
			con = DBUtils.getConnection();

			//SQL
			sql = "select sale_id, sale_date, account_id, category_id, trade_name, unit_price, sale_number, note"
					+ " from sales"
					+ " where sale_id = ?"
					+ " order by sale_id";


			//SELECT命令の準備
			ps = con.prepareStatement(sql);

			ps.setString(1, num);


			//SELECT命令の実行
			rs = ps.executeQuery();
			System.out.println(rs);

			//結果セットの内容を出力(DBから抽出したデータ)
			while(rs.next()) {

				id = rs.getString("sale_id");
				saledate = rs.getString("sale_date");
				accountid = rs.getString("account_id");
				categoryid = rs.getString("category_id");
				tradename = rs.getString("trade_name");
				unitprice = rs.getString("unit_price");
				salenumber = rs.getString("sale_number");
				note = rs.getString("note");

				System.out.println(id);

			}

			S0025Form form = new S0025Form(id, saledate, accountid, categoryid, tradename, unitprice, salenumber, note);

			return form;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con, ps, rs);
		}
		return null;

	}

	//データの削除
	public void delete(S0025Form form) {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;

		try {
			//データベース接続
			con = DBUtils.getConnection();

			//SQL
			sql = "delete from sales where sale_id = ?";

			//DELETE命令の準備
			ps = con.prepareStatement(sql);

			//DELETE命令にポストデータの内容をセット
			ps.setString(1, form.getId());

			//DELETEE命令の実行
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con, ps);
		}

	}

}
