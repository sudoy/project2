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

import accounts.forms.S0042FormGet;
import accounts.forms.S0042FormPost;
import accounts.services.S0042Service;

@WebServlet("/S0042.html")
public class S0042Servlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		boolean login = false;

		//ログインチェック???
		if (session.getAttribute("login") != null) {
			login = (boolean) session.getAttribute("login");

		}
		if (login == false) {
			session.setAttribute("error", "不正なアクセスです。");
			//			resp.sendRedirect("S0010.html");
		}

		//アカウント登録権限チェック(登録権限が無い場合はC0020_ダッシュボードへ遷移)

		S0042Service service = new S0042Service();

		S0042FormGet form = service.select(req.getParameter("id"));
		req.setAttribute("form", form);

		this.getServletContext().getRequestDispatcher("/WEB-INF/S0042.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String mail = req.getParameter("mail");
		String password = req.getParameter("password");
		String check = req.getParameter("check");
		String sale = req.getParameter("sale");
		String account = req.getParameter("account");
		String authority = req.getParameter("authority");

		S0042FormPost form = new S0042FormPost(id, name, mail, password, check, sale, account, authority);

		//入力チェック
		List<String> error = validate(form);

		//エラー時はS0042.jspを再表示
		if (error.size() != 0) {

			req.setAttribute("error", error);
			req.setAttribute("form", form);

			getServletContext().getRequestDispatcher("/WEB-INF/S0042.jsp").forward(req, resp);
		} else {

			//入力チェックをクリアすればS0043_アカウント詳細編集確認画面へ遷移
			resp.sendRedirect("S0043.html");
		}

	}

	private List<String> validate(S0042FormPost form) throws UnsupportedEncodingException {//catch

		List<String> e = new ArrayList<>();

		String name = form.getName();
		String mail = form.getMail();
		String password = form.getPassword();
		String check = form.getCheck();
		String sale = form.getSale();
		String account = form.getAccount();

		String mailFormat = "^[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+(\\.[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+)*+(.*)@[a-zA-Z0-9][a-zA-Z0-9\\-]*(\\.[a-zA-Z0-9\\-]+)+$";
		Pattern mailp = Pattern.compile(mailFormat);
		Matcher mailm = mailp.matcher(mail);

		//		 Pattern namep = Pattern.compile("^\\s");
		//		 Matcher namem = namep.matcher(name);

		//権限チェック アカウント登録権限のない場合はダッシュボードに遷移し、エラーを表示

		//氏名必須入力チェック
		if (name.equals("")) {
			e.add("氏名を入力してください。");
		}
		//氏名長さチェック(21バイト以上でエラー)
		if (21 <= form.getName().getBytes("UTF-8").length) {
			e.add("氏名が長すぎます。");
		}
		//メールアドレス必須入力チェック
		if (mail.equals("")) {
			e.add("メールアドレスを入力して下さい。");
		}
		//メールアドレス長さチェック(101バイト以上でエラー)
		if (101 <= form.getMail().getBytes("UTF-8").length) {
			e.add("メールアドレスが長すぎます。");
		}
		//メールアドレス形式チェック
		if (!mail.equals("") && mailm.find() == false) {
			e.add("メールアドレスの形式が誤っています。");
		}
		//パスワード長さチェック(31バイト以上)
		if (31 <= form.getPassword().getBytes("UTF-8").length) {
			e.add("パスワードが長すぎます。");
		}
		//パスワード一致チェック
		if (!password.equals("") && !password.equals("") && !password.equals(check)) {
			e.add("パスワードとパスワード(確認)が一致していません。");
		}
		//売上登録権限必須チェック
		if (sale == null) {
			e.add("売上登録権限を入力して下さい。");
		}
		//売上登録権限値チェック
		if (!sale.equals("0") && !sale.equals("1")) {
			e.add("売上登録権限に正しい値を入力してください。");
		}
		//アカウント登録権限必須チェック
		if (account == null) {
			e.add("アカウント登録権限を入力してください。");
		}
		//アカウント登録権限値チェック
		if (!sale.equals("0") && !sale.equals("1")) {
			e.add("アカウント登録権限値に正しい値を入力して下さい。");
		}
		System.out.println(e.get(0));
		return e;

	}

}
