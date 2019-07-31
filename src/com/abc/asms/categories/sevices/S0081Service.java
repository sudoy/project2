package com.abc.asms.categories.sevices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.abc.asms.categories.forms.S0081Form;
import com.abc.asms.goods.utils.DBUtils;

public class S0081Service {

	public S0081Form select(String id) {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		String categoryid = null;
		String categoryname = null;
		String active = null;

		try {
			//データベース接続
			con = DBUtils.getConnection();

			//SQL
			sql = "select category_id, category_name, active_flg from categories where category_id = ? order by category_id";

			//SELECT命令の準備
			ps = con.prepareStatement(sql);

			ps.setString(1, id);

			//SELECT命令の実行
			rs = ps.executeQuery();

			//結果セットの内容を出力(DBから抽出したデータ)
			while (rs.next()) {

				categoryid = rs.getString("category_id");
				categoryname = rs.getString("category_name");
				active = rs.getString("active_flg");


			}

			S0081Form select = new S0081Form(categoryid, categoryname, active);

			return select;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con, ps, rs);
		}
		return null;

	}

	public boolean categoryexist(S0081Form form) {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		boolean exist = true;

		//編集後の値
		String categoryid = form.getCategoryid() ;
		String categoryname = form.getCategoryname();
		//DBから取った値
		String dbId = null;
		String dbName = null;

		try {
			//データベースの接続を確立
			con = DBUtils.getConnection();
			//sql
			sql = "SELECT category_id, category_name FROM categories WHERE category_name= ?";

			// プレースホルダに値を設定
			ps = con.prepareStatement(sql);

			ps.setString(1, form.getCategoryname());

			rs = ps.executeQuery();

			if (rs.next()) {

				categoryname = rs.getString("category_name");

				if(categoryname.equals("dbName") && !categoryid.equals("dbId")) {
					exist = false;

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
