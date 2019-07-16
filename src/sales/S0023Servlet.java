package sales;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sales.forms.S0023Form;
import sales.services.S0023Service;

@WebServlet("/S0023.html")
public class S0023Servlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");


		S0023Service service = new S0023Service();

		S0023Form form = service.select(req.getParameter("id"));






	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();

		String saledate = req.getParameter("saledate");
		String name = req.getParameter("name");
		String categoryname = req.getParameter("categoryname");
		String tradename = req.getParameter("tradename");
		String price = req.getParameter("price");
		String salenumber = req.getParameter("salenumber");
		String note = req.getParameter("note");

		S0023Form form = new S0023Form(saledate,name, categoryname, tradename, price, salenumber, note);

		session.setAttribute("S0023Form", form);



		getServletContext().getRequestDispatcher("/WEB-INF/S0023.jsp").forward(req, resp);


	}

}
