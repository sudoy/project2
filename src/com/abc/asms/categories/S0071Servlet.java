package com.abc.asms.categories;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.asms.categories.forms.S0070Form;
import com.abc.asms.categories.forms.S0071Form;
import com.abc.asms.categories.sevices.S0071Service;
import com.abc.asms.others.forms.C0010Form;

@WebServlet("/S0071.html")
public class S0071Servlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		//ログインチェック
		HttpSession session = req.getSession();
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
			C0010Form checkauthority = (C0010Form) session.getAttribute("accounts");

			if (!checkauthority.getAuthority().equals("1") && !checkauthority.getAuthority().equals("11")) {
				error.add("不正なアクセスです。");
				session.setAttribute("error", error);
				resp.sendRedirect("C0020.html");
			} else {

				session.removeAttribute("complete");

				//S0070で入力した値の取得
				S0070Form form = (S0070Form) session.getAttribute("S0070Form");

				req.setAttribute("S0071Form", form);

				getServletContext().getRequestDispatcher("/WEB-INF/S0071.jsp").forward(req, resp);
			}
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		//ログインチェック
		HttpSession session = req.getSession();
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
			C0010Form checkauthority = (C0010Form) session.getAttribute("accounts");

			if (!checkauthority.getAuthority().equals("1") && !checkauthority.getAuthority().equals("11")) {
				error.add("不正なアクセスです。");
				session.setAttribute("error", error);
				resp.sendRedirect("C0020.html");
			} else {

				//req×
				S0070Form S0070Form = (S0070Form) session.getAttribute("S0070Form");

				String categoryname = S0070Form .getCategoryname();
				String active = S0070Form .getActive();

				S0071Form form = new S0071Form(categoryname, active);

				S0071Service service = new S0071Service();

				//登録
				service.insert(form);

				session.setAttribute("complete", "No" + service.Categoryid(form) + "の商品カテゴリーを登録しました。");

				resp.sendRedirect("S0070.html");

				session.removeAttribute("S0070Form");

			}
		}

	}

}
