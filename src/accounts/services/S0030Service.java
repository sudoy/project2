package accounts.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import accounts.forms.S0030Form;
import goods.utils.DBUtils;



public class S0030Service {
	public void register(S0030Form form){

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		try{
			//データベースの接続を確立
			con = DBUtils.getConnection();
		//sql
			sql = "INSERT INTO accounts(name, mail, password, authority) VALUES (?,?,md5(?),?)";

			// プレースホルダに値を設定
			ps = con.prepareStatement(sql);

			ps.setString(1, form.getName());
			ps.setString(2, form.getMail());
			ps.setString(3, form.getPassword());
			ps.setString(4, form.getAuthority());
			System.out.println(ps);

			//select命令を実行
			ps.executeUpdate();


		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtils.close(con,ps, rs);
		}
	}
}


