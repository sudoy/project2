package com.abc.asms.sales;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.asms.sales.forms.S0022Form;
import com.abc.asms.sales.services.S0022Service;

@WebServlet("/S0022.html")
public class S0022Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
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
		} else {//以下ログイン状態にあるときの処理
			String id = req.getParameter("id");

			S0022Service serv = new S0022Service();
			S0022Form form = serv.service(id);

			session.setAttribute("S0022Form", form);

			

			getServletContext().getRequestDispatcher("/WEB-INF/S0022.jsp").forward(req, resp);
		}
	}


}
