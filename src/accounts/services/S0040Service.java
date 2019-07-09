package accounts.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import accounts.forms.S0040Form;
import goods.utils.DBUtils;
import goods.utils.HTMLUtils;

public class S0040Service {
	public List<S0040Form> service(S0040Form form) throws ServletException {

		String name = form.getName();
		String mail = form.getMail();
		String sale = form.getSale();
		String account = form.getAccount();

		Map<String, String> map = HTMLUtils.formatAuthority(sale, account);

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		List<S0040Form> list = new ArrayList<>();

		try {
			con = DBUtils.getConnection();
			sql = "select account_id, name, mail, authority from accounts where name like ? and mail = ? ";

			for(Map.Entry<String, String> entry : map.entrySet()) {
					sql += entry.getValue();
			}

			ps = con.prepareStatement(sql);

			ps.setString(1, "%" + name + "%");
			ps.setString(2, mail);

			System.out.println(ps);

			rs = ps.executeQuery();

			while (rs.next()) {

				int dbId = Integer.parseInt(rs.getString("account_id"));
				String dbName = rs.getString("name");
				String dbMail = rs.getString("mail");
				String dbAuthority = rs.getString("authority");

				S0040Form f = new S0040Form(dbId, dbName, dbMail, dbAuthority);
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
