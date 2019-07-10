package accounts;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/S0044.html")
public class S0044Servlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//		S0042Service service = new S0042Service();

//		S0044Form form = service.select(req.getParameter("id"));
//		req.setAttribute("form", form);

		getServletContext().getRequestDispatcher("/WEB-INF/S0044.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		getServletContext().getRequestDispatcher("/WEB-INF/S0044.jsp").forward(req, resp);
	}



}