package com.abc.asms.accounts;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

import com.abc.asms.accounts.forms.S0031Form;
import com.abc.asms.accounts.services.S0031Service;
import com.abc.asms.others.forms.C0010Form;

@WebServlet("/S0031.html")
public class S0031Servlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		//ログインチェック
		HttpSession session = req.getSession();
		boolean login = false;
		List<String> loginerror = new ArrayList<>();


		if (session.getAttribute("login") != null) {//そもそもsessionが存在してないとエラーになるので
			//loginがtrue(ログイン状態にある)じゃないと入れないように
			login = (boolean) session.getAttribute("login");
		}

		if (login == false) {
			loginerror.add("ログインしてください。");
			session.setAttribute("error", loginerror);
			resp.sendRedirect("C0010.html");

		} else {

			//権限チェック(権限が無い場合はダッシュボードへ遷移)
			C0010Form checkauthority1 = (C0010Form) session.getAttribute("userinfo");

			if (!checkauthority1.getAuthority().equals("10") && !checkauthority1.getAuthority().equals("11")) {
				loginerror.add("不正なアクセスです。");
				session.setAttribute("error", loginerror);
				resp.sendRedirect("C0020.html");

			}else {

				String name = req.getParameter("name");
				String mail = req.getParameter("mail");
				String password = req.getParameter("password");
				String check = req.getParameter("check");
				String sale = req.getParameter("sale");
				String account = req.getParameter("account");

				S0031Form s0031form = new S0031Form(name, mail, password, check, sale, account);

				//登録確認からキャンセルで戻った時にも使えるように
				session = req.getSession();
				session.setAttribute("S0031form", s0031form);


				// バリデーションチェック
				List<String> error = validate(s0031form);

				//エラーがある場合
				if(error.size() != 0) {
					// S0030.htmlを再表示
					session.setAttribute("error", error);
					getServletContext().getRequestDispatcher("/WEB-INF/S0030.jsp").forward(req, resp);
					session.removeAttribute("error");


				}else {
					getServletContext().getRequestDispatcher("/WEB-INF/S0031.jsp").forward(req, resp);
				}

			}

		}
	}


	private List<String> validate(S0031Form form) throws UnsupportedEncodingException {
		List<String> error = new ArrayList<String>();
		//メールアドレス重複確認
		S0031Service s0031service = new S0031Service();
		boolean exist = s0031service.service(form);

		String name = form.getName();
		String mail = form.getMail();
		String password = form.getPassword();
		String check = form.getCheck();
		String sale = form.getSale();
		String account = form.getAccount();


		if(name.equals("")) {
			error.add("氏名を入力して下さい。") ;
		}else if (21 <= name.length()) {
			error.add("氏名が長すぎます。");
		}

		if(mail.equals("")) {
			error.add( "メールアドレスを入力して下さい。");
		}else if(101 <= mail.length()) {
			error.add("メールアドレスが長すぎます。");
		}

		//mailの形式チェック
		//「＠」が含まれていること。先頭は「a-zA-Z0-9」で、2文字目以降はさらに「._-」の文字が許容される。
		//「＠」以降は「a-zA-Z0-9._-」が1文字以上続き、必ず「.」が含まれていること
		if (!(form.getMail().equals("")) &&
				(form.getMail().getBytes("UTF-8").length) < 101) {//mailが入力されており且101バイトより小さい
			String mailFormat = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
			Pattern pattern = Pattern.compile(mailFormat);
			Matcher matcher = pattern.matcher(form.getMail());
			if (!matcher.find()) {
				error.add("メールアドレスを正しく入力して下さい。");
			}
		}
		if(exist == false){
			error.add("メールアドレスが既に登録されています。") ;
		}

		if(password.equals("")) {
			error.add("パスワードを入力して下さい。");

		}else if (31 <= password.length()) {
			error.add("パスワードが長すぎます。");
		}

		if(check.equals("")) {
			error.add("パスワード（確認）を入力して下さい。");
		}

		if(!password.equals(check)) {
			error.add("パスワードとパスワード（確認）が一致していません。");
		}

		if (sale == null || sale.isEmpty()) {
			error.add( "売上登録権限を入力して下さい。");
		}else if(!sale.contentEquals("0") && !sale.contentEquals("1")) {
			error.add( "売上登録権限に正しい値を入力して下さい。");

		}
		if (account == null || account.isEmpty()) {
			error.add( "アカウント登録権限を入力して下さい。");
		}else if(!account.contentEquals("0") && !account.contentEquals("1")) {
			error.add( "アカウント登録権限に正しい値を入力して下さい。");
		}

		return error;

	}

}
