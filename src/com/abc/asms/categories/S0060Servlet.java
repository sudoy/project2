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

import com.abc.asms.categories.forms.S0060Form;
import com.abc.asms.categories.services.S0060Service;
@WebServlet("/S0060.html")
public class S0060Servlet extends HttpServlet {
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
			//権限チェックを入れる


			S0060Service service = new S0060Service();
			List<S0060Form> form = service.select();


			req.setAttribute("categories", form);

			getServletContext().getRequestDispatcher("/WEB-INF/S0060.jsp").forward(req, resp);
			session.removeAttribute("complete");



		}

	}
}
