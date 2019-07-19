package com.abc.asms.sales.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import com.abc.asms.goods.utils.DBUtils;
import com.abc.asms.sales.forms.S0020Form;
import com.abc.asms.sales.forms.S0021Form;

public class S0021Service {
	public List<S0021Form> service(S0020Form form) throws ServletException {

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

		List<S0021Form> list = new ArrayList<>();

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
			sql += " order by s.sale_id desc";

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

				S0021Form f = new S0021Form(saleId, saleDate, staff, categoryName, productName,
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

}
