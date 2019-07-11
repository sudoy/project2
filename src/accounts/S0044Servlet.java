package accounts;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import accounts.forms.S0044FormGet;
import accounts.services.S0044Service;

@WebServlet("/S0044.html")
public class S0044Servlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		S0044Service service = new S0044Service();

		S0044FormGet form = service.select(req.getParameter("id"));
		req.setAttribute("S0044Form", form);

		getServletContext().getRequestDispatcher("/WEB-INF/S0044.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {




		getServletContext().getRequestDispatcher("/WEB-INF/S0044.jsp").forward(req, resp);
	}



}
