package accounts.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;

import accounts.forms.S0025Form;
import goods.utils.DBUtils;

public class S0025Service {
	public String Saleid(S0025Form form) throws ServletException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;


		try {
			//データベース接続
			con = DBUtils.getConnection();

			//SQL
			sql = "select sale_id from sales where sale_date = ? and account_id = ? and trade_name= ?";

			//SELECT命令の準備
			ps = con.prepareStatement(sql);

			ps.setString(1, form.getSaledate());
			ps.setString(2, form.getAccountid());
			ps.setString(3, form.getTradename());

			//SELECT命令の実行
			rs = ps.executeQuery();
			String saleid = null;


			while(rs.next()) {
				saleid = rs.getString("sale_id");
			}
			return saleid;

		}catch(Exception e){
			throw new ServletException(e);

		} finally {
			DBUtils.close(con, ps, rs);
		}
	}
//	public void delete(S0025Form form) {
//
//		Connection con = null;
//		PreparedStatement ps = null;
//		String sql = null;
//
//		try {
//			//データベース接続
//			con = DBUtils.getConnection();
//
//			//SQL
//			sql = "delete from sales where sale_id = ?";
//
//			//DELETE命令の準備
//			ps = con.prepareStatement(sql);
//
//			//DELETE命令にポストデータの内容をセット
//			ps.setString(1, form.getSaleid());
//
//			//DELETEE命令の実行
//			ps.executeUpdate();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			DBUtils.close(con, ps);
//		}
//
//	}

}
