package com.abc.asms.categories.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;

import com.abc.asms.goods.utils.DBUtils;

public class S0050Service {
	public boolean exist(String category){

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
			sql = "SELECT category_name From categories WHERE category_name = ?";

			// プレースホルダに値を設定
			ps = con.prepareStatement(sql);

			ps.setString(1, category);

			rs = ps.executeQuery();

			while(rs.next()) {
				s = rs.getString("category_name");
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

	public String categoryid(String category) throws ServletException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;


		try {
			//データベース接続
			con = DBUtils.getConnection();

			//SQL
			sql = "select category_id from categories where category_name = ?";

			//SELECT命令の準備
			ps = con.prepareStatement(sql);

			ps.setString(1, category);


			//SELECT命令の実行
			rs = ps.executeQuery();

			String categoryid = null;
			while(rs.next()) {
				categoryid = rs.getString("category_id");
			}
			return categoryid;

		}catch(Exception e){
			throw new ServletException(e);

		} finally {
			DBUtils.close(con, ps, rs);
		}

	}

	public void insert(String category) {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		try{
			//データベースの接続を確立
			con = DBUtils.getConnection();
		//sql
			sql = "INSERT INTO categories(category_name, active_flg) VALUES (?,1)";

			// プレースホルダに値を設定
			ps = con.prepareStatement(sql);

			ps.setString(1, category);


			//select命令を実行
			ps.executeUpdate();


		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtils.close(con,ps, rs);
		}


	}
}
