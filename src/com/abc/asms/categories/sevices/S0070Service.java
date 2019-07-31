package com.abc.asms.categories.sevices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.abc.asms.categories.forms.S0070Form;
import com.abc.asms.goods.utils.DBUtils;

public class S0070Service {

	public boolean categoryexist(S0070Form form) {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		boolean exist = true;

		//DBから取った値
		String categoryname = null;

		try {
			//データベースの接続を確立
			con = DBUtils.getConnection();
			//sql
			sql = "SELECT category_name FROM categories WHERE category_name= ?";

			// プレースホルダに値を設定
			ps = con.prepareStatement(sql);

			ps.setString(1, form.getCategoryname());

			rs = ps.executeQuery();

			if (rs.next()) {

				categoryname = rs.getString("category_name");

				exist = false;//値が取れた時点でfalse
			}
			;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			DBUtils.close(con, ps, rs);
		}
		return exist;

	}

}
