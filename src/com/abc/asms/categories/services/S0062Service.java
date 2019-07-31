package com.abc.asms.categories.services;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.abc.asms.categories.forms.S0061Form;
import com.abc.asms.goods.utils.DBUtils;

public class S0062Service {

	public void update(S0061Form s0061form) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;

		try{
			//データベースの接続を確立
			con = DBUtils.getConnection();
		//sql
			sql = " UPDATE categories set category_name=?, active_flg=? where category_id=?";


			//UPDATE命令の準備
			ps = con.prepareStatement(sql);

			//UPDATE命令にポストデータの内容をセット
			ps.setString(1, s0061form.getName());
			ps.setString(2, s0061form.getFlg());
			ps.setString(3, s0061form.getId());

			//UPDATE命令の実行
			ps.executeUpdate();

		} catch (Exception e) {
		e.printStackTrace();
		} finally {
		DBUtils.close(con, ps, null);
		}
	}

}
