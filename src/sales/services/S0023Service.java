package sales.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import accounts.forms.S0042Form;
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

			//SQL//全部一つのテーブルになったので
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

			id = rs.getString("sale_id");
			name = rs.getString("name");
			mail = rs.getString("mail");
			password = rs.getString("password");
			authority = rs.getString("authority");

			}

			S0042Form edit = new S0042Form(id, name, mail, password, authority);

			return edit;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con, ps, rs);
		}
		return null;

	}
}
