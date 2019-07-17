package accounts.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import accounts.forms.S0046Form;
import goods.utils.DBUtils;

public class S0046Service {

	public S0046Form selectmail(S0046Form form) {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;


		String mail = null;

		try {
			//データベース接続
			con = DBUtils.getConnection();

			//SQL
			sql = "select mail "
					+ "from accounts "
					+ "where male = ?";

			//SELECT命令の準備
			ps = con.prepareStatement(sql);

			ps.setString(1, form.getId());


			//SELECT命令の実行
			rs = ps.executeQuery();

			//結果セットの内容を出力(DBから抽出したデータ)
			while(rs.next()) {

			mail = rs.getString("mail");

			}

			S0046Form existmail = new S0046Form(mail);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con, ps, rs);
		}
		return form;

	}

	//パスワードの更新
	public void updatepassword(S0046Form form) {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;

		try {
			//データベース接続
			con = DBUtils.getConnection();

			sql = "update accounts set password = ? "
					+ "where mail= ?";

			//UPDATE命令の準備
			ps = con.prepareStatement(sql);

			//UPDATE命令にポストデータの内容をセット
			ps.setString(1, form.getMail());


			//UPDATE命令の実行
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con, ps, null);
		}

	}

}
