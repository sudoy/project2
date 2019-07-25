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

import com.abc.asms.accounts.forms.S0030Form;
import com.abc.asms.accounts.forms.S0031Form;
import com.abc.asms.accounts.services.S0030Service;
import com.abc.asms.others.forms.C0010Form;

@WebServlet("/S0030.html")
public class S0030Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

		req.setCharacterEncoding("UTF-8");
		boolean login = false;
		List<String> error = new ArrayList<>();

		//ログインチェック
		if (session.getAttribute("login") != null) {//そもそもsessionが存在してないとエラーになるので
			//loginがtrue(ログイン状態にある)じゃないと入れないように
			login = (boolean) session.getAttribute("login");
		}

		if (login == false) {
			error.add("ログインしてください。");
			session.setAttribute("error", error);
			resp.sendRedirect("C0010.html");

		}else {

			//アカウント検索画面で出たエラーメッセージを消す
			session.removeAttribute("error");

			//権限チェック(権限が無い場合はダッシュボードへ遷移)
			C0010Form checkauthority1 = (C0010Form) session.getAttribute("accounts");

			if (!checkauthority1.getAuthority().equals("10") && !checkauthority1.getAuthority().equals("11")) {
				error.add("不正なアクセスです。");
				session.setAttribute("error", error);
				resp.sendRedirect("C0020.html");
			}else {

				getServletContext().getRequestDispatcher("/WEB-INF/S0030.jsp").forward(req, resp);
				session.removeAttribute("error");

			}
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		List<String> error = new ArrayList<>();

		//ログインチェック
		boolean login = false;
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

			//		System.out.println(checkaccount1.getAuthority());

			if (!checkauthority1.getAuthority().equals("10") && !checkauthority1.getAuthority().equals("11")) {
				error.add("不正なアクセスです。");
				session.setAttribute("error", error);
				resp.sendRedirect("C0020.html");
			}else {

				try {
					S0031Form s0031form = (S0031Form) session.getAttribute("S0031form");
					String sale = s0031form.getSale();
					String account = s0031form.getAccount();
					String authority = null;


					if(sale.equals("0") && account.equals("0")) {
						authority = "0";
					}else if(sale.equals("1") && account.equals("0")) {
						authority = "1";
					}else if(sale.equals("0") && account.equals("1")) {
						authority = "10";
					}else if(sale.equals("1") && account.equals("1")) {
						authority = "11";
					}

					//フォームへ値をセット
					S0030Form s0030form = new S0030Form();
					s0030form.setName(s0031form.getName());
					s0030form.setMail(s0031form.getMail());
					s0030form.setPassword(s0031form.getPassword());
					s0030form.setAuthority(authority);


					// DBへ登録
					S0030Service service = new S0030Service();
					service.register(s0030form);

					session.removeAttribute("S0031form");


					session.setAttribute("complete", "No" + service.Accountid(s0030form) + "のアカウントを登録しました");

					getServletContext().getRequestDispatcher("/WEB-INF/S0030.jsp").forward(req, resp);
					session.removeAttribute("complete");


				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
