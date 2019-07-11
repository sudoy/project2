          package accounts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import accounts.forms.S0030Form;
import accounts.forms.S0031Form;
import accounts.services.S0030Service;
import accounts.services.S0031Service;

@WebServlet("/S0030.html")
public class S0030Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		boolean login = false;

		//ログインチェック
		if (session.getAttribute("login") != null) {//そもそもsessionが存在してないとエラーになるので
			//loginがtrue(ログイン状態にある)じゃないと入れないように
			login = (boolean) session.getAttribute("login");
		}

		if (login == false) {
			session.setAttribute("error", "ログインしてください。");
			resp.sendRedirect("C0010.html");
		} else {
			req.setCharacterEncoding("UTF-8");
			getServletContext().getRequestDispatcher("/WEB-INF/S0030.jsp").forward(req, resp);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();

		//ログインチェック
		boolean login = false;
		if (session.getAttribute("login") != null) {//そもそもsessionが存在してないとエラーになるので
			//loginがtrue(ログイン状態にある)じゃないと入れないように
			login = (boolean) session.getAttribute("login");
		}

		if (login == false) {
			session.setAttribute("error", "ログインしてください。");
			resp.sendRedirect("C0010.html");
		} else {

			try {
				S0031Form form2 = (S0031Form) session.getAttribute("form");
				String sale = form2.getSale();
				String account = form2.getAccount();
				String authority = null;


				if(sale.equals("0") && account.equals("0")) {
					authority = "0";
				}else if(sale.equals("1") && account.equals("0")) {
					authority = "1";
				}else if(sale.equals("0") && account.equals("1")) {
					authority = "10";
				}else if(sale.equals("1") && account.equals("1")) {
					authority = "11";
				}

				//フォームへ値をセット
				S0030Form form = new S0030Form();
				form.setName(form2.getName());
				form.setMail(form2.getMail());
				form.setPassword(form2.getPassword());
				form.setAuthority(authority);


				List<String> error = new ArrayList<String>();


				//メールアドレス重複確認
				S0031Service service2 = new S0031Service();
				boolean exist = service2.service(form);
				//ここまでok

				if(exist == false){
					error.add("メールアドレスが重複しています。") ;
					session.setAttribute("error", error);
					getServletContext().getRequestDispatcher("/WEB-INF/S0031.jsp").forward(req, resp);
					session.removeAttribute("error");
					session.removeAttribute("form");


				}else {

					// DBへ登録
					S0030Service service = new S0030Service();
					service.register(form);

					session.removeAttribute("form");

					session.setAttribute("complete", "登録しました");

					getServletContext().getRequestDispatcher("/WEB-INF/S0030.jsp").forward(req, resp);
					session.removeAttribute("complete");
				}

			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
