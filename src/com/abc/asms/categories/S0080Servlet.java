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

import com.abc.asms.categories.forms.S0080Form;
import com.abc.asms.categories.sevices.S0080Service;

@WebServlet("/S0080.html")
public class S0080Servlet extends HttpServlet {

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

			List<S0080Form> list = new ArrayList<>();
			S0080Service service = new S0080Service();

			//一覧の取得
			list = service.select();

			session.setAttribute("S0080Form", list);

			getServletContext().getRequestDispatcher("/WEB-INF/S0080.jsp").forward(req, resp);
			session.removeAttribute("complete");

		}

	}

}
