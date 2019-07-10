          package accounts;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import accounts.forms.S0030Form;
import accounts.forms.S0031Form;
import accounts.services.S0030Service;

@WebServlet("/s0030.html")
public class S0030Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		getServletContext().getRequestDispatcher("/WEB-INF/S0030.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		try {

			HttpSession session = req.getSession();
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

			// DBへ登録
			S0030Service service = new S0030Service();
			service.register(form);

			req.setAttribute("complete", complete);

			// リダイレクト
			resp.sendRedirect("s0030.html");

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
