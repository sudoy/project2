package sales.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;

import goods.utils.DBUtils;



public class S0011Service {

	public String select2(String accountid) throws ServletException {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;


		try {
			//データベース接続
			con = DBUtils.getConnection();

			//SQL
			sql = "select name from accounts where account_id = ?";

			//SELECT命令の準備
			ps = con.prepareStatement(sql);
			ps.setString(1, accountid);

			//SELECT命令の実行
			rs = ps.executeQuery();



			rs.next();
			String name = rs.getString("name");

			return name;

		}catch(Exception e){
			throw new ServletException(e);

		} finally {
			DBUtils.close(con, ps, rs);
		}

	}

}
