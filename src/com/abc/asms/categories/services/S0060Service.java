package com.abc.asms.categories.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import com.abc.asms.categories.forms.S0060Form;
import com.abc.asms.goods.utils.DBUtils;

public class S0060Service {
	public List<S0060Form> select() throws ServletException {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;


		try {
			//データベース接続
			con = DBUtils.getConnection();

			//SQL
			sql = "select category_id, category_name, active_flg from categories";

			//SELECT命令の準備
			ps = con.prepareStatement(sql);

			//SELECT命令の実行
			rs = ps.executeQuery();

			List<S0060Form> categories = new ArrayList<>();

			while(rs.next()) {
				S0060Form form = new S0060Form();

				form.setCategoryid(rs.getString("category_id"));
				form.setName(rs.getString("category_name"));

				String activeflg = null;
				if(rs.getString("active_flg").equals("1")) {
					activeflg = rs.getString("active_flg").replace("1", "可");
				}else if(rs.getString("active_flg").equals("0")) {
					activeflg = rs.getString("active_flg").replace("0", "不可");				}
				form.setActiveflg(activeflg);
				categories.add(form);
			}
			System.out.println(categories);
			return categories;

		}catch(Exception e){
			throw new ServletException(e);

		} finally {
			DBUtils.close(con, ps, rs);
		}
	}
}
