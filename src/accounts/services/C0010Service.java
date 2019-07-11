package accounts.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import accounts.forms.C0010Form;
import goods.utils.DBUtils;

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
		String password = null;
		String authority = null;

		try {
			con = DBUtils.getConnection();
			sql = "select account_id, name, mail, password, authority "
					+ "from accounts where mail = ? and password = md5(?)";//ハッシュ化
			ps = con.prepareStatement(sql);

			ps.setString(1, form.getMail());
			ps.setString(2, form.getPassword());

			rs = ps.executeQuery();

			while (rs.next()) {
				name = rs.getString("name");
				mail = rs.getString("mail");
				password = rs.getString("password");

				if (mail != null && password != null) {
					login = true;

				}
			}
			f = new C0010Form(login, id, name, mail, password, authority);

			return f;

		} catch (Exception e) {
			e.printStackTrace();
			login = false;
			f = new C0010Form(login, id, name, form.getMail(), password, authority);//getMailは初期表示用
			return f;
		} finally {
			DBUtils.close(con, ps, rs);
		}
	}
}
