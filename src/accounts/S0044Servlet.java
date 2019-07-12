package accounts;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import accounts.forms.S0044Form;
import accounts.services.S0044Service;

@WebServlet("/S0044.html")
public class S0044Servlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
			S0044Service service = new S0044Service();

			S0044Form form = service.select(req.getParameter("id"));
			session.setAttribute("S0044Form", form);

			getServletContext().getRequestDispatcher("/WEB-INF/S0044.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();
		boolean login = false;

		if (session.getAttribute("login") != null) {
			login = (boolean) session.getAttribute("login");
		}
		if (login == false) {
			session.setAttribute("error", "ログインしてください。");
			resp.sendRedirect("C0010.html");
		} else {

			String id = req.getParameter("id");
			String name = req.getParameter("name");
			System.out.println("テスト");
			System.out.println(req.getParameter("name"));//null
			System.out.println("テスト");
			String mail = req.getParameter("mail");
			String password = req.getParameter("password");
			String authority = req.getParameter("authority");

			S0044Form deleteform = new S0044Form(id, name, mail, password, authority);

			S0044Service service = new S0044Service();
			service.delete(deleteform);

			resp.sendRedirect("S0041.html");
		}

	}

}
