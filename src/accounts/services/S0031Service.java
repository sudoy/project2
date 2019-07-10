package accounts.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import goods.utils.DBUtils;

public class S0031Service {
	public ResultSet rs(){

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;


		try{
			//データベースの接続を確立
			con = DBUtils.getConnection();
		//sql
			sql = "SELECT mail from accounts";

			// プレースホルダに値を設定
			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtils.close(con,ps, rs);
		}
		return rs;
	}
}
