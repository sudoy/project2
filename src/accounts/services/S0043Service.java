package accounts.services;

import java.sql.Connection;
import java.sql.PreparedStatement;

import accounts.forms.S0043Form;
import goods.utils.DBUtils;

public class S0043Service {


	//データ更新
	public void update(S0043Form form) {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;

		try {
			//データベース接続
			con = DBUtils.getConnection();

			//SQL MD5化
			sql = "update accounts set name=?, mail=?, password=md5(?), authority = ? "
					+ "where account_id = ?";

			//UPDATE命令の準備
			ps = con.prepareStatement(sql);

			//UPDATE命令にポストデータの内容をセット
			ps.setString(1, form.getName());
			ps.setString(2, form.getMail());
			ps.setString(3, form.getPassword());
			ps.setString(4, form.getAuthority());
			ps.setString(5, form.getId());

			//UPDATE命令の実行
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con, ps, null);
		}

	}

}
