package accounts.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import accounts.forms.S0030Form;
import goods.utils.DBUtils;

public class S0031Service {
	public boolean service(S0030Form form){

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		boolean exist = false;

		try{
			//データベースの接続を確立
			con = DBUtils.getConnection();
		//sql
			sql = "SELECT mail From accounts WHERE mail = ?";

			// プレースホルダに値を設定
			ps = con.prepareStatement(sql);

			ps.setString(1, form.getMail());

			rs = ps.executeQuery();

			rs.next();

			if(rs.getString("mail") == null) {
				exist = true;
			}else {
				exist = false;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{

			DBUtils.close(con,ps, rs);
		}
		return exist;

	}
}
