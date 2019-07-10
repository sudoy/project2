package accounts;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import accounts.forms.S0031Form;

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
//		req.setAttribute("form", form);

		HttpSession session = req.getSession();
		session.setAttribute("form", form);


		// バリデーションチェック
		List<String> error = validate(form);

		//エラーがある場合
		if(error.size() != 0) {
			// S0030.htmlを再表示
			session.setAttribute("error", error);
			req.setAttribute("form", form);
			getServletContext().getRequestDispatcher("/WEB-INF/S0030.jsp")
				.forward(req, resp);
			session.removeAttribute("error");

			getServletContext().getRequestDispatcher("/WEB-INF/S0030.jsp").forward(req, resp);
		}

		getServletContext().getRequestDispatcher("/WEB-INF/S0031.jsp").forward(req, resp);

	}
	private List<String> validate(S0031Form form) throws UnsupportedEncodingException {
		List<String> error = new ArrayList<String>();  //list add

		String name = form.getName();
		String mail = form.getMail();
		String password = form.getPassword();
		String check = form.getCheck();


		if(name.equals("")) {
			error.add("氏名は必須入力です。") ;
		}else if (!(form.getName().equals("")) &&
				(21 <= form.getName().getBytes("UTF-8").length)) {
			error.add("氏名が長すぎます。");
		}

		if(mail.equals("")) {
			error.add( "メールアドレスは必須入力です。");
		}else if (!(form.getMail().equals("")) &&
				(101 <= form.getMail().getBytes("UTF-8").length)) {
			error.add("メールアドレスが長すぎます。");
		}
		if(password.equals("")) {
			error.add("パスワードは必須入力です。");
		}else if (!(form.getPassword().equals("")) &&
				(33 <= form.getPassword().getBytes("UTF-8").length)) {
			error.add("パスワードが長すぎます。");
		}

		if(!password.equals(check)) {
			error.add("パスワードが異なります");
		}



		return error;

	}
}
