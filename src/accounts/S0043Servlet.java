package accounts;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import accounts.forms.S0043FormUpdate;
import accounts.services.S0043Service;

@WebServlet("/S0043.html")
public class S0043Servlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

//		HttpSession session = req.getSession();
//		boolean login = true;

		//ログインチェック???
//		if (session.getAttribute("login") != null) {
//			login = (boolean) session.getAttribute("login");
//
//		}
//		if (login == false) {
//			session.setAttribute("error", "不正なアクセスです。");
//				resp.sendRedirect("S0010.html");
//		}


		getServletContext().getRequestDispatcher("/WEB-INF/S0043.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		String name = req.getParameter("name");
		String mail = req.getParameter("mail");
		String password = req.getParameter("password");
		String check = req.getParameter("check");
		String sale = req.getParameter("sale");
		String account = req.getParameter("account");
		String authority = req.getParameter("authority");

		System.out.println("test");
		System.out.println(name);

		//ログインチェック
		//権限チェック(権限がない場合はダッシュボードへ遷移)
		if(!authority.equals("10") && !authority.equals("11")) {
			resp.sendRedirect("C0020.html");
		}

		S0043FormUpdate form = new S0043FormUpdate(name, mail, password, check, sale, account, authority);

		//更新
		S0043Service service = new S0043Service();
		service.update(form);

		//更新完了後、S0041へ遷移
		resp.sendRedirect("S0041.html");
	}


}
