package com.abc.asms.accounts.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.abc.asms.accounts.forms.S0046Form;
import com.abc.asms.goods.utils.DBUtils;

public class S0046Service {

	//メールアドレスの存在チェック
	public boolean selectMail(String mail) {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		boolean existmail = false;

		try {
			//データベース接続
			con = DBUtils.getConnection();

			//SQL
			sql = "select mail from accounts where mail = ?";

			//SELECT命令の準備
			ps = con.prepareStatement(sql);

			ps.setString(1, mail);

			//SELECT命令の実行
			rs = ps.executeQuery();

			//結果セットの内容を出力(DBから抽出したデータ)
			if (rs.next()) {

				existmail = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con, ps, rs);
		}

		return existmail;
	}

	//パスワードの更新
	public void updatePassword(S0046Form form) {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;

		try {
			//データベース接続
			con = DBUtils.getConnection();

			sql = "update accounts set password = md5(?) "
					+ "where mail= ?";

			//UPDATE命令の準備
			ps = con.prepareStatement(sql);

			//UPDATE命令にポストデータの内容をセット
			ps.setString(1, form.getPassword());
			ps.setString(2, form.getMail());

			//UPDATE命令の実行
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con, ps, null);
		}

	}

}
