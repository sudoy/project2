package com.abc.asms.accounts.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;

import com.abc.asms.accounts.forms.S0030Form;
import com.abc.asms.goods.utils.DBUtils;



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


			//select命令を実行
			ps.executeUpdate();


		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtils.close(con,ps, rs);
		}
	}
	public String Accountid(S0030Form form) throws ServletException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;


		try {
			//データベース接続
			con = DBUtils.getConnection();

			//SQL
			sql = "select account_id from accounts where mail= ?";

			//SELECT命令の準備
			ps = con.prepareStatement(sql);

			ps.setString(1, form.getMail());


			//SELECT命令の実行
			rs = ps.executeQuery();
			String saleid = null;


			while(rs.next()) {
				saleid = rs.getString("account_id");
			}
			return saleid;

		}catch(Exception e){
			throw new ServletException(e);

		} finally {
			DBUtils.close(con, ps, rs);
		}
	}

}


