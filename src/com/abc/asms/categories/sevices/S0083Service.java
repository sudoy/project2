package com.abc.asms.categories.sevices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.abc.asms.categories.forms.S0083Form;
import com.abc.asms.goods.utils.DBUtils;

public class S0083Service {

	//データの削除
	public void delete(S0083Form form) {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;

		try {
			//データベース接続
			con = DBUtils.getConnection();

			//SQL
			sql = "delete from categoriess where category_id = ?";

			//DELETE命令の準備
			ps = con.prepareStatement(sql);

			//DELETE命令にポストデータの内容をセット
			ps.setString(1, form.getCategoryid());

			//DELETEE命令の実行
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con, ps);
		}

	}

	public S0083Form select(String id) {

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

			S0083Form select = new S0083Form(categoryid, categoryname, active);

			return select;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con, ps, rs);
		}
		return null;

	}

}
