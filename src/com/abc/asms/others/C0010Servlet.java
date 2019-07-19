package com.abc.asms.others;

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

import com.abc.asms.others.forms.C0010Form;
import com.abc.asms.others.services.C0010Service;

@WebServlet("/C0010.html")
public class C0010Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		boolean login = false;

		if (session.getAttribute("login") != null) {//そもそもsessionが存在してないとエラーになるので
			//sessinが存在していればloginに取り出した値を代入
			login = (boolean) session.getAttribute("login");
		}

		if (login == true) {//ログイン状態にあればダッシュボードに遷移
			resp.sendRedirect("C0020.html");
		} else {//ログイン状態になければログイン画面へ

			getServletContext().getRequestDispatcher("/WEB-INF/C0010.jsp").forward(req, resp);

			session.removeAttribute("error");
			session.removeAttribute("complete");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		boolean login = false;

		if (session.getAttribute("login") != null) {//そもそもsessionが存在してないとエラーになるので
			//sessinが存在していればloginに取り出した値を代入
			login = (boolean) session.getAttribute("login");
		}

		if (login == true) {//ログイン状態にあればダッシュボードに遷移
			resp.sendRedirect("C0020.html");
		} else {//ログイン状態になければログイン画面へ

			String mail = req.getParameter("mail");
			String password = req.getParameter("password");

			C0010Form form = new C0010Form(mail, password);

			List<String> error = validate(form);

			if (error.size() == 0) {//エラーが一つもなければ

				C0010Service C0010s = new C0010Service();
				C0010Form userinfo = C0010s.service(form);//ログイン状態（true or false）とログインした人のユーザー情報が返る
				session.setAttribute("login", userinfo.isLogin());

				if (userinfo.isLogin() == true) {//mailとpassが正しければ（trueが返ってきていれば）
					session.removeAttribute("error");

					session.setAttribute("userinfo", userinfo);
					resp.sendRedirect("C0020.html");
				} else {
					error.add("メールアドレス、パスワードを正しく入力して下さい。");
					session.setAttribute("error", error);

					req.setAttribute("C0010Form", form);//初期表示用 このformはmailとpasswordのみ格納されている
					getServletContext().getRequestDispatcher("/WEB-INF/C0010.jsp").forward(req, resp);
					//エラーと成功メッセージのみ解放
					session.removeAttribute("error");

				}
			} else {//エラーが一つでもあれば
				session.setAttribute("error", error);//sessionにエラーメッセージを格納
				req.setAttribute("C0010Form", form);//初期表示用 このformはmailとpasswordのみ格納されている

				getServletContext().getRequestDispatcher("/WEB-INF/C0010.jsp").forward(req, resp);//検索入力画面を再表示

				session.removeAttribute("error");//送ったら削除

			}
		}

	}

	private List<String> validate(C0010Form form) throws UnsupportedEncodingException {

		List<String> error = new ArrayList<>();

		//mailが入力されていない
		if (form.getMail().equals("")) {
			error.add("メールアドレスを入力して下さい。");
		}
		//mailが入力されており且文字数が101以上
		if (!(form.getMail().equals("")) &&
				(101 <= form.getMail().length())) {
			error.add("メールアドレスが長すぎます。");
		}
		//mailの形式チェック
		//「＠」が含まれていること。先頭は「a-zA-Z0-9」で、2文字目以降はさらに「._-」の文字が許容される。
		//「＠」以降は「a-zA-Z0-9._-」が1文字以上続き、必ず「.」が含まれていること
		if (!(form.getMail().equals("")) &&
				(101 >= form.getMail().length())) {//mailが入力されており且101文字以下
			String mailFormat = "^\\w+([-_.]\\w+)*@\\w+([-_.]\\w+)*\\.\\w+([-_.]\\w+)*$";
			Pattern pattern = Pattern.compile(mailFormat);
			Matcher matcher = pattern.matcher(form.getMail());
			if (matcher.find() == false) {
				error.add("メールアドレスを正しく入力して下さい。");
			}
		}

		//passwordが入力されていない
		if (form.getPassword().equals("")) {
			error.add("パスワードを入力して下さい。");
		}
		//passowrdが入力されており且バイト数が31以上
		if (!(form.getPassword().equals("")) &&
				(31 <= form.getPassword().length())) {
			error.add("パスワードが長すぎます。");
		}

		return error;

	}

}
