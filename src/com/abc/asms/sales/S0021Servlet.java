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

import com.abc.asms.sales.forms.S0020Form;
import com.abc.asms.sales.forms.S0021Form;
import com.abc.asms.sales.services.S0021Service;

@WebServlet("/S0021.html")
public class S0021Servlet extends HttpServlet {
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

			S0020Form form = (S0020Form) session.getAttribute("S0020Form");//sessionから検索時に入力したデータを取得

			List<S0021Form> list = new ArrayList<>();
			S0021Service serv = new S0021Service();
			list = serv.service(form);//formをS0021のサービスに渡して一覧を取得

			if (list.size() == 0) {//検索結果なしの場合
				error.add("検索結果はありません。");
				session.setAttribute("error", error);//sessionにエラーメッセージを格納

				resp.sendRedirect("S0020.html");

			} else {
				session.setAttribute("S0021Form", list);//sessionに格納

				getServletContext().getRequestDispatcher("/WEB-INF/S0021.jsp").forward(req, resp);//遷移
				session.removeAttribute("complete");
				session.removeAttribute("allCategory");
				session.removeAttribute("allName");

			}
		}
	}

}
