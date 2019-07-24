package com.abc.asms.accounts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.asms.accounts.forms.S0042Form;
import com.abc.asms.accounts.services.S0042Service;
import com.abc.asms.others.forms.C0010Form;

@WebServlet("/S0042.html")
public class S0042Servlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		//ログインチェック
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

				S0042Service service = new S0042Service();

				//アカウント情報の取得(キャンセルで飛んでくると値が取れないのでifで囲んでいる)
				if (req.getParameter("id") != null) {
					S0042Form form = service.select(req.getParameter("id"));
					session.setAttribute("S0042Form", form);
					session.setAttribute("password", form.getPassword());
				}


				this.getServletContext().getRequestDispatcher("/WEB-INF/S0042.jsp").forward(req, resp);
			}
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();
		boolean login = false;
		List<String> error = new ArrayList<>();

		int authorityint = 0;
		String authority = null;

		if (session.getAttribute("login") != null) {
			login = (boolean) session.getAttribute("login");
		}

		if (login == false) {
			error.add("ログインしてください。");
			session.setAttribute("error", error);
			resp.sendRedirect("C0010.html");
			session.removeAttribute("error");//送ったら削除
		} else {

			//権限チェック(権限が無い場合はダッシュボードへ遷移)
			C0010Form checkauthority2 = (C0010Form) session.getAttribute("accounts");

			if (!checkauthority2.getAuthority().equals("10") && !checkauthority2.getAuthority().equals("11")) {
				error.add("不正なアクセスです。");
				session.setAttribute("error", error);
				resp.sendRedirect("C0020.html");
			} else {

				String id = req.getParameter("id");
				String name = req.getParameter("name");
				String mail = req.getParameter("mail");
				String password = req.getParameter("password");
				String check = req.getParameter("check");
				String sale = req.getParameter("sale");
				String account = req.getParameter("account");

				//saleとaccountの合計値をauthorityに入れることで権限の有無を判断
				if (account != null && sale != null) {
					authorityint = Integer.parseInt(account + sale);
					authority = String.valueOf(authorityint);
				}

				S0042Form form = new S0042Form(id, name, mail, password, check, sale, account, authority);


				//入力チェック
				error = validate(form);

				//エラー時はS0042.jspを再表示
				if (error.size() != 0) {

					session.setAttribute("error", error);
					session.setAttribute("S0042Form", form);

					getServletContext().getRequestDispatcher("/WEB-INF/S0042.jsp").forward(req, resp);

					//errorメッセージ除去
					session.removeAttribute("error");
				} else {

					//入力チェックをクリア後はS0043アカウント詳細編集確認画面へ遷移
					session.setAttribute("S0042Form", form);
					resp.sendRedirect("S0043.html");
				}
			}
		}

	}

	private List<String> validate(S0042Form form) {

		List<String> e = new ArrayList<>();

		String name = form.getName();
		String mail = form.getMail();
		String password = form.getPassword();
		String check = form.getCheck();
		String sale = form.getSale();
		String account = form.getAccount();

		String mailFormat = "^\\w+([-_.]\\w+)*@\\w+([-_.]\\w+)*\\.\\w+([-_.]\\w+)*$";
		Pattern mailp = Pattern.compile(mailFormat);
		Matcher mailm = mailp.matcher(mail);

		//氏名必須入力チェック
		if (name.equals("")) {
			e.add("氏名を入力してください。");
		} else if (21 <= form.getName().length()) {//氏名長さチェック(21文字以上でエラー)
			e.add("氏名が長すぎます。");
		}

		//メールアドレス必須入力チェック
		if (mail.equals("")) {
			e.add("メールアドレスを入力して下さい。");
		} else if (101 <= form.getMail().length()) {//メールアドレス長さチェック(101文字以上でエラー)
			e.add("メールアドレスが長すぎます。");
		}

		//メールアドレス形式チェック
		if (!mail.equals("") && mailm.find() == false) {
			e.add("メールアドレスの形式が誤っています。");
		} else if (31 <= form.getPassword().length()) {//パスワード長さチェック(31文字以上でエラー)
			e.add("パスワードが長すぎます。");
		} else if (!password.equals(check)) {//パスワード一致チェック (未入力の場合は前のパスワードのまま)
			e.add("パスワードとパスワード(確認)が一致していません。");
		}

		//売上登録権限必須チェック
		if (sale == null) {
			e.add("売上登録権限を入力して下さい。");
		} else if (!sale.equals("0") && !sale.equals("1")) {//売上登録権限値チェック
			e.add("売上登録権限に正しい値を入力してください。");
		}

		//アカウント登録権限必須チェック
		if (account == null) {
			e.add("アカウント登録権限を入力してください。");
		} else if (!account.equals("0") && !account.equals("1")) {//アカウント登録権限値チェック
			e.add("アカウント登録権限値に正しい値を入力して下さい。");
		}
		return e;

	}

}
