package com.abc.asms.sales.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import com.abc.asms.goods.utils.DBUtils;
import com.abc.asms.sales.forms.S0020Form;

public class S0020Service {


	//getで検索画面表示するときの担当者選択用
	public List<S0020Form> staff() throws ServletException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		List<S0020Form> list = new ArrayList<>();

		try {
			con = DBUtils.getConnection();
			sql = "select name from accounts";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
//				String accountId = rs.getString("account_id");
				String accountName = rs.getString("name");

				S0020Form f = new S0020Form(accountName);
				list.add(f);
			}
			return list;

		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			DBUtils.close(con, ps, rs);
		}

	}

	//getで検索画面表示するときのカテゴリー選択用
	public List<String> category() throws ServletException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		List<String> list = new ArrayList<>();

		try {
			con = DBUtils.getConnection();
			sql = "select category_name from categories";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				String categoryName = rs.getString("category_name");

				list.add(categoryName);
			}
			return list;

		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			DBUtils.close(con, ps, rs);
		}
	}
}
