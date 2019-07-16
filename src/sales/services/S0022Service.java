package sales.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;

import goods.utils.DBUtils;
import goods.utils.HTMLUtils;

public class S0022Service {

	public S0022Form service() {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		try {
			con = DBUtils.getConnection();

			sql = "SELECT daimei, syosai, juyodoval, kigen, status FROM todolist where number = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, number);
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
