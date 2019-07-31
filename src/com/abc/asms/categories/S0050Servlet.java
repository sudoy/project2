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

import com.abc.asms.categories.forms.S0050Form;
import com.abc.asms.categories.services.S0050Service;
import com.abc.asms.others.forms.C0010Form;
@WebServlet("/S0050.html")
public class S0050Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		req.setCharacterEncoding("UTF-8");
		//ログインチェック

		boolean login = false;
		List<String> error = new ArrayList<>();

		if (session.getAttribute("login") != null) {//そもそもsessionが存在してないとエラーになるので
			//loginがtrue(ログイン状態にある)じゃないと入れないように
			login = (boolean) session.getAttribute("login");
		}

		if (login == false) {
			error.add("ログインしてください。");
			session.setAttribute("error", error);
			resp.sendRedirect("C0010.html");
		} else {
			//権限チェック(権限が無い場合はダッシュボードへ遷移)
			C0010Form checkauthority1 = (C0010Form) session.getAttribute("accounts");

			if (!checkauthority1.getAuthority().equals("1") && !checkauthority1.getAuthority().equals("11")) {
				error.add("不正なアクセスです。");
				session.setAttribute("error", error);
				resp.sendRedirect("C0020.html");
			} else {
				getServletContext().getRequestDispatcher("/WEB-INF/S0050.jsp").forward(req, resp);
				session.removeAttribute("s0050form");

			}
		}

	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		req.setCharacterEncoding("UTF-8");
		//ログインチェック

		boolean login = false;
		List<String> loginerror = new ArrayList<>();

		if (session.getAttribute("login") != null) {//そもそもsessionが存在してないとエラーになるので
			//loginがtrue(ログイン状態にある)じゃないと入れないように
			login = (boolean) session.getAttribute("login");
		}

		if (login == false) {
			loginerror.add("ログインしてください。");
			session.setAttribute("error", loginerror);
			resp.sendRedirect("C0010.html");
		} else {
			//権限チェック(権限が無い場合はダッシュボードへ遷移)
			C0010Form checkauthority1 = (C0010Form) session.getAttribute("accounts");

			if (!checkauthority1.getAuthority().equals("1") && !checkauthority1.getAuthority().equals("11")) {
				loginerror.add("不正なアクセスです。");
				session.setAttribute("error", loginerror);
				resp.sendRedirect("C0020.html");
			} else {
				String category = req.getParameter("category");

				S0050Form s0050form = new S0050Form();
				s0050form.setCategory(category);

				//キャンセルしたときに値を使いたいから
				session.setAttribute("s0050form", s0050form);

				//途中
				req.setAttribute("category",category);


				// バリデーションチェック
				List<String> error = validate(category);

				//エラーがある場合
				if (error.size() != 0) {
					session.setAttribute("error", error);
					getServletContext().getRequestDispatcher("/WEB-INF/S0050.jsp").forward(req, resp);
					session.removeAttribute("error");

				} else {
					getServletContext().getRequestDispatcher("/WEB-INF/S0051.jsp").forward(req, resp);
				}

			}
		}

	}
	private List<String> validate(String category) {
		S0050Service s0050service = new S0050Service();
		boolean exist = s0050service.service(category);

		List<String> error = new ArrayList<String>();
		if (category.equals("")) {
			error.add("カテゴリー名を入力して下さい。");
		} else if (51 <= category.length()) {
			error.add("カテゴリー名が長すぎます。");
		}
		if (exist == false) {
			error.add("カテゴリー名が既に登録されています。");
		}

		return error;

	}

}
