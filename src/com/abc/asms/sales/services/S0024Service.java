package com.abc.asms.sales.services;

import java.sql.Connection;
import java.sql.PreparedStatement;

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

			sql = "update sales set saledate=?, name=?, categoryname = ?, tradename = ? "
					+ "price = ?, salenumber = ?, note = ?,  where sale_id = ?";

			//UPDATE命令の準備
			ps = con.prepareStatement(sql);

			//UPDATE命令にポストデータの内容をセット
			ps.setString(1, form.getSaledate());
			ps.setString(2, form.getName());
			ps.setString(3, form.getCategoryname());
			ps.setString(4, form.getTradename());
			ps.setString(5, form.getPrice());
			ps.setString(6, form.getSalenumber());
			ps.setString(7, form.getNote());

			//UPDATE命令の実行
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con, ps, null);
		}

	}

}