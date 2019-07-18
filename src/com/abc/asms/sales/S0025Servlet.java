package com.abc.asms.sales;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		if (session.getAttribute("login") != null) {
			login = (boolean) session.getAttribute("login");
		}
		if (login == false) {
			session.setAttribute("error", "ログインしてください。");
			resp.sendRedirect("C0010.html");
		} else {

			//権限チェック(権限が無い場合はダッシュボードへ遷移)
			C0010Form checkauthority2 = (C0010Form) session.getAttribute("userinfo");

			if (!checkauthority2.getAuthority().equals("10") && !checkauthority2.getAuthority().equals("11")) {
				resp.sendRedirect("C0020.html");
			} else {




				//渡されたidからアカウント情報の取得
				S0025Service service = new S0025Service();
				S0025Form form = service.select(req.getParameter("id"));
				session.setAttribute("S0025Form", form);

				//小計をだす
				int pricenum = Integer.parseInt(form.getUnitprice());
				int salenumbernum = Integer.parseInt(form.getSalenumber());
				int total = pricenum * salenumbernum;
				req.setAttribute("total", total);


				getServletContext().getRequestDispatcher("/WEB-INF/S0025.jsp").forward(req, resp);
			}
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		boolean login = false;

		//ログインチェック
		if (session.getAttribute("login") != null) {
			login = (boolean) session.getAttribute("login");
		}
		if (login == false) {
			session.setAttribute("error", "ログインしてください。");
			resp.sendRedirect("C0010.html");
		} else {

			//権限チェック(権限が無い場合はダッシュボードへ遷移)
			C0010Form checkauthority2 = (C0010Form) session.getAttribute("userinfo");

			if (!checkauthority2.getAuthority().equals("10") && !checkauthority2.getAuthority().equals("11")) {
				resp.sendRedirect("C0020.html");
			} else {

				S0025Form s0025form = (S0025Form) session.getAttribute("S0025Form");

				//削除
				S0025Service service = new S0025Service();
				service.delete(s0025form);

				//取得したアカウント情報を破棄
				session.removeAttribute("S0025Form");

				//成功メッセージ
				session.setAttribute("complete", "No" + s0025form.getId() + "のアカウントを削除しました。");

				//削除完了後、S0021.jspに遷移
				resp.sendRedirect("S0021.html");
			}
		}
	}
}
