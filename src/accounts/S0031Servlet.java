package accounts;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import accounts.forms.S0031Form;
import accounts.services.S0031Service;

@WebServlet("/s0031.html")
public class S0031Servlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		String name = req.getParameter("name");
		String mail = req.getParameter("mail");
		String password = req.getParameter("password");
		String check = req.getParameter("check");
		String sale = req.getParameter("sale");
		String account = req.getParameter("account");
		S0031Form form = new S0031Form(name, mail, password, check, sale, account);
		req.setAttribute("form", form);

		HttpSession session = req.getSession();
		session.setAttribute("name", name);
		session.setAttribute("mail", mail);
		session.setAttribute("password", password);
		session.setAttribute("sale", sale);
		session.setAttribute("account",account);


		// バリデーションチェック
		String error = validate(form);

		//エラーがある場合
		if(!error.equals("")) {
			// S0030.htmlを再表示
			req.setAttribute("error", error);
			req.setAttribute("form", form);
			getServletContext().getRequestDispatcher("/WEB-INF/S0030.jsp")
				.forward(req, resp);
			return;
		}

		//メールアドレス重複確認
		S0031Service service = new S0031Service();

		ResultSet mailcheck = service.mail();

		if(mail.equals(mailcheck)) {
			System.out.println("aaa");
		}



		getServletContext().getRequestDispatcher("/WEB-INF/S0031.jsp").forward(req, resp);
	}
	private String validate(S0031Form form) {
		String error = "";

		String name = form.getName();
		String mail = form.getMail();
		String password = form.getPassword();
		String check = form.getCheck();


		if(name.equals("")) {
			error += "氏名は必須入力です。";
		}
		if(mail.equals("")) {
			error += "メールアドレスは必須入力です。";
		}
		if(password.equals("")) {
			error += "パスワードは必須入力です。";
		}
		if(!password.equals(check)) {
			error += "パスワードが異なります";
		}
		return error;

	}
}
