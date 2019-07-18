package com.abc.asms.others.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.abc.asms.goods.utils.DBUtils;
import com.abc.asms.others.forms.C0010Form;

public class C0010Service {
	public C0010Form service(C0010Form form) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		boolean login = false;
		C0010Form f;
		String id = null;
		String name = null;
		String mail = null;
//		String password = null;
		String authority = null;

		try {
			con = DBUtils.getConnection();
			sql = "select account_id, name, mail, password, authority "
					+ "from accounts where mail = ? and password = md5(?)";//ハッシュ化
			ps = con.prepareStatement(sql);

			ps.setString(1, form.getMail());
			ps.setString(2, form.getPassword());

			rs = ps.executeQuery();

			if (rs.next()) {
				id = rs.getString("account_id");
				name = rs.getString("name");
				mail = rs.getString("mail");
//				password = rs.getString("password");//ここでpasswordだけ取得できないということはありえる…?ありえないなら消す
				authority = rs.getString("authority");

				login = true;//１件も取れていなければifの中に入らないのでloginはfalseのまま
			}
			f = new C0010Form(login, id, name, mail, authority);

			return f;

		} catch (Exception e) {
			e.printStackTrace();
			f = new C0010Form(login, id, name, mail, authority);//途中でExceptionが発生してしまったとき用
			return f;
		} finally {
			DBUtils.close(con, ps, rs);
		}
	}
}
