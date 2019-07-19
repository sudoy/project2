package com.abc.asms.sales.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import com.abc.asms.goods.utils.DBUtils;
import com.abc.asms.sales.forms.S0025Form;

public class S0025Service {

	//salesとcategoriesのcategory_name
	public S0025Form select(String num) {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		String id = null;
		String saledate = null;
		String accountid = null;
		String categoryid = null;
		String tradename = null;
		String unitprice = null;
		String salenumber = null;
		String note = null;
		String categoryname = null;

		try {
			//データベース接続
			con = DBUtils.getConnection();

			//SQL
			sql = "select s.sale_id, s.sale_date, s.account_id, s.category_id, s.trade_name, s.unit_price,"
					+ " s.sale_number, note, c.category_name"
					+ " from sales s join categories c on s.category_id = c.category_id"
					+ " where sale_id = ?"
					+ " order by sale_id";


			//SELECT命令の準備
			ps = con.prepareStatement(sql);

			ps.setString(1, num);


			//SELECT命令の実行
			rs = ps.executeQuery();


			//結果セットの内容を出力(DBから抽出したデータ)
			while(rs.next()) {

				id = rs.getString("s.sale_id");
				saledate = rs.getString("s.sale_date");
				accountid = rs.getString("s.account_id");
				categoryid = rs.getString("s.category_id");
				tradename = rs.getString("s.trade_name");
				unitprice = rs.getString("s.unit_price");
				salenumber = rs.getString("s.sale_number");
				note = rs.getString("s.note");
				categoryname = rs.getString("c.category_name");


			}
			//ハイフンをスラッシュに変更
			saledate = saledate.replace("-", "/");

			S0025Form form = new S0025Form(id, saledate, accountid, categoryid, tradename, unitprice,
					salenumber, note, categoryname);

			return form;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con, ps, rs);
		}
		return null;

	}

	//データの削除
	public void delete(S0025Form form) {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;

		try {
			//データベース接続
			con = DBUtils.getConnection();

			//SQL
			sql = "delete from sales where sale_id = ?";

			//DELETE命令の準備
			ps = con.prepareStatement(sql);

			//DELETE命令にポストデータの内容をセット
			ps.setString(1, form.getId());

			//DELETEE命令の実行
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(con, ps);
		}

	}

	//name
	public String name(String accountid) throws ServletException {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;


		try {
			//データベース接続
			con = DBUtils.getConnection();

			//SQL
			sql = "select name from accounts where account_id = ?";

			//SELECT命令の準備
			ps = con.prepareStatement(sql);
			ps.setString(1, accountid);

			//SELECT命令の実行
			rs = ps.executeQuery();

			rs.next();
			String name = rs.getString("name");

			return name;

		}catch(Exception e){
			throw new ServletException(e);

		} finally {
			DBUtils.close(con, ps, rs);
		}

	}

	//category_name
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

				list.add(categoryName);
			}
			return list;

		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			DBUtils.close(con, ps, rs);
		}
	}


}
