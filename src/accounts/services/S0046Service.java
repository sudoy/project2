package accounts.services;

import java.sql.Connection;
import java.sql.PreparedStatement;

import accounts.forms.S0046Form;
import goods.utils.DBUtils;

public class S0046Service {

	//パスワードの更新
	public void updatepassword(S0046Form form) {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;

		try {
			//データベース接続
			con = DBUtils.getConnection();

			sql = "update accounts set password = ? "
					+ "where account_id = ?";

			//UPDATE命令の準備
			ps = con.prepareStatement(sql);

			//UPDATE命令にポストデータの内容をセット
			ps.setString(1, form.getPassword());


			//UPDATE命令の実行
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con, ps, null);
		}

	}

}
