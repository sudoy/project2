package com.abc.asms.sales;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.asms.others.forms.C0010Form;
import com.abc.asms.sales.forms.S0010Form;
import com.abc.asms.sales.forms.S0011Form;
import com.abc.asms.sales.services.S0010Service;
import com.abc.asms.sales.services.S0011Service;

@WebServlet("/S0010.html")
public class S0010Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();

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
				//accountsテーブルから情報を取得
				S0010Service service = new S0010Service();
				List<S0010Form> form = service.select();

				req.setAttribute("accountsInfo", form);

				//今日の日付を取得
				String today = LocalDate.now().toString();
				S0010Form S0010f = new S0010Form(today);

				req.setAttribute("todayForm", S0010f);

				//商品カテゴリーの取得
				List<String> categoryList = service.category();

				session.setAttribute("allCategory", categoryList);

				//メッセージが出ないように
				session.removeAttribute("error");
				session.removeAttribute("complete");

				getServletContext().getRequestDispatcher("/WEB-INF/S0010.jsp").forward(req, resp);

			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		//ログインチェック
		HttpSession session = req.getSession();
		boolean login = false;
		List<String> error1 = new ArrayList<>();

		if (session.getAttribute("login") != null) {//そもそもsessionが存在してないとエラーになるので
			//loginがtrue(ログイン状態にある)じゃないと入れないように
			login = (boolean) session.getAttribute("login");
		}

		if (login == false) {
			error1.add("ログインしてください。");
			session.setAttribute("error", error1);
			resp.sendRedirect("C0010.html");
		} else {

			//権限チェック(権限が無い場合はダッシュボードへ遷移)
			C0010Form checkauthority1 = (C0010Form) session.getAttribute("accounts");

			//		System.out.println(checkaccount1.getAuthority());

			if (!checkauthority1.getAuthority().equals("10") && !checkauthority1.getAuthority().equals("11")) {
				error1.add("不正なアクセスです。");
				session.setAttribute("error", error1);
				resp.sendRedirect("C0020.html");
			} else {
				try {
					S0011Form s0011form = (S0011Form) session.getAttribute("form");

					//フォームへ値をセット
					S0010Form s0010form = new S0010Form();

					s0010form.setSaledate(s0011form.getSaledate());
					s0010form.setCategoryid(s0011form.getCategoryid());
					s0010form.setTradename(s0011form.getTradename());
					s0010form.setPrice(s0011form.getPrice());
					s0010form.setSalenumber(s0011form.getSalenumber());
					s0010form.setNote(s0011form.getNote());
					s0010form.setAccountid(s0011form.getAccountid());

					//account_idがテーブルに存在するかチェック
					List<String> error2 = new ArrayList<>();
					S0011Service s0011service = new S0011Service();
					boolean exist = s0011service.service(s0010form);

					if (exist == true) {
						error2.add("アカウントテーブルに存在しません。");

						// S0010.htmlを再表示
						session.setAttribute("error", error2);
						req.setAttribute("form", s0010form);

						//accountsテーブルから情報を取得
						S0010Service serv = new S0010Service();
						List<S0010Form> s0010form1 = serv.select();
						req.setAttribute("accounts", s0010form1);

						getServletContext().getRequestDispatcher("/WEB-INF/S0010.jsp").forward(req, resp);
						session.removeAttribute("error");

					} else {

						// DBへ登録
						S0010Service service = new S0010Service();

						service.register(s0010form);

						session.removeAttribute("form");
						session.setAttribute("complete", "No" + service.Saleid(s0010form) + "の売上を登録しました");

						//accountsテーブルから情報を取得
						S0010Service s0010service = new S0010Service();
						List<S0010Form> form = s0010service.select();
						req.setAttribute("accounts", form);

						//今日の日付を取得
						String today = LocalDate.now().toString();
						S0010Form S0010f = new S0010Form(today);

						req.setAttribute("todayForm", S0010f);

						getServletContext().getRequestDispatcher("/WEB-INF/S0010.jsp").forward(req, resp);
						session.removeAttribute("complete");
						session.removeAttribute("allCategory");
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
