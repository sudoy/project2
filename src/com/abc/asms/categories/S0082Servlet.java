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

import com.abc.asms.categories.forms.S0081Form;
import com.abc.asms.categories.forms.S0082Form;
import com.abc.asms.categories.sevices.S0082Service;
import com.abc.asms.others.forms.C0010Form;

@WebServlet("/S0082.html")
public class S0082Servlet extends HttpServlet {

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

				getServletContext().getRequestDispatcher("/WEB-INF/S0082.jsp").forward(req, resp);
			}
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();

		S0081Form S0082Form = (S0081Form) session.getAttribute("S0082Form");

		String categoryid = S0082Form.getCategoryid();
		String categoryname = S0082Form.getCategoryname();
		String active = S0082Form.getActive();

		S0082Form form = new S0082Form(categoryid, categoryname, active);

		//更新
		S0082Service service = new S0082Service();
		service.update(form);

		//成功メッセージ
		session.setAttribute("complete", "No" + form.getCategoryid() + "の商品カテゴリーを更新しました。");

		resp.sendRedirect("S0080.html");



	}

}
