package accounts.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import accounts.forms.S0030Form;
import accounts.forms.S0031Form;
import goods.utils.DBUtils;

public class S0031Service {
	public List<S0031Form> service(S0030Form form){

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;


		try{
			//データベースの接続を確立
			con = DBUtils.getConnection();
		//sql
			sql = "SELECT mail From accounts WHERE mail = ?";

			// プレースホルダに値を設定
			ps = con.prepareStatement(sql);

			ps.setString(1, form.getName());

			rs = ps.executeQuery();

			List<S0031Form> list = new ArrayList<>();
			while(rs.next()) {
				S0031Form f = new S0031Form(sql);
				list.add(f);
				f.setMail(rs.getString("mail"));
			}


		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtils.close(con,ps, rs);
		}
		return list;



	}
}
