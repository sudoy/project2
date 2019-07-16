package accounts;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import accounts.forms.C0010Form;
import accounts.forms.S0042Form;
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

			//権限チェック(権限が無い場合はダッシュボードへ遷移)
			C0010Form checkauthority1 = (C0010Form) session.getAttribute("userinfo");

			if (!checkauthority1.getAuthority().equals("10") && !checkauthority1.getAuthority().equals("11")) {
				session.setAttribute("error", "不正なアクセスです。" );
				resp.sendRedirect("C0020.html");
			} else {

				getServletContext().getRequestDispatcher("/WEB-INF/S0042.jsp").forward(req, resp);
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		//ログインチェック
		HttpSession session = req.getSession();
		boolean login = false;

		if (session.getAttribute("login") != null) {
			login = (boolean) session.getAttribute("login");
		}

		if (login == false) {
			session.setAttribute("error", "ログインしてください。");
			resp.sendRedirect("C0010.html");
		} else {

		//権限チェック(権限が無い場合はダッシュボードへ遷移)
			C0010Form checkauthority2 = (C0010Form) session.getAttribute("userinfo");

			if (!checkauthority2.getAuthority().equals("10") && !checkauthority2.getAuthority().equals("11")) {
				session.setAttribute("error", "不正なアクセスです。" );
				resp.sendRedirect("C0020.html");
			}

			S0042Form form = (S0042Form) session.getAttribute("S0042Form");

			String id = form.getId();
			String name = form.getName();
			String mail = form.getMail();
			String password = form.getPassword();
			String authority = form.getAuthority();

			S0043Form updateform;

			//パスワードが未入力の場合は前のパスワードのまま更新(この時ハッシュ化はしない)
			if (password.equals("")) {
				String oldpassword = (String) session.getAttribute("password");
				updateform = new S0043Form(id, name, mail, oldpassword, password, authority);

			//パスワード入力時はそのまま更新
			} else {
				updateform = new S0043Form(id, name, mail, password, authority);
			}

			//更新
			S0043Service service = new S0043Service();
			service.update(updateform);

			session.removeAttribute("S0042Form");
			session.removeAttribute("password");

			//データ更新後の検索画面にする
			session.setAttribute("update", "on");// 7/11 16:08追加

			//成功メッセージ
			session.setAttribute("complete", "No" + updateform.getId() + "のアカウントを更新しました。");

			//更新完了後、S0041へ遷移(遷移先で成功メッセージを表示)
			resp.sendRedirect("S0041.html");

		}
	}

}
