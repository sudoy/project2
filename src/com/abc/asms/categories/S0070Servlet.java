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
import com.abc.asms.categories.sevices.S0070Service;
import com.abc.asms.others.forms.C0010Form;

@WebServlet("/S0070.html")
public class S0070Servlet extends HttpServlet {
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

				getServletContext().getRequestDispatcher("/WEB-INF/S0070.jsp").forward(req, resp);
				session.removeAttribute("error");
				session.removeAttribute("complete");
			}
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();

		String categoryname = req.getParameter("categoryname");
		String active = req.getParameter("active");

		S0070Form form = new S0070Form(categoryname, active);

		List<String> error = validate(form);

		//エラー発生時
		if (error.size() != 0) {

			session.setAttribute("error", error);
			req.setAttribute("S0070Form", form);
			getServletContext().getRequestDispatcher("/WEB-INF/S0070.jsp").forward(req, resp);

			session.removeAttribute("error");

		} else {
			session.setAttribute("S0070Form", form);//getで表示させる為
			resp.sendRedirect("S0071.html");

		}
	}

	private List<String> validate(S0070Form form) {

		List<String> error = new ArrayList<>();
		S0070Service service = new S0070Service();
		boolean exist = service.categoryexist(form);

		String categoryname = form.getCategoryname();
		String active = form.getActive();

		if (categoryname.equals("")) {
			error.add("カテゴリー名を入力してください。");
		} else if (51 <= categoryname.length()) {
			error.add("カテゴリー名が長すぎます。");
		} else if (exist == false) {
			error.add("カテゴリー名が重複しています。");
		}

		if (active == null) {
			error.add("有効/無効を入力して下さい。");
		} else if (!active.equals("0") && !active.equals("1")) {
			error.add("有効/無効に正しい値を入力して下さい。");
		}

		return error;
	}

}
