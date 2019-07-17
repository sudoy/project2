package sales.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import goods.utils.DBUtils;
import sales.forms.S0023Form;

public class S0023Service {


	public S0023Form select(String num) {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		String id = null;
		String saledate = null;
		String name = null;
		String categoryname = null;
		String tradename = null;
		String price = null;
		String salenumber = null;
		String note = null;


		try {
			//データベース接続
			con = DBUtils.getConnection();

			//SQL
			sql = "select s.sale_id, s.sale_date, a.name, c.category_name, s.trade_name,"
					+ " s.unit_price, s.sale_number, s.unit_price * s.sale_number as total , s.note"
					+ " from sales s join categories c on s.category_id = c.category_id "
					+ "join accounts a on s.account_id = a.account_id where sale_id = ?";

			//SELECT命令の準備
			ps = con.prepareStatement(sql);

			ps.setString(1, num);


			//SELECT命令の実行
			rs = ps.executeQuery();

			//結果セットの内容を出力(DBから抽出したデータ)
			while(rs.next()) {

			id = rs.getString("s.sale_id");
			System.out.println(id);
			saledate = rs.getString("s.sale_date");
			name = rs.getString("a.name");
			categoryname = rs.getString("c.category_name");
			tradename = rs.getString("s.trade_name");
			price = rs.getString("s.unit_price");
			salenumber = rs.getString("s.sale_number");
			note = rs.getString("s.note");

			}

			S0023Form form = new S0023Form(id, saledate, name, categoryname,tradename, price, salenumber, note);

			return form;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con, ps, rs);
		}
		return null;

	}
	public List<S0023Form> selectaccount() throws ServletException {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;


		try {
			//データベース接続
			con = DBUtils.getConnection();

			//SQL
			sql = "select account_id,name from accounts";

			//SELECT命令の準備
			ps = con.prepareStatement(sql);

			//SELECT命令の実行
			rs = ps.executeQuery();

			List<S0023Form> accounts = new ArrayList<>();

			while(rs.next()) {
				S0023Form form = new S0023Form();

				form.setId(rs.getString("account_id"));
				form.setName(rs.getString("name"));
				accounts.add(form);
			}
			return accounts;

		}catch(Exception e){
			throw new ServletException(e);

		} finally {
			DBUtils.close(con, ps, rs);
		}


	}
}
