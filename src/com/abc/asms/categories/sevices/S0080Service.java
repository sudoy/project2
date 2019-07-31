package com.abc.asms.categories.sevices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.abc.asms.categories.forms.S0080Form;
import com.abc.asms.goods.utils.DBUtils;

public class S0080Service {

	public List<S0080Form> select() {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		String id = null;
		String name = null;
		String active = null;

		List<S0080Form> list = new ArrayList<>();

		try {
			//データベース接続
			con = DBUtils.getConnection();

			//SQL
			sql = "select category_id, category_name, active_flg from categories where 1 = 1 order by category_id";

			//SELECT命令の準備
			ps = con.prepareStatement(sql);

			//SELECT命令の実行
			rs = ps.executeQuery();

			//結果セットの内容を出力(DBから抽出したデータ)
			while (rs.next()) {

				id = rs.getString("category_id");
				name = rs.getString("category_name");
				active = rs.getString("active_flg");

				S0080Form select = new S0080Form(id, name, active);
				list.add(select);
			}

			return list;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con, ps, rs);
		}
		return null;

	}

}
