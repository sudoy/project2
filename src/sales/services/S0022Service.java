package sales.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;

import goods.utils.DBUtils;
import goods.utils.HTMLUtils;
import sales.forms.S0022Form;

public class S0022Service {

	public S0022Form service(String id) {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		try {
			con = DBUtils.getConnection();

			sql = "select s.sale_id, s.sale_date, a.name, c.category_name, s.trade_name," +
					"s.unit_price, s.sale_number, s.unit_price * s.sale_number as total , s.note" +
					"from sales s join categories c on s.category_id = c.category_id " +
					"join accounts a on s.account_id = a.account_id where s.sale_id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();

			S0022Form sf = null;

			while (rs.next()) {

				String daimei = rs.getString("daimei");
				String syosai = rs.getString("syosai");
				String juyodoval = rs.getString("juyodoval");
				String kigen = rs.getString("kigen");
				String status = rs.getString("status");

				sf = new S0022Form(number, daimei, syosai, kigen, HTMLUtils.radio1(juyodoval),
						HTMLUtils.radio2(juyodoval), HTMLUtils.radio3(juyodoval), HTMLUtils.status1(status),
						HTMLUtils.status0(status));
			}
			return sf;

		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			DBUtils.close(con, ps, rs);
		}
	}

}
