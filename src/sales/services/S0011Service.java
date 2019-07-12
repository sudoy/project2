package sales.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import goods.utils.DBUtils;
import sales.forms.S0010Form;



public class S0011Service {
	public List<S0010Form> select() throws ServletException {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;


		try {
			//データベース接続
			con = DBUtils.getConnection();

			//SQL
			sql = "select account_id,name from accounts";

			//SELECT命令の準備
			ps = con.prepareStatement(sql);

			//SELECT命令の実行
			rs = ps.executeQuery();

			List<S0010Form> accounts = new ArrayList<>();

			while(rs.next()) {
				S0010Form form = new S0010Form();
				accounts.add(form);

				form.setAccountid(rs.getString("account_id"));
				form.setName(rs.getString("name"));
			}
			return accounts;

		}catch(Exception e){
			throw new ServletException(e);

		} finally {
			DBUtils.close(con, ps, rs);
		}


	}
}
