package com.abc.asms.accounts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.asms.accounts.forms.S0044Form;
import com.abc.asms.accounts.services.S0044Service;
import com.abc.asms.goods.utils.DBUtils;
import com.abc.asms.others.forms.C0010Form;

@WebServlet("/S0044.html")
public class S0044Servlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();
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
					boolean judge = DBUtils.checkAccountId(id);
					if (judge == false) {
						resp.sendRedirect("C0020.html");
						return;
					}

				}

				S0044Service service = new S0044Service();

				//アカウント情報の取得
				//				if (req.getParameter("id") != null) {
				S0044Form form = service.select(req.getParameter("id"));
				session.setAttribute("S0044Form", form);//reqに修正→sessionに戻した

				getServletContext().getRequestDispatcher("/WEB-INF/S0044.jsp").forward(req, resp);
				//				} else {
				//				resp.sendRedirect("S0040.html");
				//				}
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
			C0010Form checkauthority2 = (C0010Form) session.getAttribute("accounts");

			if (!checkauthority2.getAuthority().equals("10") && !checkauthority2.getAuthority().equals("11")) {
				error.add("不正なアクセスです。");
				session.setAttribute("error", error);
				resp.sendRedirect("C0020.html");
			} else {

				S0044Form deleteform = (S0044Form) session.getAttribute("S0044Form");

				//削除
				S0044Service service = new S0044Service();
				service.delete(deleteform);

				//取得したアカウント情報を破棄
				session.removeAttribute("S0044Form");

				//成功メッセージ
				session.setAttribute("complete", "No" + deleteform.getId() + "のアカウントを削除しました。");

				//削除完了後、S0041.jspに遷移
				resp.sendRedirect("S0041.html");
			}
		}

	}

}
