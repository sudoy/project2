package accounts;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/s0031.html")
public class S0031Servlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		String name = req.getParameter("name");
		String mail = req.getParameter("mail");
		String password = req.getParameter("password");
		String sale = req.getParameter("sale");
		String account = req.getParameter("account");


		HttpSession session = req.getSession();
		session.setAttribute("name", name);
		session.setAttribute("mail", mail);
		session.setAttribute("password", password);
		session.setAttribute("sale", sale);
		session.setAttribute("account",account);

		getServletContext().getRequestDispatcher("/WEB-INF/S0031.jsp").forward(req, resp);
	}

}
