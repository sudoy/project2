package sales;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sales.forms.S0010Form;

@WebServlet("/s0011.html")
public class S0011Servlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		String saledate = req.getParameter("saledate");
		String accountid = req.getParameter("accountid");
		String categoryid = req.getParameter("categoryid");
		String tradename = req.getParameter("tradename");
		String price = req.getParameter("price");
		String salenumber = req.getParameter("salenumber");
		String note = req.getParameter("note");

		System.out.println(categoryid);

		System.out.println(price);
		//単価と個数をかけて小計をだす
		int pricenum = Integer.parseInt(price);
		int salenumbernum = Integer.parseInt(salenumber);


		System.out.println(pricenum);

		int total = pricenum * salenumbernum;
		req.setAttribute("total", total);


		S0010Form saleform = new S0010Form(saledate, accountid, categoryid, tradename, price, salenumber, note);




		HttpSession session = req.getSession();
		session.setAttribute("saleform", saleform);

		getServletContext().getRequestDispatcher("/WEB-INF/S0011.jsp").forward(req, resp);

	}
}
