package com.abc.asms.categories.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.abc.asms.categories.forms.S0061Form;
import com.abc.asms.goods.utils.DBUtils;

public class S0061Service {
	public boolean exist(S0061Form s0061form) {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		boolean exist = true;

		String formid = s0061form.getId();//編集後のid
		String formname = s0061form.getName();//編集後のname
		String id = null;//DBからとったid
		String name = null;//DBからとったname

		try {
			//データベースの接続を確立
			con = DBUtils.getConnection();
			//sql
			sql = "SELECT category_id, category_name From categories WHERE category_name = ?";

			// プレースホルダに値を設定
			ps = con.prepareStatement(sql);

			ps.setString(1, s0061form.getName());

			rs = ps.executeQuery();

			if (rs.next()) {

				id = rs.getString("category_id");
				name = rs.getString("category_name");




				if (formname.equals(name) && !formid.equals(id)) {
					exist = false;//被りあり
				}
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
