package com.abc.asms.sales.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;

import com.abc.asms.goods.utils.DBUtils;
import com.abc.asms.sales.forms.S0022Form;

public class S0022Service {

	public S0022Form service(String id) throws ServletException {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		try {
			con = DBUtils.getConnection();

			sql = "select s.sale_id, s.sale_date, a.name, c.category_name, s.trade_name," +
					" s.unit_price, s.sale_number, s.unit_price * s.sale_number as total , s.note" +
					" from sales s left join categories c on s.category_id = c.category_id" +
					" left join accounts a on s.account_id = a.account_id where s.sale_id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);

			rs = ps.executeQuery();

			S0022Form sf = null;

			while (rs.next()) {

				String saleId = rs.getString("s.sale_id");
				String saleDate = rs.getString("s.sale_date");
				String name = rs.getString("a.name");
				String categoryName = rs.getString("c.category_name");
				String tradeName = rs.getString("s.trade_name");
				String unitPrice = rs.getString("s.unit_price");
				String saleNumber = rs.getString("s.sale_number");
				String total = rs.getString("total");
				String note = rs.getString("s.note");

				sf = new S0022Form(saleId, saleDate, name, categoryName, tradeName, unitPrice, saleNumber, total, note);
			}
			return sf;

		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			DBUtils.close(con, ps, rs);
		}
	}

}
