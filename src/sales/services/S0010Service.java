package sales.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import goods.utils.DBUtils;
import sales.forms.S0010Form;

public class S0010Service {
	public void register(S0010Form form){

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		try{
			//データベースの接続を確立
			con = DBUtils.getConnection();
		//sql
			sql = "INSERT INTO sales(sale_date, account_id, category_id, trade_name, unit_price, sale_number, note)"
					+ " VALUES (?,?,?,?,?,?,?)";

			// プレースホルダに値を設定
			ps = con.prepareStatement(sql);

			ps.setString(1, form.getSaledate());
			ps.setString(2, form.getAccountid());
			ps.setString(3, form.getCategoryid());
			ps.setString(4, form.getTradename());
			ps.setString(5, form.getPrice());
			ps.setString(6, form.getSalenumber());
			ps.setString(7, form.getNote());

			//select命令を実行
			ps.executeUpdate();


		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtils.close(con,ps, rs);
		}
	}
	public List<String> category() throws ServletException{
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		List<String> list = new ArrayList<>();

		try {
			con = DBUtils.getConnection();
			sql = "select category_name from categories";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				String categoryName = rs.getString("category_name");
				System.out.println(categoryName);

				list.add(categoryName);
			}
			return list;

		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			DBUtils.close(con, ps, rs);
		}
	}
	public List<S0010Form> select() throws ServletException {

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

			List<S0010Form> accounts = new ArrayList<>();

			while(rs.next()) {
				S0010Form form = new S0010Form();
				accounts.add(form);

				form.setAccountid(rs.getString("account_id"));
				form.setName(rs.getString("name"));
			}
			return accounts;

		}catch(Exception e){
			throw new ServletException(e);

		} finally {
			DBUtils.close(con, ps, rs);
		}


	}

}

