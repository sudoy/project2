package com.abc.asms.accounts.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.abc.asms.accounts.forms.S0044Form;
import com.abc.asms.goods.utils.DBUtils;

public class S0044Service {

	public S0044Form select(String num) {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		String id = null;
		String name = null;
		String mail = null;
		String password = null;
		String authority = null;
		String version = null;

		try {
			//データベース接続
			con = DBUtils.getConnection();

			//SQL
			sql = "select account_id, name, mail, password, authority, version "
					+ "from accounts "
					+ "where account_id = ? "
					+ "order by account_id";

			//SELECT命令の準備
			ps = con.prepareStatement(sql);

			ps.setString(1, num);


			//SELECT命令の実行
			rs = ps.executeQuery();

			//結果セットの内容を出力(DBから抽出したデータ)
			while(rs.next()) {

				id = rs.getString("account_id");
				name = rs.getString("name");
				mail = rs.getString("mail");
				password = rs.getString("password");
				authority = rs.getString("authority");
				version = rs.getString("version");

			}

			S0044Form list = new S0044Form(id, name, mail, password, authority, version);

			return list;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con, ps, rs);
		}
		return null;

	}

	//データの削除
	public List<String> delete(S0044Form form) {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;

		List<String> error = new ArrayList<>();

		try {
			//データベース接続
			con = DBUtils.getConnection();

			//SQL
			sql = "delete from accounts where account_id = ? and version = ?";

			//DELETE命令の準備
			ps = con.prepareStatement(sql);

			//DELETE命令にポストデータの内容をセット
			ps.setString(1, form.getId());
			ps.setString(2, form.getVersion());

			//DELETEE命令の実行
			if(ps.executeUpdate() == 0) {//削除に成功した行数が返ってくるので
				error.add("No." + form.getId() + "のアカウントを削除できませんでした。");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con, ps);
		}
		return error;

	}

}
