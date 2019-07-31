package com.abc.asms.categories.forms;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.abc.asms.goods.utils.DBUtils;

public class S0063Service {

	public void delete(S0063Form s0063form) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;

		try {
			//データベース接続
			con = DBUtils.getConnection();

			//SQL
			sql = "delete from categories where category_id = ?";

			//DELETE命令の準備
			ps = con.prepareStatement(sql);

			//DELETE命令にポストデータの内容をセット
			ps.setString(1, s0063form.getId());

			//DELETEE命令の実行
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con, ps);
		}
	}

}
