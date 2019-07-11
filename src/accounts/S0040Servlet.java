package accounts;

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

import accounts.forms.S0040Form;
import accounts.forms.S0041Form;
import accounts.services.S0040Service;

@WebServlet("/S0040.html")
public class S0040Servlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
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
			req.setCharacterEncoding("UTF-8");
			getServletContext().getRequestDispatcher("/WEB-INF/S0040.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
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

			String name = req.getParameter("name");
			String mail = req.getParameter("mail");
			String sale = req.getParameter("sale");
			String account = req.getParameter("account");

			S0040Form form = new S0040Form(name, mail, sale, account);

			List<String> error = validate(form);

			if (error.size() == 0) {

				List<S0041Form> list = new ArrayList<>();
				S0040Service serv = new S0040Service();
				list = serv.service(form);//Serviceで取得した一覧
				if (list.size() == 0) {//2つ下のelseとほぼ同じことをしているのでまとめたい
					error.add("検索結果はありません。");
					session.setAttribute("error", error);//sessionにエラーメッセージを格納
					session.setAttribute("S0040Form", form);//初期表示用

					getServletContext().getRequestDispatcher("/WEB-INF/S0040.jsp").forward(req, resp);//検索入力画面を再表示

					session.removeAttribute("error");//送ったら削除
				} else {

					session.setAttribute("S0041Form", list);//sessionに取得した一覧を格納
					session.setAttribute("S0040Form", form);//更新時の再表示用
					getServletContext().getRequestDispatcher("/S0041.html").forward(req, resp);
				}
			} else {
				session.setAttribute("error", error);//sessionにエラーメッセージを格納
				session.setAttribute("S0040Form", form);//初期表示用

				getServletContext().getRequestDispatcher("/WEB-INF/S0040.jsp").forward(req, resp);//検索入力画面を再表示

				session.removeAttribute("error");//送ったら削除
			}
		}
	}

	private List<String> validate(S0040Form form) throws UnsupportedEncodingException {

		List<String> error = new ArrayList<>();

		//nameが入力されており且バイト数が21以上
		if (!(form.getName().equals("")) &&
				(21 <= form.getName().getBytes("UTF-8").length)) {
			error.add("氏名の指定が長すぎます。");
		}

		//mailが入力されており且バイト数が101以上
		if (!(form.getMail().equals("")) &&
				(101 <= form.getMail().getBytes("UTF-8").length)) {//kigenが空じゃない時
			error.add("メールアドレスの指定が長すぎます。");
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
				error.add("メールアドレスの形式が間違っています。");
			}
		}

		return error;
	}

}
