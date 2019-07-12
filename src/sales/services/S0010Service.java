package sales.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import goods.utils.DBUtils;
import sales.forms.S0010Form;

public class S0010Service {
	public void register(S0010Form form){

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		try{
			//データベースの接続を確立
			con = DBUtils.getConnection();
		//sql
			sql = "INSERT INTO sales(sale_date, account_id, category_id, trade_name, unit_price, sale_number, note)"
					+ " VALUES (?,?,?,?,?,?,?)";

			// プレースホルダに値を設定
			ps = con.prepareStatement(sql);

			ps.setString(1, form.getSaledate());
			ps.setString(2, form.getAccountid());
			ps.setString(3, form.getCategoryid());
			ps.setString(4, form.getTradename());
			ps.setString(5, form.getPrice());
			ps.setString(6, form.getSalenumber());
			ps.setString(7, form.getNote());

			//select命令を実行
			ps.executeUpdate();


		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtils.close(con,ps, rs);
		}
	}
}

