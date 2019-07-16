package accounts;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import accounts.forms.C0010Form;
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

			//権限チェック(権限が無い場合はダッシュボードへ遷移)
			C0010Form checkaccount1 = (C0010Form) session.getAttribute("userinfo");

			if (!checkaccount1.getAuthority().equals("10") && !checkaccount1.getAuthority().equals("11")) {
				resp.sendRedirect("C0020.html");
			} else {

				S0044Service service = new S0044Service();

				//アカウント情報の取得
				S0044Form form = service.select(req.getParameter("id"));
				session.setAttribute("S0044Form", form);

				getServletContext().getRequestDispatcher("/WEB-INF/S0044.jsp").forward(req, resp);
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();
		boolean login = false;

		//ログインチェック
		if (session.getAttribute("login") != null) {
			login = (boolean) session.getAttribute("login");
		}
		if (login == false) {
			session.setAttribute("error", "ログインしてください。");
			resp.sendRedirect("C0010.html");
		} else {

			//権限チェック(権限が無い場合はダッシュボードへ遷移)
			C0010Form checkaccount2 = (C0010Form) session.getAttribute("userinfo");

			if (!checkaccount2.getAuthority().equals("10") && !checkaccount2.getAuthority().equals("11")) {
				resp.sendRedirect("C0020.html");
			} else {

				S0044Form form = (S0044Form) session.getAttribute("S0044Form");

				String id = form.getId();
				String name = form.getName();
				String mail = form.getMail();
				String password = form.getPassword();
				String authority = form.getAuthority();

				S0044Form deleteform = new S0044Form(id, name, mail, password, authority);

				//削除
				S0044Service service = new S0044Service();
				service.delete(deleteform);

				//取得したアカウント情報を破棄
				session.removeAttribute("S0044Form");
				//データ削除後の検索画面にする
				session.setAttribute("update", "on");//7/16 追加

				//成功メッセージ
				session.setAttribute("complete", "No" + deleteform.getId() + "のアカウントを削除しました。");

				//削除完了後、S0041.jspに遷移
				resp.sendRedirect("S0041.html");
			}
		}

	}

}
