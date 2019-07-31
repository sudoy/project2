package com.abc.asms.categories;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.asms.categories.forms.S0083Form;
import com.abc.asms.categories.sevices.S0083Service;

@WebServlet("/S0083.html")
public class S0083Servlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		S0083Service service = new S0083Service();
		S0083Form form = service.select(req.getParameter("categoryid"));

		req.setAttribute("S0083Form", form);

		getServletContext().getRequestDispatcher("/WEB-INF/S0083.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();

		String categoryid = req.getParameter("categoryid");

		S0083Form form = new S0083Form(categoryid);

		S0083Service service = new S0083Service();
		service.delete(form);

		//成功メッセージ
		session.setAttribute("complete", "No" + form.getCategoryid() + "の商品カテゴリーを更新しました。");

		resp.sendRedirect("S0080.html");




	}

}
