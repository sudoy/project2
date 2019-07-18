package com.abc.asms.accounts.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import com.abc.asms.accounts.forms.S0040Form;
import com.abc.asms.accounts.forms.S0041Form;
import com.abc.asms.goods.utils.DBUtils;
import com.abc.asms.goods.utils.HTMLUtils;

public class S0041Service {
	public List<S0041Form> service(S0040Form form) throws ServletException {

		String name = form.getName();
		String mail = form.getMail();
		String sale = form.getSale();
		String account = form.getAccount();

		String authoritySQL = HTMLUtils.formatAuthority(sale, account);

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		List<S0041Form> list = new ArrayList<>();

		try {
			con = DBUtils.getConnection();
			sql = "select account_id, name, mail, authority from accounts where 1 = 1";

			if (!name.equals("")) {//nameが入力されている
				sql += " and name like ?";
			}
			if (!mail.equals("")) {//mailが入力されている
				sql += " and mail = ?";
			}

			sql += authoritySQL;

			ps = con.prepareStatement(sql);
			System.out.println(ps);

			if (!name.equals("")) {//nameが入力されている
				ps.setString(1, "%" + name + "%");
				if (!mail.equals("")) {//mailが入力されている
					ps.setString(2, mail);
				}
			} else {//nameが入力されていない
				if (!mail.equals("")) {//mailが入力されている
					ps.setString(1, mail);
				}
			}

			rs = ps.executeQuery();

			while (rs.next()) {

				String dbId = rs.getString("account_id");
				String dbName = rs.getString("name");
				String dbMail = rs.getString("mail");
				String dbAuthority = HTMLUtils.expressAuthority(rs.getString("authority"));

				S0041Form f = new S0041Form(dbId, dbName, dbMail, dbAuthority);
				list.add(f);
			}
			return list;

		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			DBUtils.close(con, ps, rs);
		}

	}
}
