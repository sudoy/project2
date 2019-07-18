package com.abc.asms.others;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.asms.others.forms.C0010Form;
import com.abc.asms.others.forms.C0020Form;
import com.abc.asms.others.services.C0020Service;

@WebServlet("/C0020.html")
public class C0020Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		boolean login = false;

		if (session.getAttribute("login") != null) {//そもそもsessionが存在してないとエラーになるので
			//loginがtrue(ログイン状態にある)じゃないと入れないように
			login = (boolean) session.getAttribute("login");
		}

		if (login == false) {
			session.setAttribute("error", "ログインしてください。");
			resp.sendRedirect("C0010.html");
		} else {//以下ログイン状態にあるときの処理

			C0010Form userInfo = (C0010Form) session.getAttribute("userinfo");

			String value = req.getParameter("value");
			LocalDate today = null;
			if (value == null) {//value==nullなら今日の日付を取得して代入
				today = LocalDate.now();

			} else {//value!=nullならsessionからtodayを取得して代入
				today = (LocalDate) session.getAttribute("today");
			}

			//DBからなんやかんやして一覧を取得

			C0020Service serv = new C0020Service();
			List<C0020Form> form = serv.service(userInfo.getMail(), value, today);

			session.setAttribute("C0020Form", form);

			//年月、今月の売上合計、前月の売上合計、前月比、ログインしている人の今月の売上合計、今月、前月
			C0020Form variousForm = serv.returnVariousForm(userInfo.getId());

			req.setAttribute("variousForm", variousForm);
			session.setAttribute("today", C0020Service.today);//todayの値の保持

			getServletContext().getRequestDispatcher("/WEB-INF/C0020.jsp").forward(req, resp);

			session.removeAttribute("error");
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		boolean login = false;

		if (session.getAttribute("login") != null) {//そもそもsessionが存在してないとエラーになるので
			//loginがtrue(ログイン状態にある)じゃないと入れないように
			login = (boolean) session.getAttribute("login");
		}

		if (login == false) {
			session.setAttribute("error", "ログインしてください。");
			resp.sendRedirect("C0010.html");
		} else {//以下ログイン状態にあるときの処理

			getServletContext().getRequestDispatcher("/WEB-INF/C0020.jsp").forward(req, resp);

			session.removeAttribute("error");
		}

	}

}
