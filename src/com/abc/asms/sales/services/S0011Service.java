package com.abc.asms.sales.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;

import com.abc.asms.goods.utils.DBUtils;
import com.abc.asms.sales.forms.S0010Form;
import com.abc.asms.sales.forms.S0011Form;



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

			String name = null;
			if(rs.next()) {
				name = rs.getString("name");
			};


			return name;

		}catch(Exception e){
			throw new ServletException(e);

		} finally {
			DBUtils.close(con, ps, rs);
		}

	}
	public boolean service1(S0011Form form){

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
			sql = "SELECT account_id From accounts WHERE account_id = ?";

			// プレースホルダに値を設定
			ps = con.prepareStatement(sql);

			ps.setString(1, form.getAccountid());

			rs = ps.executeQuery();

			while(rs.next()) {
				s = rs.getString("account_id");
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
	public boolean service(S0010Form form) {

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
			sql = "SELECT account_id From accounts WHERE account_id = ?";

			// プレースホルダに値を設定
			ps = con.prepareStatement(sql);

			ps.setString(1, form.getAccountid());

			rs = ps.executeQuery();

			while(rs.next()) {
				s = rs.getString("account_id");
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
	public boolean service2(S0011Form form){

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		boolean categorytable = false;

		String s = null;

		try{
			//データベースの接続を確立
			con = DBUtils.getConnection();
		//sql
			sql = "SELECT category_id From categories WHERE category_id = ? and active_flg = 1";

			// プレースホルダに値を設定
			ps = con.prepareStatement(sql);

			ps.setString(1, form.getCategoryid());

			rs = ps.executeQuery();

			while(rs.next()) {
				s = rs.getString("category_id");
			};


			if(s == null){
				categorytable = true;
			}else {
				categorytable = false;
			}


		}catch(Exception e){
			e.printStackTrace();
		}finally{

			DBUtils.close(con,ps, rs);
		}
		return categorytable;


	}



}
