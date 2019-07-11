package accounts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import accounts.forms.S0040Form;
import accounts.forms.S0041Form;
import accounts.services.S0040Service;

@WebServlet("/S0041.html")
public class S0041Servlet extends HttpServlet {

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
		} else {

			if (session.getAttribute("update").equals("on")) {//更新画面からの遷移ならば
				S0040Form form = (S0040Form) session.getAttribute("S0040Form");//sessionから検索時に入力したデータを取得
//				System.out.println("mail:" + form.getMail());
//				System.out.println("name:" + form.getName());
//				System.out.println("sale" + form.getSale());
				List<S0041Form> list = new ArrayList<>();
				S0040Service serv = new S0040Service();
				list = serv.service(form);//S0040のサービスにぶち込んで一覧を取得
				session.setAttribute("S0041Form", list);//sessionに格納

				getServletContext().getRequestDispatcher("/WEB-INF/S0041.jsp").forward(req, resp);//遷移

			} else {
				getServletContext().getRequestDispatcher("/WEB-INF/S0041.jsp").forward(req, resp);
			}
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
		} else {

			getServletContext().getRequestDispatcher("/WEB-INF/S0041.jsp").forward(req, resp);
		}
	}

}
