package com.abc.asms.accounts.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.abc.asms.accounts.forms.S0043Form;
import com.abc.asms.goods.utils.DBUtils;

public class S0043Service {

	//データ更新
	public List<String> update(S0043Form form) {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;

		List<String> error = new ArrayList<>();

		try {
			//データベース接続
			con = DBUtils.getConnection();


			//パスワード未入力時はハッシュ化せずに更新
				if(form.getNewpassword() == null) {
				sql = "update accounts set name = ?, mail = ?, password = md5(?), authority = ?, version = version + 1 "
						+ "where account_id = ? and version = ?";
				}else{
				sql = "update accounts set name = ?, mail = ?, password = ?, authority = ?, version = version + 1 "
							+ "where account_id = ? and version = ?";
				}


				//UPDATE命令の準備
				ps = con.prepareStatement(sql);

				//UPDATE命令にポストデータの内容をセット
				ps.setString(1, form.getName());
				ps.setString(2, form.getMail());
				ps.setString(3, form.getPassword());
				ps.setString(4, form.getAuthority());
				ps.setString(5, form.getId());
				ps.setString(6, form.getVersion());

				//UPDATE命令の実行
				if(ps.executeUpdate() == 0) {
					error.add("No." + form.getId() + "のアカウントを編集できませんでした。");
				}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con, ps, null);
		}
		return error;

	}

}
