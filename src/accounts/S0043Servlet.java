package accounts;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import accounts.forms.S0042FormPost;
import accounts.forms.S0043Form;
import accounts.services.S0043Service;

@WebServlet("/S0043.html")
public class S0043Servlet extends HttpServlet {

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

			getServletContext().getRequestDispatcher("/WEB-INF/S0042.jsp").forward(req, resp);
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

			S0042FormPost form = (S0042FormPost) session.getAttribute("S0042Form");

			String id = form.getId();
			String name = form.getName();
			String mail = form.getMail();
			String password = form.getPassword();
			String authority = form.getAuthority();

			S0043Form updateform = new S0043Form(id, name, mail, password, authority);

			//ログインチェック
			//権限チェック(権限がない場合はダッシュボードへ遷移)
			if (!(authority.equals("10")) && !(authority.equals("11"))) {
				resp.sendRedirect("C0020.html");
			} else {

				//更新
				S0043Service service = new S0043Service();
				service.update(updateform);

				session.removeAttribute("S0042Form");

				//更新完了後、S0041へ遷移
				resp.sendRedirect("S0041.html");
			}
		}
	}

}
