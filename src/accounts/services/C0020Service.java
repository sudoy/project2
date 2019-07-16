package accounts.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import accounts.forms.C0020Form;
import goods.utils.DBUtils;

public class C0020Service {

	//現在の日付を取得して月始まりと月終わりも取得
	//DBからなんやかんやして一覧を取得
	public List<C0020Form> service(String userMail) throws ServletException {

		LocalDate today = LocalDate.now();
		LocalDate startDay = today.withDayOfMonth(1);
		LocalDate lastDay = today.plusMonths(1).withDayOfMonth(1).minusDays(1);

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		List<C0020Form> list = new ArrayList<>();

		try {
			con = DBUtils.getConnection();
			sql = "select s.sale_id, s.sale_date, a.name, c.category_name, s.trade_name,"
					+ " s.unit_price, s.sale_number, s.unit_price * s.sale_number as total , s.note"
					+ " from sales s join categories c on s.category_id = c.category_id"
					+ " join accounts a on s.account_id = a.account_id"
					+ " where ? <= sale_date and sale_date <= ? and a.mail = ?";

			ps = con.prepareStatement(sql);
			ps.setString(1, startDay.toString());
			ps.setString(2, lastDay.toString());
			ps.setString(3, userMail);

			rs = ps.executeQuery();

			C0020Form form = null;

			while (rs.next()) {
				String saleId = rs.getString("s.sale_id");
				String saleDate = rs.getString("s.sale_date");
				String name = rs.getString("a.name");
				String categoryName = rs.getString("c.category_name");
				String tradeName = rs.getString("s.trade_name");
				String unitPrice = rs.getString("s.unit_price");
				String saleNumber = rs.getString("s.sale_number");
				String total = rs.getString("total");

				form = new C0020Form(saleId, saleDate, name, categoryName, tradeName,
						unitPrice, saleNumber, total);
				list.add(form);
			}
			return list;

		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			DBUtils.close(con, ps, rs);
		}

	}

}
