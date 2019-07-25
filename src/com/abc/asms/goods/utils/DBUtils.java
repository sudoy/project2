package com.abc.asms.goods.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBUtils {
	public static Connection getConnection()
			throws NamingException, SQLException {
		Context initContext = new InitialContext();
		Context envContext = (Context) initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource) envContext.lookup("project2");
		return ds.getConnection();
	}

	public static void close(Connection con, PreparedStatement ps, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			close(con, ps);
		} catch (Exception e) {
		}
	}

	public static void close(Connection con, PreparedStatement ps) {
		try {
			if (ps != null) {
				ps.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (Exception e) {
		}
	}

	//account_id照合
	public boolean checkAccountId(String accountId) {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		boolean accountExist = false;

		try {
			//データベースの接続を確立
			con = getConnection();

			sql = "select account_id from accounts where account_id = ?";

			// プレースホルダに値を設定
			ps = con.prepareStatement(sql);

			ps.setString(1, accountId);

			rs = ps.executeQuery();

			if (rs.next()) {//値が取れたら

				rs.getString("account_id");

				accountExist = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			DBUtils.close(con, ps, rs);
		}
		return accountExist;

	}

	//sale_id照合
	public boolean checkSaleId(String saleId) {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		boolean saleExist = false;

		try {
			//データベースの接続を確立
			con = getConnection();

			sql = "select sale_id from sales where sale_id = ?";

			// プレースホルダに値を設定
			ps = con.prepareStatement(sql);

			ps.setString(1, saleId);

			rs = ps.executeQuery();

			if (rs.next()) {//値が取れたら

				rs.getString("sale_id");

				saleExist = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			DBUtils.close(con, ps, rs);
		}
		return saleExist;

	}

}
