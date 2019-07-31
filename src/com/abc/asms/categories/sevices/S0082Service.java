package com.abc.asms.categories.sevices;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.abc.asms.categories.forms.S0082Form;
import com.abc.asms.goods.utils.DBUtils;

public class S0082Service {

	public void update(S0082Form form) {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;

		try {
			//データベース接続
			con = DBUtils.getConnection();

			sql = "update categories set category_name=?, active_flg=? "
					+ "where category_id = ?";

			//UPDATE命令の準備
			ps = con.prepareStatement(sql);

			//UPDATE命令にポストデータの内容をセット
			ps.setString(1, form.getCategoryname());
			ps.setString(2, form.getActive());
			ps.setString(3, form.getCategoryid());

			//UPDATE命令の実行
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con, ps, null);
		}

	}

}
