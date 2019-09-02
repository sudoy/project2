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

import com.abc.asms.categories.forms.S0061Form;
import com.abc.asms.categories.services.S0062Service;
import com.abc.asms.others.forms.C0010Form;
@WebServlet("/S0062.html")

public class S0062Servlet extends HttpServlet {
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
				String name = req.getParameter("name");
				String flg1 = req.getParameter("flg1");
				String flg0 = req.getParameter("flg0");
				String id = req.getParameter("id");
				String flg = null;

				if(flg1.equals("1")) {
					flg = flg1;
				}else if(flg0.equals("0")) {
					flg = flg0;
				}

				S0061Form s0061form = new S0061Form(name, flg, id);

				S0062Service service = new S0062Service();
				service.update(s0061form);


				session.setAttribute("complete", "No" + id + "の商品カテゴリーを更新しました");


				resp.sendRedirect("S0060.html");
				session.removeAttribute("id");



			}
		}
	}
}
