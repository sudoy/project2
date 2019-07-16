package sales;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sales.forms.S0022Form;
import sales.services.S0022Service;

@WebServlet("/S0022.html")
public class S0022Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();

		String id = req.getParameter("id");

		S0022Service serv = new S0022Service();
		S0022Form form = serv.service(id);

		session.setAttribute("S0022Form", form);

		getServletContext().getRequestDispatcher("/WEB-INF/S0022.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		getServletContext().getRequestDispatcher("/WEB-INF/S0022.jsp").forward(req, resp);
	}

}
