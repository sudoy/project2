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

import com.abc.asms.accounts.forms.S0045Form;
import com.abc.asms.accounts.services.S0045Service;

@WebServlet("/S0045.html")
public class S0045Servlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		getServletContext().getRequestDispatcher("/WEB-INF/S0045.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//mailをformにいれる
		String mail = req.getParameter("mail");
		S0045Form s0045form = new S0045Form(mail);

		HttpSession session = req.getSession();
		session = req.getSession();

		//入力チェック
		List<String> error = validate(s0045form);

		//エラー時はS0045.jspを再表示
		if (error.size() != 0) {
			session.setAttribute("error", error);
			req.setAttribute("S0045Form", s0045form);

			getServletContext().getRequestDispatcher("/WEB-INF/S0045.jsp").forward(req, resp);

			//errorメッセージ除去
			session.removeAttribute("error");

		} else {
			//メール送信
			S0045Service serv = new S0045Service();
			error = serv.sendMail(mail);
			if (error.size() == 0) {
				session.setAttribute("complete", "パスワード再設定メールを送信しました。");

				getServletContext().getRequestDispatcher("/WEB-INF/S0045.jsp").forward(req, resp);
				session.removeAttribute("complete");
			}else {//メールが送信できなかったら
				session.setAttribute("error", error);
				req.setAttribute("S0045Form", s0045form);

				getServletContext().getRequestDispatcher("/WEB-INF/S0045.jsp").forward(req, resp);

				//errorメッセージ除去
				session.removeAttribute("error");
			}

		}
	}

	private List<String> validate(S0045Form s0045form) throws UnsupportedEncodingException {
		List<String> error = new ArrayList<String>();
		S0045Service s0045service = new S0045Service();
		boolean exist = s0045service.service(s0045form);

		String mail = s0045form.getMail();

		if (mail.equals("")) {
			error.add("メールアドレスを入力して下さい。");
		} else if (101 <= mail.length()) {
			error.add("メールアドレスが長すぎます。");

			//mailの形式チェック
			//「＠」が含まれていること。先頭は「a-zA-Z0-9」で、2文字目以降はさらに「._-」の文字が許容される。
			//「＠」以降は「a-zA-Z0-9._-」が1文字以上続き、必ず「.」が含まれていること
		} else if (!(s0045form.getMail().equals("")) &&
				(s0045form.getMail().length()) < 101) {//mailが入力されており且101バイトより小さい
			String mailFormat = "^\\w+([-_.]\\w+)*@\\w+([-_.]\\w+)*\\.\\w+([-_.]\\w+)*$";
			Pattern pattern = Pattern.compile(mailFormat);
			Matcher matcher = pattern.matcher(s0045form.getMail());
			if (!matcher.find()) {
				error.add("メールアドレスを正しく入力して下さい。");
			}

		}
		//メールアドレスが存在しない場合
		if (exist == false) {
			error.add("メールアドレスを正しく入力して下さい。");
		}

		return error;

	}

}
