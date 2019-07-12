package accounts.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import accounts.forms.S0044Form;
import goods.utils.DBUtils;

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

		try {
			//データベース接続
			con = DBUtils.getConnection();

			//SQL
			sql = "select account_id, name, mail, password, authority "
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

			}

			S0044Form list = new S0044Form(id, name, mail, password, authority);

			return list;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con, ps, rs);
		}
		return null;

	}

	//データの削除
	public void delete(S0044Form form) {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;

		try {
			//データベース接続
			con = DBUtils.getConnection();

			//SQL
			sql = "delete from accounts where account_id = ?";

			//DELETE命令の準備
			ps = con.prepareStatement(sql);

			//DELETE命令にポストデータの内容をセット
			ps.setString(1, form.getId());

			//DELETEE命令の実行
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con, ps, null);
		}

	}

}
