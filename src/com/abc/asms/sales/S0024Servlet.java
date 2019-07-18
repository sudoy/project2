package com.abc.asms.sales;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.asms.others.forms.C0010Form;
import com.abc.asms.sales.forms.S0023Form;
import com.abc.asms.sales.forms.S0024Form;
import com.abc.asms.sales.services.S0024Service;

@WebServlet("/S0024.html")
public class S0024Servlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		//ログインチェック
		HttpSession session = req.getSession();
		boolean login = false;
		List<String> error = new ArrayList<>();

		if (session.getAttribute("login") != null) {
			login = (boolean) session.getAttribute("login");
		}

		if (login == false) {
			error.add("ログインしてください。");
			session.setAttribute("error", error);
			resp.sendRedirect("C0010.html");
		} else {

			//権限チェック(権限が無い場合はダッシュボードへ遷移)
			C0010Form checkauthority1 = (C0010Form) session.getAttribute("userinfo");

			if (!checkauthority1.getAuthority().equals("1") && !checkauthority1.getAuthority().equals("11")) {
				error.add("不正なアクセスです。");
				session.setAttribute("error", error);
				resp.sendRedirect("C0020.html");
			} else {

				getServletContext().getRequestDispatcher("/WEB-INF/S0024.jsp").forward(req, resp);
			}

		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");


		//ログインチェック
		HttpSession session = req.getSession();
		boolean login = false;
		List<String> error = new ArrayList<>();

		if (session.getAttribute("login") != null) {
			login = (boolean) session.getAttribute("login");
		}

		if (login == false) {
			error.add("ログインしてください。");
			session.setAttribute("error", error);
			resp.sendRedirect("C0010.html");
		} else {

			//権限チェック(権限が無い場合はダッシュボードへ遷移)
			C0010Form checkauthority2 = (C0010Form) session.getAttribute("userinfo");

			if (!checkauthority2.getAuthority().equals("1") && !checkauthority2.getAuthority().equals("11")) {
				error.add("不正なアクセスです。");
				session.setAttribute("error", error);
				resp.sendRedirect("C0020.html");
			} else {

				S0023Form form = (S0023Form) session.getAttribute("S0023Form");

				String saledate = form.getSaledate();
				String name = form.getName();
				String categoryname = form.getCategoryname();
				String tradename = form.getTradename();
				String price = form.getPrice();
				String salenumber = form.getSalenumber();
				String note = form.getNote();

//				int intprice = Integer.parseInt(price);
//				int intsalenumber = Integer.parseInt(salenumber);
//				String total = Integer.toString(intprice * intsalenumber);

				S0024Form updateform = new S0024Form( saledate, name, categoryname, tradename, price, salenumber, note);

				//更新
				S0024Service service = new S0024Service();
				service.update(updateform);

				session.removeAttribute("S0023Form");
				session.removeAttribute("password");

				//成功メッセージ
				session.setAttribute("complete", "No" + updateform.getSaleid() + "のアカウントを更新しました。");

				//更新完了後、S0021へ遷移(遷移先で成功メッセージを表示)
				resp.sendRedirect("S0021.html");
			}

		}

	}

}
