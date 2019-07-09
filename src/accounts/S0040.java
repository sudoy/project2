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

import accounts.forms.S0040Form;
import accounts.forms.S0041Form;
import accounts.services.S0040Service;

@WebServlet("/S0040.html")
public class S0040 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		getServletContext().getRequestDispatcher("/WEB-INF/S0040.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		String name = req.getParameter("name");
		String mail = req.getParameter("mail");
		String sale = req.getParameter("sale");
		String account = req.getParameter("account");

		S0040Form form = new S0040Form(name, mail, sale, account);

		List<S0041Form> list = new ArrayList<>();
		S0040Service serv = new S0040Service();
		list = serv.service(form);//Serviceで取得した一覧

		HttpSession session = req.getSession();
		session.setAttribute("form", list);

		getServletContext().getRequestDispatcher("/S0041.html").forward(req, resp);
	}

}
