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

import com.abc.asms.goods.utils.DBUtils;
import com.abc.asms.others.forms.C0010Form;
import com.abc.asms.sales.forms.S0025Form;
import com.abc.asms.sales.services.S0025Service;

@WebServlet("/S0025.html")
public class S0025Servlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();

		//ログインチェック
		boolean login = false;
		List<String> error = new ArrayList<>();

		if (session.getAttribute("login") != null) {
			login = (boolean) session.getAttribute("login");
		}
		if (login == false) {
			error.add("ログインしてください。");
			session.setAttribute("error", error);
			resp.sendRedirect("C0010.html");
		} else {

			//権限チェック(権限が無い場合はダッシュボードへ遷移)
			C0010Form checkauthority1 = (C0010Form) session.getAttribute("accounts");


			if (!checkauthority1.getAuthority().equals("10") && !checkauthority1.getAuthority().equals("11")) {
				error.add("不正なアクセスです。");
				session.setAttribute("error", error);
				resp.sendRedirect("C0020.html");
			} else {

				String id = req.getParameter("id");
				if (id == null) {
					resp.sendRedirect("C0020.html");
					return;
				} else {
					boolean judge = DBUtils.checkSaleId(id);
					if (judge == false) {
						resp.sendRedirect("C0020.html");
						return;
					}

				}

				//渡されたidのsalesテーブル, c.category_name, a.nameの取得
				S0025Service service = new S0025Service();
				S0025Form form = service.select(req.getParameter("id"));
				session.setAttribute("S0025Form", form);



				//全category_nameの取得
				List<String> categoryList = service.category();
				req.setAttribute("allCategory", categoryList);


				//小計
				int pricenum = Integer.parseInt(form.getUnitprice());
				int salenumbernum = Integer.parseInt(form.getSalenumber());
				int totalnum = pricenum * salenumbernum;
				String total = String.valueOf(totalnum);
				form.setTotal(total);



				getServletContext().getRequestDispatcher("/WEB-INF/S0025.jsp").forward(req, resp);
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
			C0010Form checkauthority1 = (C0010Form) session.getAttribute("accounts");
			if (!checkauthority1.getAuthority().equals("10") && !checkauthority1.getAuthority().equals("11")) {
				error.add("不正なアクセスです。");
				session.setAttribute("error", error);
				resp.sendRedirect("C0020.html");
			} else {

				S0025Form s0025form = (S0025Form) session.getAttribute("S0025Form");

				//削除
				S0025Service service = new S0025Service();
				service.delete(s0025form);

				session.removeAttribute("S0025Form");
				session.removeAttribute("allCategory");
				session.removeAttribute("allName");
				session.removeAttribute("S0022Form");

				//成功メッセージ
				session.setAttribute("complete", "No" + s0025form.getId() + "の売上を削除しました。");

				resp.sendRedirect("S0021.html");

			}
		}
	}
}
