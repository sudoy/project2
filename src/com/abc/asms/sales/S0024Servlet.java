package com.abc.asms.sales;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.asms.others.forms.C0010Form;
import com.abc.asms.sales.forms.S0023Form;
import com.abc.asms.sales.forms.S0024Form;
import com.abc.asms.sales.services.S0024Service;

@WebServlet("/S0024.html")
public class S0024Servlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();
		boolean login = false;
		List<String> error = new ArrayList<>();

		//ログインチェック
		if (session.getAttribute("login") != null) {
			login = (boolean) session.getAttribute("login");
		}

		if (login == false) {
			error.add("ログインしてください。");
			session.setAttribute("error", error);
			resp.sendRedirect("C0010.html");
		} else {

			//権限チェック(権限が無い場合はダッシュボードへ遷移)
			C0010Form checkauthority = (C0010Form) session.getAttribute("accounts");

			if (!checkauthority.getAuthority().equals("1") && !checkauthority.getAuthority().equals("11")) {
				error.add("不正なアクセスです。");
				session.setAttribute("error", error);
				resp.sendRedirect("C0020.html");
			} else {

				S0023Form form = (S0023Form) session.getAttribute("S0023Form");
				if (form == null) {
					resp.sendRedirect("C0020.html");
					return;
				}

				//jspに表示する為の商品カテゴリー一覧
				S0024Service service = new S0024Service();
				List<String> categories = service.categories();

				req.setAttribute("categories", categories);

				getServletContext().getRequestDispatcher("/WEB-INF/S0024.jsp").forward(req, resp);
			}

		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();
		boolean login = false;
		List<String> error = new ArrayList<>();

		//ログインチェック
		if (session.getAttribute("login") != null) {
			login = (boolean) session.getAttribute("login");
		}

		if (login == false) {
			error.add("ログインしてください。");
			session.setAttribute("error", error);
			resp.sendRedirect("C0010.html");
		} else {

			//権限チェック(権限が無い場合はダッシュボードへ遷移)
			C0010Form checkauthority = (C0010Form) session.getAttribute("accounts");

			if (!checkauthority.getAuthority().equals("1") && !checkauthority.getAuthority().equals("11")) {
				error.add("不正なアクセスです。");
				session.setAttribute("error", error);
				resp.sendRedirect("C0020.html");
			} else {

				S0023Form forminput = (S0023Form) session.getAttribute("S0023Form");//S0023で入力した値

				String saleid = forminput.getId();
				String saledate = forminput.getSaledate();
				String name = forminput.getName();
				String categoryname = forminput.getCategoryname();
				String tradename = forminput.getTradename();
				String price = forminput.getPrice();
				String salenumber = forminput.getSalenumber();
				String note = forminput.getNote();
				String version = forminput.getVersion();

				//nameをaccount_idに、categorynameをcategoryidに変換(salesテーブルにname, categorynameが存在しない為)
				S0024Service service = new S0024Service();

				String accountid = service.selectid(name);
				String categoryid = service.selectcategoryid(categoryname);

				S0024Form updateform = new S0024Form(accountid, categoryid, saleid, saledate, tradename, price,
						salenumber, note, version);

				//更新
				error = service.update(updateform);

				//更新に失敗したら
				if (error.size() != 0) {
					session.setAttribute("error", error);
				} else {//成功したら
						//成功メッセージ
					session.setAttribute("complete", "No" + updateform.getSaleid() + "の売上を更新しました。");
				}
				//入力した値の消去
				session.removeAttribute("S0023Form");

				//成功失敗どちらにせよ検索結果一覧に遷移しメッセージを表示
				resp.sendRedirect("S0021.html");
			}

		}

	}

}
