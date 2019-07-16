package accounts.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import accounts.forms.S0020Form;
import goods.utils.DBUtils;

public class S0020Service {

	public List<S0020Form> service(S0020Form form) throws ServletException {

		String dateBegin = form.getDateBegin();
		String dateEnd = form.getDateEnd();
		String name = form.getName();
		String[] categoryname = form.getCateName();
		String tradeName = form.getTradeName();
		String note = form.getNote();

		if (name == null) {//ぬるぽ対策（選択してくださいのままだとnullが入ってくるので）
			name = "";
		}

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		List<S0020Form> list = new ArrayList<>();

		try {
			con = DBUtils.getConnection();
			sql = "select s.sale_id, s.sale_date, a.name, c.category_name, s.trade_name,"
					+ " s.unit_price, s.sale_number, s.unit_price * s.sale_number as total , s.note"
					+ " from sales s join categories c on s.category_id = c.category_id "
					+ "join accounts a on s.account_id = a.account_id where 1 = 1";

			if (!(dateBegin.equals(""))) {//開始日が入力されている
				sql += " and ? <= s.sale_date";
			}
			if (!(dateEnd.equals(""))) {//終了日が入力されている
				sql += " and s.sale_date <= ?";
			}
			if (!(name.equals(""))) {//担当者が選択されている
				sql += " and a.name = ?";
			}

			if (!(tradeName.equals(""))) {//商品名が入力されている
				sql += " and s.trade_name like ?";
			}
			if (!(note.equals(""))) {//備考が入力されている
				sql += " and s.note like ?";
			}

			if (!(categoryname == null)) {//カテゴリーが選択されている
				for (int i = 0; i < categoryname.length; i++) {
					if (i == 0) {
						sql += " and (c.category_name = '" + categoryname[i] + "'";
					} else {
						sql += " or c.category_name = '" + categoryname[i] + "'";
					}
				}
				sql += ")";
			}
			sql += " order by s.sale_id asc";

			ps = con.prepareStatement(sql);

			//プレースホルダーにセット
			if (!(dateBegin.equals(""))) {//開始日が入力されている
				ps.setString(1, dateBegin);
				if (!(dateEnd.equals(""))) {//終了日が入力されている
					ps.setString(2, dateEnd);
					if (!(name.equals(""))) {//担当者が選択されている
						ps.setString(3, name);
						if (!(tradeName.equals(""))) {//商品名が入力されている
							ps.setString(4, "%" + tradeName + "%");
							if (!(note.equals(""))) {//備考が入力されている
								ps.setString(5, "%" + note + "%");
							} else {//備考が入力されていない
							}
						} else {//商品名が入力されていない
							if (!(note.equals(""))) {//備考が入力されている
								ps.setString(4, "%" + note + "%");
							} else {//備考が入力されていない
							}
						}
					} else {//担当者が選択されていない
						if (!(tradeName.equals(""))) {//商品名が入力されている
							ps.setString(3, "%" + tradeName + "%");
							if (!(note.equals(""))) {//備考が入力されている
								ps.setString(4, "%" + note + "%");
							} else {//備考が入力されていない
							}
						} else {//商品名が入力されていない
							if (!(note.equals(""))) {//備考が入力されている
								ps.setString(3, "%" + note + "%");
							} else {//備考が入力されていない
							}
						}
					}
				} else {//終了日が入力されていない
					if (!(name.equals(""))) {//担当者が選択されている
						ps.setString(2, name);
						if (!(tradeName.equals(""))) {//商品名が入力されている
							ps.setString(3, "%" + tradeName + "%");
							if (!(note.equals(""))) {//備考が入力されている
								ps.setString(4, "%" + note + "%");
							} else {//備考が入力されていない

							}
						} else {//商品名が入力されていない
							if (!(note.equals(""))) {//備考が入力されている
								ps.setString(4, "%" + note + "%");
							} else {//備考が入力されていない
							}
						}
					} else {//担当者が選択されていない
						if (!(tradeName.equals(""))) {//商品名が入力されている
							ps.setString(2, "%" + tradeName + "%");
							if (!(note.equals(""))) {//備考が入力されている
								ps.setString(3, "%" + note + "%");
							} else {//備考が入力されていない
							}
						} else {//商品名が入力されていない
							if (!(note.equals(""))) {//備考が入力されている
								ps.setString(2, "%" + note + "%");
							} else {//備考が入力されていない
							}
						}
					}
				}

			} else {//開始日が入力されていない
				if (!(dateEnd.equals(""))) {//終了日が入力されている
					ps.setString(1, dateEnd);
					if (!(name.equals(""))) {//担当者が選択されている
						ps.setString(2, name);
						if (!(tradeName.equals(""))) {//商品名が入力されている
							ps.setString(3, "%" + tradeName + "%");
							if (!(note.equals(""))) {//備考が入力されている
								ps.setString(4, "%" + note + "%");
							} else {//備考が入力されていない
							}
						} else {//商品名が入力されていない
							if (!(note.equals(""))) {//備考が入力されている
								ps.setString(3, "%" + note + "%");
							} else {//備考が入力されていない
							}
						}
					} else {//担当者が選択されていない
						if (!(tradeName.equals(""))) {//商品名が入力されている
							ps.setString(2, "%" + tradeName + "%");
							if (!(note.equals(""))) {//備考が入力されている
								ps.setString(3, "%" + note + "%");
							} else {//備考が入力されていない
							}
						} else {//商品名が入力されていない
							if (!(note.equals(""))) {//備考が入力されている
								ps.setString(2, "%" + note + "%");
							} else {//備考が入力されていない
							}
						}
					}
				} else {//終了日が入力されていない
					if (!(name.equals(""))) {//担当者が選択されている
						ps.setString(1, name);
						if (!(tradeName.equals(""))) {//商品名が入力されている
							ps.setString(2, "%" + tradeName + "%");
							if (!(note.equals(""))) {//備考が入力されている
								ps.setString(3, "%" + note + "%");
							} else {//備考が入力されていない
							}
						} else {//商品名が入力されていない
							if (!(note.equals(""))) {//備考が入力されている
								ps.setString(2, "%" + note + "%");
							} else {//備考が入力されていない
							}
						}
					} else {//担当者が選択されていない
						if (!(tradeName.equals(""))) {//商品名が入力されている
							ps.setString(1, "%" + tradeName + "%");
							if (!(note.equals(""))) {//備考が入力されている
								ps.setString(2, "%" + note + "%");
							} else {//備考が入力されていない
							}
						} else {//商品名が入力されていない
							if (!(note.equals(""))) {//備考が入力されている
								ps.setString(1, "%" + note + "%");
							} else {//備考が入力されていない
							}
						}
					}
				}
			}

			System.out.println(ps);

			rs = ps.executeQuery();

			while (rs.next()) {

				String saleId = rs.getString("s.sale_id");
				String saleDate = rs.getString("s.sale_date");
				String staff = rs.getString("a.name");//nameが重複していたので
				String categoryName = rs.getString("c.category_name");
				String productName = rs.getString("s.trade_name");//tradeNameが重複していたので
				String unitPrice = rs.getString("s.unit_price");
				String saleNumber = rs.getString("s.sale_number");
				String total = rs.getString("total");

				S0020Form f = new S0020Form(saleId, saleDate, staff, categoryName, productName,
						unitPrice, saleNumber, total);
				list.add(f);
			}
			return list;

		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			DBUtils.close(con, ps, rs);
		}
	}

	//getで検索画面表示するときの担当者選択用
	public List<S0020Form> staff() throws ServletException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		List<S0020Form> list = new ArrayList<>();

		try {
			con = DBUtils.getConnection();
			sql = "select account_id, name from accounts";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				String accountId = rs.getString("account_id");
				String accountName = rs.getString("name");

				S0020Form f = new S0020Form(accountId, accountName);
				list.add(f);
			}
			return list;

		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			DBUtils.close(con, ps, rs);
		}

	}

	//getで検索画面表示するときのカテゴリー選択用
	public List<String> category() throws ServletException {
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
