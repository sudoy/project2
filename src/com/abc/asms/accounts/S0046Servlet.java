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

import com.abc.asms.accounts.forms.S0046Form;
import com.abc.asms.accounts.services.S0046Service;

@WebServlet("/S0046.html")
public class S0046Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		HttpSession session = req.getSession();
		List<String> error = new ArrayList<>();

		String mail = req.getParameter("user");//受信メール内のURL末尾にあるメールアドレスの情報を取得

		System.out.println(mail);

		if(mail == null) {//userが取得できなければ
			resp.sendRedirect("C0010.html");
			return;
		}

		//メール存在チェック(パラメーターで渡されたメールアドレスが存在しない場合はエラー)
		S0046Service serv = new S0046Service();
		boolean mailExist = serv.selectmail(mail);

		if(mailExist == false) {
			error.add("メールアドレスが存在しません。");
			session.setAttribute("error", error);
		}

		session.setAttribute("receivedEmail", mail);

		getServletContext().getRequestDispatcher("/WEB-INF/S0046.jsp").forward(req, resp);

		//errorメッセージ除去
		session.removeAttribute("error");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		HttpSession session = req.getSession();

		String mail = (String) session.getAttribute("receivedEmail");
		String password = req.getParameter("password");
		String check = req.getParameter("check");

		S0046Form form = new S0046Form(mail, password, check);

		//入力チェック
		List<String> error = validate(form);

		//エラー時はS0046.jspを再表示
		if (error.size() != 0) {
			session.setAttribute("error", error);

			getServletContext().getRequestDispatcher("/WEB-INF/S0046.jsp").forward(req, resp);

			//errorメッセージ除去
			session.removeAttribute("error");
		} else {

			//入力チェック完了後パスワード更新
			S0046Service service = new S0046Service();
			service.updatepassword(form);

			//パスワード更新後はC0010へ遷移
			session.removeAttribute("S0046Form");
			session.setAttribute("complete", "パスワードを再設定しました。");
			resp.sendRedirect("C0010.html");

		}
	}

	//入力チェック
	private List<String> validate(S0046Form form) {

		List<String> error = new ArrayList<>();

		String mail = form.getMail();
		String password = form.getPassword();
		String check = form.getCheck();

		//メール存在チェック //登録してあるメールアドレスと一致するかどうか
		S0046Service serv = new S0046Service();
		boolean mailExist = serv.selectmail(mail);

		if(mailExist == false) {
			error.add("メールアドレスが存在しません。");
		}

		//新パスワード必須入力チェック
		if (password.equals("")) {
			error.add("パスワードを入力してください。");
		}
		//新パスワード長さチェック

		if (31 <= password.length()) {
			error.add("パスワードが長すぎます。");
		}

		//新パスワード確認必須入力チェック
		if (check.equals("")) {
			error.add("確認用パスワードを入力してください");
		}
		//新パスワード一致チェック
		if (!password.equals(check)) {
			error.add("新パスワードとパスワード(確認)が一致していません。");
		}
		return error;
	}

}
