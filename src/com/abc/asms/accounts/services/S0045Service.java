package com.abc.asms.accounts.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.abc.asms.accounts.S0045Form;
import com.abc.asms.goods.utils.DBUtils;

public class S0045Service {

	public boolean service(S0045Form form){

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		boolean exist = false;

		String s = null;

		try{
			//データベースの接続を確立
			con = DBUtils.getConnection();
		//sql
			sql = "SELECT mail From accounts WHERE mail = ?";

			// プレースホルダに値を設定
			ps = con.prepareStatement(sql);

			ps.setString(1, form.getMail());

			rs = ps.executeQuery();

			while(rs.next()) {
				s = rs.getString("mail");
			};


			if(s == null){
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
