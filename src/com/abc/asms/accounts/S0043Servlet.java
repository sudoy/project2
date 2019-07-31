package com.abc.asms.accounts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.asms.accounts.forms.S0042Form;
import com.abc.asms.accounts.forms.S0043Form;
import com.abc.asms.accounts.services.S0043Service;
import com.abc.asms.goods.utils.DBUtils;
import com.abc.asms.others.forms.C0010Form;

@WebServlet("/S0043.html")
public class S0043Servlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
		} else {

			//権限チェック(権限が無い場合はダッシュボードへ遷移)
			C0010Form checkauthority1 = (C0010Form) session.getAttribute("accounts");

			if (!checkauthority1.getAuthority().equals("10") && !checkauthority1.getAuthority().equals("11")) {
				error.add("不正なアクセスです。");
				session.setAttribute("error", error);
				resp.sendRedirect("C0020.html");
			} else {

				//idが存在しない場合はダッシュボードに遷移

				S0042Form form = (S0042Form) session.getAttribute("S0042Form");
				try {
					String id = form.getId();

					boolean judge = DBUtils.checkAccountId(id);
					if (judge == false) {
						resp.sendRedirect("C0020.html");
						return;
					}
				} catch (NullPointerException e) {
					resp.sendRedirect("C0020.html");
					return;
				}
				getServletContext().getRequestDispatcher("/WEB-INF/S0043.jsp").forward(req, resp);//遷移

			}

		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

			//権限チェック(権限が無い場合はダッシュボードへ遷移)
			C0010Form checkauthority2 = (C0010Form) session.getAttribute("accounts");

			if (!checkauthority2.getAuthority().equals("10") && !checkauthority2.getAuthority().equals("11")) {
				error.add("不正なアクセスです。");
				session.setAttribute("error", error);
				resp.sendRedirect("C0020.html");
			} else {

				S0042Form form = (S0042Form) session.getAttribute("S0042Form");

				String id = form.getId();
				String name = form.getName();
				String mail = form.getMail();
				String password = form.getPassword();
				String authority = form.getAuthority();
				String version = form.getVersion();

				S0043Form updateform;

				//パスワードが未入力の場合は前のパスワードのまま更新(この時ハッシュ化はしない)
				if (password.equals("")) {
					String oldpassword = (String) session.getAttribute("password");
					updateform = new S0043Form(id, name, mail, oldpassword, password, authority, version);

					//パスワード入力時はそのまま更新
				} else {
					updateform = new S0043Form(id, name, mail, password, authority, version);
				}

				//更新
				S0043Service service = new S0043Service();
				error = service.update(updateform);//updateに失敗するとエラーメッセージが返ってくる

				//updateに失敗したら（他のユーザーと被ったら）
				if (error.size() != 0) {
					session.setAttribute("error", error);

				} else {//成功したら
					//成功メッセージ
					session.setAttribute("complete", "No" + updateform.getId() + "のアカウントを更新しました。");
				}

				session.removeAttribute("S0042Form");
				session.removeAttribute("password");

				//成功失敗どちらにせよ検索結果一覧に遷移しメッセージを表示
				resp.sendRedirect("S0041.html");
			}

		}
	}

}
