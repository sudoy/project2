package com.abc.asms.categories.sevices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;

import com.abc.asms.categories.forms.S0071Form;
import com.abc.asms.goods.utils.DBUtils;

public class S0071Service {

	public void insert(S0071Form form) {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		try {
			//データベースの接続を確立
			con = DBUtils.getConnection();
			//sql
			sql = "INSERT INTO categories(category_name, active_flg) VALUES (?, ?)";

			// プレースホルダに値を設定
			ps = con.prepareStatement(sql);

			ps.setString(1, form.getCategoryname());
			ps.setString(2, form.getActive());

			//select命令を実行
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con, ps, rs);
		}
	}

	public String Categoryid(S0071Form form) throws ServletException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		String categoryid = null;


		try {
			//データベース接続
			con = DBUtils.getConnection();

			//SQL(カテゴリー名は重複しない)
			sql = "select category_id from categories where category_name= ?";

			//SELECT命令の準備
			ps = con.prepareStatement(sql);

			ps.setString(1, form.getCategoryname());

			//SELECT命令の実行
			rs = ps.executeQuery();

			rs.next();
				categoryid = rs.getString("category_id");

			return categoryid;

		}catch(Exception e){
			e.printStackTrace();

		} finally {
			DBUtils.close(con, ps, rs);
		}
		return categoryid;
	}

}
